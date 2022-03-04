package com.fullstackboy.designpatterns.observer.use.event.listener;

import com.fullstackboy.designpatterns.observer.use.LotteryResult;

public interface EventListener {

    void doEvent(LotteryResult result);
}
