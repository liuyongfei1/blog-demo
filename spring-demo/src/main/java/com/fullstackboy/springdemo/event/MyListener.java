package com.fullstackboy.springdemo.event;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 自定义事件监听器
 *
 * @author Liuyongfei
 * @date 2022/3/13 17:01
 */
public class MyListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof MyEvent) {
            ((MyEvent) applicationEvent).event();
        }
    }
}
