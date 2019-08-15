package com.glodon.seckillweb.service.impl;

import com.alibaba.fastjson.JSON;
import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
import com.glodon.seckillcommon.utils.LockUtil;
import com.glodon.seckillweb.dto.SeckillInfoContent;
import com.glodon.seckillweb.dto.UrlExposer;
import com.glodon.seckillweb.mapper.SeckillProductDAO;
import com.glodon.seckillweb.mapper.SuccessKilledDAO;
import com.glodon.seckillweb.service.SeckillService;
import com.glodon.seckillweb.task.KafkaSender;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public List<SeckillProduct> getSeckillList() {
        return seckillProductDAO.selectAll();
    }


    @Override
    public SeckillProduct selectBySeckillId(String seckillId, boolean fastsearch) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        if (fastsearch) {
            String key = "product_detail" + seckillId;
            String stringValue = null;
            stringValue=redisTemplate.opsForValue().get(key);
            if (StringUtils.isEmpty(stringValue)) {
                SeckillProduct seckillProduct = seckillProductDAO.selectByPrimaryKey(seckillId);
                valueOperations.set(key, JSON.toJSONString(seckillProduct),5, TimeUnit.SECONDS);
                return seckillProduct;
            } else {
                return  JSON.parseObject(stringValue, SeckillProduct.class);
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
        Date nowTime = new Date();
        int insertCount = successKilledDAO.insertSuccessKilled(seckillId, userPhone);
        //禁止重复秒杀
        if (insertCount > 0) {
            int updateCount = 0;
            //获得锁
            if (LockUtil.lock(seckillId)) {
                updateCount = seckillProductDAO.reduceNumber(seckillId, nowTime);
                //失去锁
                LockUtil.unLock(seckillId);
            } else {
                doSeckill(seckillId, userPhone, md5);
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
        } else {
            successKilledDAO.updateBySeckillIdIdAndUserPhone(seckillId,userPhone);
        }
    }

    @Override
    public Byte searchSeckillResult(String seckillId, String phone) {
        SuccessKilled successKilled = successKilledDAO.selectBySeckillIdIdAndUserPhone(seckillId,phone);
        return successKilled.getState();
    }

    private String getSaltMD5(String seckillId) {
        String base = seckillId + "/" + "wangpengpengasdas54d57asd754as";
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


}

