package com.fullstackboy.designpatterns.observer.use;

import com.fullstackboy.designpatterns.observer.use.event.EventManager;
import com.fullstackboy.designpatterns.observer.use.event.listener.MQEventListener;
import com.fullstackboy.designpatterns.observer.use.event.listener.MessageEventListener;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/3/4 18:29
 */
public abstract class LotteryService {
    private EventManager eventManager;

    public LotteryService() {
        eventManager = new EventManager(EventManager.EventType.MQ, EventManager.EventType.Message);
        eventManager.subscribe(EventManager.EventType.MQ, new MQEventListener());
        eventManager.subscribe(EventManager.EventType.Message, new MessageEventListener());
    }

    public LotteryResult draw(String uId) {
        LotteryResult result = doDraw(uId);

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
