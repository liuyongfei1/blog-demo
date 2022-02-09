package com.fullstackboy.event;

import org.junit.Test;

/**
 * 测试 Java事件机制
 *
 * @author Liuyongfei
 * @date 2022/2/9 12:57
 */
public class Test1 {
    
    @Test
    public void test() {
        EventSourceObject eventSource = new EventSourceObject();

        // 注册监听器- 第一种写法
//        eventSource.addCusListener(new CusEventListener());

        // 注册监听器- 第二种写法
        eventSource.addCusListener(new CusEventListener() {
            @Override
            public void fireCusEvent(CusEvent e) {
                System.out.println("增强...");
                super.fireCusEvent(e);
            }
        });

        // 触发事件
        eventSource.setName("张三");
    }
}
