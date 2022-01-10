package com.fullstackboy.springdemo.ioc.config;

import com.fullstackboy.springdemo.ioc.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * 通过使用@Bean注解，返回一个想要注入的对象
 * @author Liuyongfei
 * @date 2022/1/10 23:15
 */
@Configuration
public class AppConfig {

    @Bean("student")
    public Student student() {
        return new Student("zhangsan", 30);
    }
}
