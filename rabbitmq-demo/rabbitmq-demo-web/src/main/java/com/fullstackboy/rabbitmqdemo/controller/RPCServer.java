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
    public String process(String message) throws Exception{
        int millis = (int) (Math.random() * 2 * 1000);
        String response = "";
        try {
            Thread.sleep(millis);
            // 获取结果
            response = "结果为：" + fib(4);
        } catch (InterruptedException e) {
        }
        return message + " sleep for " + millis + " ms; " + response;
    }

    /**
     * 计算斐波列其数列的第n项
     *
     * @param n
     * @return
     * @throws Exception
     */
    private static int fib(int n) throws Exception {
        if (n < 0)
            throw new Exception("参数错误，n必须大于等于0");
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }
}
