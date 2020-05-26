package com.fullstackboy.rabbitmqdemo.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println("接收到的消息为：" + message);
        String result = (String) rabbitTemplate.convertSendAndReceive(QueueConstants.TOPIC_QUEUE1, message);
        System.out.println("收到RPCServer返回的消息为：" + result);
        return result;
    }
}
