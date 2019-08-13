package com.glodon.seckilladmin.mapper;

import com.glodon.seckillcommon.domain.SeckillProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SeckillProductDAO继承基类
 */
@Repository
public interface SeckillProductDAO extends MyBatisBaseDao<SeckillProduct, String> {
    public List<SeckillProduct> selectMul(String productCode, String name, Integer productState);
    public SeckillProduct selectByProductCode(String productCode);
}