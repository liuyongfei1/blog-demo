package com.fullstackboy.event;

import java.util.EventListener;

/**
 * Java事件机制包含三部分：事件、事件监听器、事件源
 *
 * 事件监听器，这里定义回调方法，将你想做的事情放到这个方法下。
 * 事件源发生响应事件时会调用这个方法
 * @author Liuyongfei
 * @date 2022/2/9 12:41
 */
public class CusEventListener implements EventListener {

    /**
     * 事件发生后的回调方法
     * @param e
     */
    public void fireCusEvent(CusEvent e){
        EventSourceObject eObject = (EventSourceObject)e.getSource();
        System.out.println("My name has been changed!");
        System.out.println("I got a new name,named \"" + eObject.getName()+"\"");
    }
}
