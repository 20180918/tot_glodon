package com.glodon.seckillweb.service.impl;

import com.glodon.seckillcommon.Utils.RedisUtil;
import com.glodon.seckillcommon.Utils.SerializationUtil;
import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillweb.dto.UrlExposer;
import com.glodon.seckillweb.mapper.SeckillProductDAO;
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

    @Override
    public List<SeckillProduct> getSeckillList() {
        return seckillProductDAO.selectAll();
    }

    @Override
    public SeckillProduct selectBySeckillId(String seckillId, boolean fastsearch) {
        if (fastsearch){
            String key = "product_detail" + seckillId;
            String redisObject= RedisUtil.get(key);
            if (redisObject == null) {
                SeckillProduct seckillProduct = seckillProductDAO.selectByPrimaryKey(seckillId);
                RedisUtil.set(key.getBytes(), SerializationUtil.serialize(seckillProduct));
                return seckillProduct;
            } else {
                return (SeckillProduct) SerializationUtil.deserialize(RedisUtil.get(key.getBytes()));
            }
        }else {
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

        SeckillProduct seckillProduct = selectBySeckillId(seckillId,true);
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

    private String getSaltMD5(String seckillId) {
        String base = seckillId + "/" + "wangpengpengasdas54d57asd754as";
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}

