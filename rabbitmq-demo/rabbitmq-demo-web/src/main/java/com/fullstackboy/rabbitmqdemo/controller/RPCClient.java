package com.fullstackboy.rabbitmqdemo.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fullstackboy.rabbitmqdemo.config.RabbitConfig.QUEUE_SYNC_RPC;

/**
 * RPC客户端
 *
 * @author lyf
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-05-25 19:30
 */
@RestController
public class RPCClient {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public String send(String message) {
        String result = (String) rabbitTemplate.convertSendAndReceive(QUEUE_SYNC_RPC, message);
        System.out.println("收到RPC消息了：" + result);
        return result;
    }
}
