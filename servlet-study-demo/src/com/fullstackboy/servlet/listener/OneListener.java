package com.fullstackboy.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 利用ServletContextListener接口来监听全局作用域对象
 *
 * @author Liuyongfei
 * @date 2021/11/12 08:44
 */
public class OneListener implements ServletContextListener {

    /**
     * 全局作用域对象被创建的时候执行
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("全局作用域对象被创建了。。。");
    }

    /**
     * 全局作用域对象被销毁的时候执行
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("全局作用域对象被销毁了。。。");

    }
}
