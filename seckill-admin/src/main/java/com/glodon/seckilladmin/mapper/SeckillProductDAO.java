package com.glodon.seckilladmin.mapper;

import com.glodon.seckillcommon.domain.SeckillProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * SeckillProductDAO继承基类
 */
@Repository
public interface SeckillProductDAO extends MyBatisBaseDao<SeckillProduct, String> {
    public List<SeckillProduct> selectMul(String productCode, String name, Integer productState);

    public SeckillProduct selectByProductCode(String productCode);

    public void updateBatch(@Param("list") List<String> list);
    //下架
    public void updateBatchDown(@Param("list") List<String> list);
}

