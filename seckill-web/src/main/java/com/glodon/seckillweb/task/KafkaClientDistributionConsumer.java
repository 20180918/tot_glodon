package com.glodon.seckillweb.task;

import com.alibaba.fastjson.JSON;
import com.glodon.seckillcommon.exception.ClosedSeckillException;
import com.glodon.seckillcommon.exception.RepeatSeckillException;
import com.glodon.seckillweb.dto.SeckillExecution;
import com.glodon.seckillweb.dto.SeckillInfoContent;
import com.glodon.seckillweb.dto.SeckillResult;
import com.glodon.seckillweb.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * kafka请求分发topic消费者
 *
 * @author wangpp-b
 * @create 2019-08-13 14:49
 */
@Component
public class KafkaClientDistributionConsumer {

    @Autowired
    private SeckillService seckillService;

    @KafkaListener(topics = {"clientdistribution"})
    public void receiveMessage(String message){
        //收到通道的消息之后执行秒杀操作
        SeckillInfoContent seckillInfoContent = JSON.parseObject(message, SeckillInfoContent.class);
        String userPhone = seckillInfoContent.getUserPhone();
        Long seckillId = seckillInfoContent.getSeckillId();
        seckillService.doSeckill(String.valueOf(seckillId), String.valueOf(userPhone), "");
    }
}
