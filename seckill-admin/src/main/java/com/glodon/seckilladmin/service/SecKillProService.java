package com.glodon.seckilladmin.service;

import com.glodon.seckillcommon.domain.SeckillProduct;

import java.util.List;

public interface SecKillProService {

    public List<SeckillProduct> selectMul(String productCode,String name,Integer productState );
    // public List<SeckillProduct> selectByProductCode(String productCode);
    //根据productCode找到该条纪录
    public  SeckillProduct findByProductCode(String productCode);
    //编辑商品
    public void updataPro(SeckillProduct seckillProduct);
    //public void updataPro(String productCode);
    //查询获得者手机号
    public List<SuccessKilled> select_phone(String productId);
    //批量上架
    public void updateBatch(List<String> param);

    //下架
    public void updateBatchDown(List<String> param);
}
