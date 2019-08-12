package com.glodon.seckillweb.mapper;

import com.glodon.seckillcommon.domain.SeckillProduct;
import org.springframework.stereotype.Repository;

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
}