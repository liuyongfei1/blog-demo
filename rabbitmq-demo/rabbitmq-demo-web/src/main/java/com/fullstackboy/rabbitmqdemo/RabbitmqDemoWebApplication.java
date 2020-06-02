package com.fullstackboy.rabbitmqdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.fullstackboy.rabbitmqdemo.mapper")
public class RabbitmqDemoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemoWebApplication.class, args);
    }

}
