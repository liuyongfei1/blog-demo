package com.fullstackboy.designpatterns.observer.use;

import com.fullstackboy.designpatterns.observer.use.event.EventManager;
import com.fullstackboy.designpatterns.observer.use.event.listener.MQEventListener;
import com.fullstackboy.designpatterns.observer.use.event.listener.MessageEventListener;

/**
 * 业务处理抽象类
 *
 * @author Liuyongfei
 * @date 2022/3/4 18:29
 */
public abstract class LotteryService {
    private EventManager eventManager;

    public LotteryService() {
        eventManager = new EventManager(EventManager.EventType.MQ, EventManager.EventType.Message);
        // 订阅事件
        eventManager.subscribe(EventManager.EventType.MQ, new MQEventListener());
        eventManager.subscribe(EventManager.EventType.Message, new MessageEventListener());
    }

    public LotteryResult draw(String uId) {
        LotteryResult result = doDraw(uId);

        // 处理完业务逻辑后，需要将处理结果发短信通知或放入MQ队列
        // 这时可以调用 notify方法
        // 需要什么通知，就给调用什么方法
        eventManager.notify(EventManager.EventType.MQ, result);
        eventManager.notify(EventManager.EventType.Message, result);

        return result;
    }

    /**
     * 定义抽象方法，交给具体的业务类去实现
     * @param uId 用户id
     * @return 结果
     */
    protected abstract LotteryResult doDraw(String uId);
}
