package com.fullstackboy.designpatterns.observer.use.event.listener;

import com.fullstackboy.designpatterns.observer.use.LotteryResult;

/**
 * MQ 事件监听器
 *
 * @author Liuyongfei
 * @date 2022/3/4 18:15
 */
public class MQEventListener implements EventListener{

    @Override
    public void doEvent(LotteryResult result) {
        System.out.println("记录用户：" + result.getuId() + "，摇号结果:" + result.getMsg());
    }
}
