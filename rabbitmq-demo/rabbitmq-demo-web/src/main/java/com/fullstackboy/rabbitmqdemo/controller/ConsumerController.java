package com.fullstackboy.rabbitmqdemo.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 消息消费端
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @author lyf
 * @date 2020-05-17 18:00
 */
@Component
@RabbitListener(queues = {QueueConstants.QUEUE_NAME})
public class ConsumerController {

    @RabbitHandler
    public void handler(Map message) throws IOException {
        System.out.println("收到消息：" + message.toString());
    }
}
