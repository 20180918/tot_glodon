package com.glodon.seckillweb.service;

import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillweb.dto.UrlExposer;

import java.util.List;

/**
 * 秒杀服务接口
 *
 * @author wupx
 */
public interface SeckillService {

    /**
     * 获取秒杀列表
     *
     * @return
     */
    List<SeckillProduct> getSeckillList();

    /**
     * 根据商品id去查询商品
     */
    SeckillProduct selectBySeckillId(String seckillId,boolean ifFastSearch);

    /**
     * 暴露url地址
     *
     * @param seckillId
     * @return
     */
    UrlExposer exportSeckillUrl(String seckillId);

    /**
     * 执行秒杀
     *
     * @param seckillId
     * @return
     */
    void doSeckill(String seckillId, String userPhone, String md5);

    /**
     * 查询秒杀结果
     * @param seckillId
     * @param phone
     * @return
     */
    Byte searchSeckillResult(String seckillId, String phone);
}
