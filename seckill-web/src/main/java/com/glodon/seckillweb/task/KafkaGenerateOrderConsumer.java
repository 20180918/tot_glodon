package com.glodon.seckillweb.task;

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
//    @KafkaListener(topics = {"generateorder"})
    public void receiveMessage(String message){
        System.out.println(message);
        //收到通道的消息之后执行秒杀操作
    }
}
