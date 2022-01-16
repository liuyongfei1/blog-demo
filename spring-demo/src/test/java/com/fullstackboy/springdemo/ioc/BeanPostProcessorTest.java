package com.fullstackboy.springdemo.ioc;

import com.fullstackboy.springdemo.ioc.bean.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试类
 *
 * @author Liuyongfei
 * @date 2022/1/16 21:52
 */
public class BeanPostProcessorTest {

    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }
}
