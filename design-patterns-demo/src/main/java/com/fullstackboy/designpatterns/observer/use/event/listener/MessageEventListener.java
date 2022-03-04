package com.fullstackboy.designpatterns.observer.use.event.listener;

import com.fullstackboy.designpatterns.observer.use.LotteryResult;

/**
 * 短信 事件监听器
 *
 * @author Liuyongfei
 * @date 2022/3/4 18:17
 */
public class MessageEventListener implements EventListener{
    @Override
    public void doEvent(LotteryResult result) {
        System.out.println("给用户：" + result.getuId() + "，发送短信通知(短信):" + result.getMsg());
    }
}
