package com.fullstackboy.rabbitmqdemo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.fullstackboy.rabbitmqdemo.config.RabbitConfig.QUEUE_SYNC_RPC;

/**
 * RPC服务端
 *
 * @author lyf
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-05-25 22:00
 */
@Component
@RabbitListener(queues = QUEUE_SYNC_RPC)
public class RPCServer {
    @RabbitHandler
    public String process(String message) {
        int millis = (int) (Math.random() * 2 * 1000);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
        return message + " sleep for " + millis + " ms";
    }
}
