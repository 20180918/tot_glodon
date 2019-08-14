package com.glodon.seckillweb.task;

import com.alibaba.fastjson.JSON;
import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
import com.glodon.seckillweb.dto.SeckillInfoContent;
import com.glodon.seckillweb.mapper.SeckillProductDAO;
import com.glodon.seckillweb.mapper.SuccessKilledDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * kafka异步生成订单topic消费者
 *
 * @author wangpp-b
 * @create 2019-08-13 14:49
 */
@Component
public class KafkaGenerateOrderConsumer {

    @Autowired
    private SeckillProductDAO seckillProductDAO;

    @Autowired
    private SuccessKilledDAO successKilledDAO;

    @KafkaListener(topics = {"generateorder"})
    public void receiveMessage(String message){
        SeckillInfoContent seckillInfoContent = JSON.parseObject(message, SeckillInfoContent.class);
        SeckillProduct seckillProduct= seckillProductDAO.selectByPrimaryKey(String.valueOf(seckillInfoContent.getSeckillId()));
        SuccessKilled successKilled = new SuccessKilled();
        successKilled.setProductName(seckillProduct.getName());
        successKilled.setSeckillPrice(seckillProduct.getSeckillPrice());
        successKilled.setState(seckillInfoContent.getState());
        successKilled.setUserPhone(Long.parseLong(seckillInfoContent.getUserPhone()));
        successKilled.setSeckillId(seckillInfoContent.getSeckillId());
        successKilledDAO.updateByPrimaryKey(successKilled);
    }
}
