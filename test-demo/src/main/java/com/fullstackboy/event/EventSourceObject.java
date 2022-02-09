package com.fullstackboy.event;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Java事件机制包含三部分：事件、事件监听器、事件源
 *
 * 事件源：事件发生的地方
 * 由于事件源的某项属性或状态发生了改变（比如BUTTON被单击、TEXTBOX的值发生了改变等）导致某项事件发生。
 * 事件监听器要注册在事件源上，所以事件源类中应该要有盛放监听器的容器（List，Set等）
 * @author Liuyongfei
 * @date 2022/2/9 12:43
 */
public class EventSourceObject {
    private String name;

    /**
     * 盛放监听器的容器
     */
    private Set<CusEventListener> listener;

    public EventSourceObject() {
        this.listener = new HashSet<CusEventListener>();
        this.name = "defaultName";
    }

    /**
     * 给事件源注册监听器
     * @param listener
     */
    public void addCusListener(CusEventListener listener) {
        this.listener.add(listener);
    }

    /**
     * 事件发生时，通知注册在该事件源上的所有监听器做出相应的返佣（调用回调方法）
     */
    protected void notifies() {
        CusEventListener cel = null;
        Iterator<CusEventListener> iterator = this.listener.iterator();
        while (iterator.hasNext()) {
            cel = iterator.next();
            cel.fireCusEvent(new CusEvent(this));
        }
    }


    public String getName() {
        return name;
    }

    /**
     * 模拟事件触发器
     * 当成员变量name的值发生变化时，触发事件。
     * @param name
     */
    public void setName(String name) {
        if (!this.name.equals(name)) {
            this.name = name;
            notifies();
        }
    }
}
