package com.glodon.seckillweb.task;

import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
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
//    @KafkaListener(topics = {"generateorder"})
    public void receiveMessage(String message){
        String message2="{\"md5\":\"dad189d2d14f4df6b551d5a264771bfd\",\"seckillId\":1001,\"state\":-1,\"userPhone\":\"18603203843\"}";
//        VO vo = JSON.parseObject(message, VO.class);
        System.out.println("generateorder:"+message);
        String seckillId=null;
        String userPhone=null;
        Byte status =null;
        SeckillProduct seckillProduct= seckillProductDAO.selectByPrimaryKey(seckillId);
        SuccessKilled successKilled = new SuccessKilled();
        successKilled.setProductName(seckillProduct.getName());
        successKilled.setSeckillPrice(seckillProduct.getSeckillPrice());
        successKilled.setState(status);
        successKilled.setUserPhone(Long.parseLong(userPhone));
        successKilled.setSeckillId(Long.parseLong(seckillId));
        successKilledDAO.updateByPrimaryKey(successKilled);
        //收到通道的消息之后执行秒杀操作
    }
}
