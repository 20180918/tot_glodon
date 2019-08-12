package com.glodon.seckillweb.service.impl;

import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillweb.dto.UrlExposer;
import com.glodon.seckillweb.mapper.SeckillProductDAO;
import com.glodon.seckillweb.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SeckillProduct selectBySeckillId(String seckillId) {
        return null;
    }

    /**
     * @param seckillId
     * @return
     */
    @Override
    public UrlExposer exportSeckillUrl(String seckillId) {
        return null;
    }
}
