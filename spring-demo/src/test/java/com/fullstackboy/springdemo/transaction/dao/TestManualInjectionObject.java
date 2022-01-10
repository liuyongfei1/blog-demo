package com.fullstackboy.springdemo.transaction.dao;

import com.fullstackboy.springdemo.ioc.bean.Student;
import com.fullstackboy.springdemo.ioc.config.AppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试手动注入对象的几种实现方式
 *
 * @author Liuyongfei
 * @date 2022/1/10 11:13
 */
public class TestManualInjectionObject {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Student myStudent = context.getBean("student", Student.class);
        System.out.println(myStudent.toString());

    }
}
