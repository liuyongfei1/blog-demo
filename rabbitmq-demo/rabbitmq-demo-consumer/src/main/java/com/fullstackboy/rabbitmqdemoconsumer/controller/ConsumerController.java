package com.fullstackboy.rabbitmqdemoconsumer.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 消息消费端（RPC服务端）
 *
 * @author Liuyongfei
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-05-24 10:30
 */
@Slf4j
@Component
public class ConsumerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = {QueueConstants.TOPIC_QUEUE1})
    public void handler(Message msg, Channel channel) throws IOException {
        log.info("队列1:" + msg.toString());
        String msgBody = new String(msg.getBody());
        //数据处理，返回的Message
        Message repMsg = con(msgBody + "返回了", msg.getMessageProperties().getCorrelationId());

        rabbitTemplate.send(QueueConstants.TOPIC_EXCHANGE, QueueConstants.TOPIC_QUEUE2, repMsg);
    }

    @RabbitListener(queues = QueueConstants.TOPIC_QUEUE2)
    public void receiveTopic2(Message msg) {
        log.info("队列2:" + msg.toString());

    }

    public Message con(String s, String id) {
        MessageProperties mp = new MessageProperties();
        byte[] src = s.getBytes(Charset.forName("UTF-8"));
        mp.setContentType("application/json");
        mp.setContentEncoding("UTF-8");
        mp.setCorrelationId(id);

        return new Message(src, mp);
    }
}
