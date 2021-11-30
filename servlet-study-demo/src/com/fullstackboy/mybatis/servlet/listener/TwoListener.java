package com.fullstackboy.mybatis.servlet.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * 利用ServletContextAttributeListener来监听全局作用域对象共享数据发生变化
 *
 * 比如：添加，更新，删除
 *
 * @author Liuyongfei
 * @date 2021/11/12 09:01
 */
public class TwoListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("新增了共享数据......");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("删除了共享数据......");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
        System.out.println("更新了共享数据......");
    }
}
