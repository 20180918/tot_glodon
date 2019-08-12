package com.glodon.seckillcommon.mapper;

import com.glodon.seckillcommon.domain.SeckillProduct;
import org.springframework.stereotype.Repository;

/**
 * SeckillProductDAO继承基类
 */
@Repository
public interface SeckillProductDAO extends MyBatisBaseDao<SeckillProduct, String> {
}