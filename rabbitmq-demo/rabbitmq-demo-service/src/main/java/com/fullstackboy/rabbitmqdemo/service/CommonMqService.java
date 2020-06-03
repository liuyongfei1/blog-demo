package com.fullstackboy.rabbitmqdemo.service;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        try {
            rabbitTemplate.convertAndSend(QueueConstants.QUEUE_EXCHANGE_NAME,QueueConstants.QUEUE_ROUTING_KEY_NAME,
                    message);
        } catch (AmqpException e) {
            log.error("发送抢单信息进入队列时发生异常，mobile: [{}]",mobile);
        }
    }
}
