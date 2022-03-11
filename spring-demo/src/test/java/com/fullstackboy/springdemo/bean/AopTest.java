package com.fullstackboy.springdemo.bean;

import com.fullstackboy.springdemo.aop.bean.Calculation;
import com.fullstackboy.springdemo.aop.bean.User;
import com.fullstackboy.springdemo.aop.config.MainConfigOfAop;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * 切面功能测试类
 *
 * @author Liuyongfei
 * @date 2022/1/14 22:37
 */
public class AopTest {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAop.class);
//        Calculation calculation = context.getBean(Calculation.class);
//        calculation.div(1,1);


        ClassPathXmlApplicationContext context2 =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        User bean = context2.getBean(User.class);

        System.out.println(bean.getMyName());
    }
}
