package com.fullstackboy.rabbitmqdemoconsumer.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息消费端
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @author Liuyongfei
 * @date 2020-05-21 18:00
 */
@Component
public class ConsumerController {

    @RabbitListener(queues = {QueueConstants.QUEUE_NAME},containerFactory = "customContainerFactory")
    public void handler(Message message, Channel channel) throws IOException {
        System.out.println("监听到抢单手机号：" + message.toString());
    }
}
