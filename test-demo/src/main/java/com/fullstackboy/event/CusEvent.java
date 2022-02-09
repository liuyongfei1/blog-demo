package com.fullstackboy.event;

import java.util.EventObject;

/**
 * Java事件机制包含三部分：事件、事件监听器、事件源
 *
 *
 * 事件类，用于封装事件源以及一些与事件相关的参数
 * @author Liuyongfei
 * @date 2022/2/9 12:38
 */
public class CusEvent extends EventObject {

    /**
     * 事件源
     */
    private Object source;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CusEvent(Object source) {
        super(source);
        this.source = source;
    }

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
