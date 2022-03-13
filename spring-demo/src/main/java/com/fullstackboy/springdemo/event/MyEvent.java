package com.fullstackboy.springdemo.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义一个事件类
 *
 * @author Liuyongfei
 * @date 2022/3/13 16:59
 */
public class MyEvent extends ApplicationEvent {

    public MyEvent(Object source) {
        super(source);
    }

    public void event() {
        System.out.println("自定义事件逻辑");
    }
}

