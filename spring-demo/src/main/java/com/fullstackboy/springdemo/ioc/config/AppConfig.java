package com.fullstackboy.springdemo.ioc.config;

import com.fullstackboy.springdemo.ioc.bean.Student;
import com.fullstackboy.springdemo.ioc.service.StudentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置类
 * 1、通过使用@Bean注解，返回一个想要注入的对象
 * @author Liuyongfei
 * @date 2022/1/10 23:15
 */
//@Configuration
//public class AppConfig {
//
//    @Bean("student")
//    public Student student() {
//        return new Student("zhangsan", 30);
//    }
//}

///**
// * 配置类
// * 2、通过使用FactoryBean
// * @author Liuyongfei
// * @date 2022/1/11 07:06
// */
//@ComponentScan("com.*")
//public class AppConfig {
//
//}

/**
 * 配置类
 * 3、通过使用@Import注解
 * @author Liuyongfei
 * @date 2022/1/11 07:06
 */
@Configuration
@Import(value={StudentServiceImpl.class})
public class AppConfig {

//    @Bean
//    public Student student() {
//        return new Student("wangwu", 50);
//    }
}