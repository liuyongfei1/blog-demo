package com.fullstackboy.mybatis.rabbitmqdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消息消费端启动入口
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @author Liuyongfei
 * @date 2020-05-21 18:00
 */
@SpringBootApplication
@MapperScan(basePackages = "com.fullstackboy.rabbitmqdemo.mapper")
public class RabbitmqDemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemoConsumerApplication.class, args);
    }

}
