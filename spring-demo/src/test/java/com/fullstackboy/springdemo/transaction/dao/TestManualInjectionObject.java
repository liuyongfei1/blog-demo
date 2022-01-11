package com.fullstackboy.springdemo.transaction.dao;

import com.fullstackboy.springdemo.ioc.bean.Student;
import com.fullstackboy.springdemo.ioc.bean.Teacher;
import com.fullstackboy.springdemo.ioc.config.AppConfig;
import com.fullstackboy.springdemo.ioc.service.StudentService;
import com.fullstackboy.springdemo.ioc.service.StudentServiceImpl;
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
        // 第一种：通过使用@Bean注解
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Student myStudent = context.getBean("student", Student.class);
//        System.out.println(myStudent.toString());

//        // 第二种：通过使用FactoryBean
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        // 这个bean的名字是studentFactoryBean，但返回的是Student类
//        Student myStudent = context.getBean("studentFactoryBean", Student.class);
//        System.out.println(myStudent.toString());

        // 第三种：通过@Import注解
        // 这样将类注入的方式有个问题就是没法注入参数。也就是说UserServiceImpl提供的应该是无参的构造方法。
        // 这种方式注入类在Spring内部用的并不多。
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService studentService = context.getBean(StudentServiceImpl.class);
        System.out.println(studentService.save(null));

    }
}
