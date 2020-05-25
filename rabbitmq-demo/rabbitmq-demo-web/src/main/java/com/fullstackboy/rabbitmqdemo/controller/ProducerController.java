package com.fullstackboy.rabbitmqdemo.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 消息生产端
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @author lyf
 * @date 2020-05-17 18:30
 */
@RestController
public class ProducerController {

    /**
     * RabbitTemplate提供了发送/接收消息的方法
     */
    @Autowired
    RabbitTemplate rabbitTemplate;


    /**
     * 生产消息
     *
     * @Author Liuyongfei
     * @Date 上午12:12 2020/5/20 
     * @return java.lang.String
     **/
    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        // 生成消息的唯一id
        String msgId = UUID.randomUUID().toString();
        String messageData = "hello,this is rabbitmq demo message";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // 定义要发送的消息对象
        Map<String,Object> messageObj = new HashMap<>();
        messageObj.put("msgId",msgId);
        messageObj.put("messageData",messageData);
        messageObj.put("createTime",createTime);

        rabbitTemplate.convertAndSend(QueueConstants.QUEUE_EXCHANGE_NAME,QueueConstants.QUEUE_ROUTING_KEY_NAME,
                messageObj,new CorrelationData(msgId));
        return "message send ok";
    }
}
