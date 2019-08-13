package com.glodon.seckilladmin.service;

import com.glodon.seckilladmin.mapper.SeckillProductDAO;
import com.glodon.seckillcommon.domain.SeckillProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecKillProImpl implements SecKillProService {

    @Autowired
    private SeckillProductDAO seckillProduct ;

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
//        @Override
//    public void updataPro(String productCode) {
//            SeckillProduct seckillPro = seckillProduct.selectByProductCode(productCode);
//            seckillProduct.updateProductByProCode(seckillPro);
//    }
}
