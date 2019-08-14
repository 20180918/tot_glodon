package com.glodon.seckilladmin.service;

import com.glodon.seckilladmin.mapper.SeckillProductDAO;
import com.glodon.seckilladmin.mapper.SuccessKilledDAO;
import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecKillProImpl implements SecKillProService {

    @Autowired
    private SeckillProductDAO seckillProduct ;

    @Autowired
    private SuccessKilledDAO successKilledProduct;

    @Override
    public List<SeckillProduct> selectMul(String productCode, String name, Integer productState) {
       return seckillProduct.selectMul(productCode,name,productState);
    }

    @Override
    public SeckillProduct findByProductCode(String productCode) {
        return seckillProduct.selectByProductCode(productCode);
    }

    @Override
    public void updataPro(SeckillProduct seckillPro) {
        System.out.print(seckillPro.toString()+"@@@@@@@@@@@@");
        seckillProduct.updateByPrimaryKeySelective(seckillPro);
    }

    @Override
    public List<SuccessKilled> select_phone(String productCode) {
        SeckillProduct seckill_Product = seckillProduct.selectByProductCode(productCode);
        Long productId = Long.parseLong(seckill_Product.getSeckillId());
        return successKilledProduct.select_phone(productId);
    }
//        @Override
//    public void updataPro(String productCode) {
//            SeckillProduct seckillPro = seckillProduct.selectByProductCode(productCode);
//            seckillProduct.updateProductByProCode(seckillPro);
//    }
}
