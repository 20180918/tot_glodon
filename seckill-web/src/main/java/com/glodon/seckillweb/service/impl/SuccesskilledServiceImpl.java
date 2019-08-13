package com.glodon.seckillweb.service.impl;

import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
import com.glodon.seckillweb.mapper.SeckillProductDAO;
import com.glodon.seckillweb.mapper.SuccessKilledDAO;
import com.glodon.seckillweb.service.SuccesskilledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 * 秒杀结果服务
 *
 * @author wangpp-b
 * @create 2019-08-13 9:15
 */
@Service
public class SuccesskilledServiceImpl implements SuccesskilledService {
    @Autowired
    private SuccessKilledDAO successKilledDAO;
    @Autowired
    private SeckillProductDAO seckillProductDAO;

    /**
     * 查询秒杀结果
     * @param phone
     * @return
     */
    @Override
    public List<SuccessKilled> getSuccessSeckillList(String phone) {
        return successKilledDAO.selectByPhone(phone);
    }
}
