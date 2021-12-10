package com.fullstackboy.mybatis.rabbitmqdemo.service;

import com.fullstackboy.mybatis.rabbitmqdemo.common.QueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 发送抢单信息进入队列
 *
 * @Author Liuyongfei
 * @Date 上午9:00 2020/6/1
 **/
@Slf4j
@Service
public class CommonMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendRobbingMsg(String mobile) {
        String message = mobile;
        // 生成消息的唯一id
        String msgId = UUID.randomUUID().toString();
        try {
            rabbitTemplate.convertAndSend(QueueConstants.QUEUE_EXCHANGE_NAME,QueueConstants.QUEUE_ROUTING_KEY_NAME,
                    message, new CorrelationData(msgId));
        } catch (AmqpException e) {
            log.error("发送抢单信息进入队列时发生异常，mobile: [{}]",mobile);
        }
    }
}
