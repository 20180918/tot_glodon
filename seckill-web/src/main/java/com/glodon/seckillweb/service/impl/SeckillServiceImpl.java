package com.glodon.seckillweb.service.impl;

import com.alibaba.fastjson.JSON;
import com.glodon.seckillcommon.utils.LockUtil;
import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
import com.glodon.seckillcommon.exception.ClosedSeckillException;
import com.glodon.seckillcommon.exception.RepeatSeckillException;
import com.glodon.seckillweb.dto.SeckillExecution;
import com.glodon.seckillweb.dto.SeckillInfoContent;
import com.glodon.seckillweb.dto.UrlExposer;
import com.glodon.seckillweb.mapper.SeckillProductDAO;
import com.glodon.seckillweb.mapper.SuccessKilledDAO;
import com.glodon.seckillweb.service.SeckillService;
import com.glodon.seckillweb.task.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 秒杀实现类
 *
 * @author wupx
 * @date 2019/08/12
 */
@Service("seckillService")
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillProductDAO seckillProductDAO;

    @Autowired
    private SuccessKilledDAO successKilledDAO;

    @Autowired
    private KafkaSender kafkaSender;

    @Override
    public List<SeckillProduct> getSeckillList() {
        return seckillProductDAO.selectAll();
    }


    @Override
    public SeckillProduct selectBySeckillId(String seckillId, boolean fastsearch) {
        if (fastsearch) {
            String key = "product_detail" + seckillId;
            String redisObject = com.glodon.seckillcommon.utils.RedisUtil.get(key);
            if (redisObject == null) {
                SeckillProduct seckillProduct = seckillProductDAO.selectByPrimaryKey(seckillId);
                com.glodon.seckillcommon.utils.RedisUtil.setExpire(key.getBytes(), com.glodon.seckillcommon.utils.SerializationUtil.serialize(seckillProduct),60);
                return seckillProduct;
            } else {
                return (SeckillProduct) com.glodon.seckillcommon.utils.SerializationUtil.deserialize(com.glodon.seckillcommon.utils.RedisUtil.get(key.getBytes()));
            }
        } else {
            SeckillProduct seckillProduct = seckillProductDAO.selectByPrimaryKey(seckillId);
            return seckillProduct;
        }
    }


    /**
     * @param seckillId
     * @return
     * @author wangpp-b
     */
    @Override
    public UrlExposer exportSeckillUrl(String seckillId) {

        SeckillProduct seckillProduct = selectBySeckillId(seckillId, true);
        //若是秒杀未开启
        Date startTime = seckillProduct.getStartTime();
        Date endTime = seckillProduct.getEndTime();
        //系统当前时间
        Date nowTime = new Date();
        if (startTime.getTime() > nowTime.getTime() || endTime.getTime() < nowTime.getTime()) {
            return new UrlExposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        //秒杀开启，返回秒杀商品的id、用给接口加密的md5
        String md5 = getSaltMD5(seckillId);
        return new UrlExposer(true, md5, Long.parseLong(seckillId));
    }

    @Override
    public void doSeckill(String seckillId, String userPhone, String md5) {
        if (md5 == null || !md5.equals(getSaltMD5(seckillId))) {
            //秒杀数据被重写了
            throw new RuntimeException("seckill data rewrite");
        }
        //执行秒杀逻辑:减库存+增加购买明细
        Date nowTime = new Date();

        try {
            int insertCount = successKilledDAO.insertSuccessKilled(seckillId, userPhone);
            //看是否该明细被重复插入，即用户是否重复秒杀
            if (insertCount <= 0) {
                throw new RepeatSeckillException("seckill repeated");
            } else {
                int updateCount=0;
                //减库存
                if(LockUtil.lock(seckillId)){
                    updateCount = seckillProductDAO.reduceNumber(seckillId, nowTime);
                    LockUtil.unLock(seckillId);
                }else {
                     doSeckill(seckillId,userPhone,md5);
                }
                if (updateCount <= 0) {
                    //没有更新库存记录，说明秒杀结束 rollback
                    String seckillInfoContent = JSON.toJSONString(new SeckillInfoContent(Long.valueOf(seckillId), md5, userPhone, (byte) -1));
                    kafkaSender.sendChannelMess("generateorder", seckillInfoContent);
                } else {
                    // 向 generateorder 主题发送kafka请求信息内容
                    String seckillInfoContent = JSON.toJSONString(new SeckillInfoContent(Long.valueOf(seckillId), md5, userPhone, (byte) 0));
                    kafkaSender.sendChannelMess("generateorder", seckillInfoContent);
                }
            }

        } catch (RepeatSeckillException e1) {
            throw e1;
        } catch (ClosedSeckillException e2) {
            throw e2;
        } catch (Exception e) {
            //所以编译期异常转化为运行期异常
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private String getSaltMD5(String seckillId) {
        String base = seckillId + "/" + "wangpengpengasdas54d57asd754as";
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}

