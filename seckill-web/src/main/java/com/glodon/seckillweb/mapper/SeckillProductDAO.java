package com.glodon.seckillweb.mapper;

import com.glodon.seckillcommon.domain.SeckillProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * SeckillProductDAO继承基类
 */
@Repository
public interface SeckillProductDAO extends MyBatisBaseDao<SeckillProduct, String> {
    /**
     * 查询所有商品列表
     */
    List<SeckillProduct> selectAll();

    /**
     * 根据秒杀商品的id查询明细SuccessKilled对象(该对象携带了Seckill秒杀产品对象)
     */
    int reduceNumber(@Param("seckillId") String seckillId, @Param("killTime") Date killTime);
}