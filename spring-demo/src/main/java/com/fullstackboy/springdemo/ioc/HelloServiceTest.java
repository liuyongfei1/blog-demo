package com.fullstackboy.springdemo.ioc;

import com.fullstackboy.springdemo.ioc.service.HelloService;
import com.fullstackboy.springdemo.ioc.service.Red;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceTest {
 
    @RountingInjected(value = "hello2ServiceImpl")
    private HelloService helloService;
 
    public void testSayHello() {
        helloService.sayHello();
    }
 
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext("com.fullstackboy.springdemo.ioc");
//        HelloServiceTest helloServiceTest = applicationContext.getBean(HelloServiceTest.class);
//
//        Red bean = applicationContext.getBean(Red.class);
//
//        helloServiceTest.testSayHello();


        // 1.创建一个AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 2.设置需要激活的环境
        context.getEnvironment().setActiveProfiles("test","dev");


    }
}