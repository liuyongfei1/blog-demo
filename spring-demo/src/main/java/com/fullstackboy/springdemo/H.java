package com.fullstackboy.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 内部类的实例化现象
 *
 * @author Liuyongfei
 * @date 2022/1/8 20:47
 */
public class H {

    class J {

    }

    public static void main(String[] args) {
        // 实例化spring容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 将J放到spring容器中，让spring容器去实例化J
        context.register(J.class);

        // 初始化spring容器
        context.refresh();
        context.getBean(J.class);
    }
}
