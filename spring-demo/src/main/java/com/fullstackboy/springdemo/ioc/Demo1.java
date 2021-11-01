package com.fullstackboy.springdemo.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 使用Spring IOC
 *
 * @author Liuyongfei
 * @date 2021/10/31 17:12
 */
public class Demo1 {


    @Test
    public void testUser() {
        // 1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2.获取配置创建的对象
        User user = context.getBean("user", User.class);


        System.out.println(user);

        user.add();
    }

    /**
     * 测试使用set方法注入属性
     */
    @Test
    public void testBook() {
        // 1.加载Spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2.获取配置创建的对象
        Book book = context.getBean("book", Book.class);

        System.out.println(book);

        book.printBook();
    }


    /**
     * 测试使用有参构造方式注入属性
     */
    @Test
    public void testOrders() {
        // 1.加载Spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        // 2.获取配置创建的对象
        Orders orders = context.getBean("orders",Orders.class);

        System.out.println(orders);

        orders.printOrders();

    }
}
