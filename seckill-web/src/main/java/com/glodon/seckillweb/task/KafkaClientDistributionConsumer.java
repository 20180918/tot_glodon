package com.glodon.seckillweb.task;

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
//    @KafkaListener(topics = {"clientdistribution"})
//    public void receiveMessage(String message){
//        System.out.println("clientdistribution:"+message);
//        //收到通道的消息之后执行秒杀操作
//    }
}
