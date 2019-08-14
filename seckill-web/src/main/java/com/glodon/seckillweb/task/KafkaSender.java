package com.glodon.seckillweb.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * kafka发送消息
 *
 * @author wangpp-b
 * @create 2019-08-13 14:47
 */
@Component
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * 发送消息到kafka
     */
    public void sendChannelMess(String channel, String message){
        kafkaTemplate.send(channel,message);
    }
}
