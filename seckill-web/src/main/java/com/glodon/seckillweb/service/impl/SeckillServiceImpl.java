package com.glodon.seckillweb.service.impl;

import com.glodon.seckillcommon.Utils.RedisUtil;
import com.glodon.seckillcommon.Utils.SerializationUtil;
import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
import com.glodon.seckillcommon.exception.ClosedSeckillException;
import com.glodon.seckillcommon.exception.RepeatSeckillException;
import com.glodon.seckillweb.dto.SeckillExecution;
import com.glodon.seckillweb.dto.UrlExposer;
import com.glodon.seckillweb.mapper.SeckillProductDAO;
import com.glodon.seckillweb.mapper.SuccessKilledDAO;
import com.glodon.seckillweb.service.SeckillService;
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

    @Override
    public List<SeckillProduct> getSeckillList() {
        return seckillProductDAO.selectAll();
    }


    @Override
    public SeckillProduct selectBySeckillId(String seckillId, boolean fastsearch) {
        if (fastsearch) {
            String key = "product_detail" + seckillId;
            String redisObject = RedisUtil.get(key);
            if (redisObject == null) {
                SeckillProduct seckillProduct = seckillProductDAO.selectByPrimaryKey(seckillId);
                RedisUtil.set(key.getBytes(), SerializationUtil.serialize(seckillProduct));
                return seckillProduct;
            } else {
                return (SeckillProduct) SerializationUtil.deserialize(RedisUtil.get(key.getBytes()));
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
    public SeckillExecution doSeckill(String seckillId, String userPhone, String md5) {
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
                //减库存,热点商品竞争
                int updateCount = seckillProductDAO.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0) {
                    //没有更新库存记录，说明秒杀结束 rollback
                    throw new ClosedSeckillException("seckill is closed");
                } else {
                    //秒杀成功,得到成功插入的明细记录,并返回成功秒杀的信息 commit
                    SuccessKilled successKilled = successKilledDAO.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(Long.parseLong(seckillId),200);
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

