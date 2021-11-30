package com.fullstackboy.mybatis.rabbitmqdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class InitService {
    public static final int ThreadNum = 1000;

    @Autowired
    private CommonMqService commonMqService;

    public void generateMultiThread() {
        log.info("开始初始化线程数---");
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            for (int i = 0; i < ThreadNum; i++) {
                new Thread(new RunThread(countDownLatch, i)).start();
            }

            // 启动多个线程
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class RunThread implements Runnable {
        private final CountDownLatch startLatch;
        private int mobile = 185188;

        public RunThread(CountDownLatch startLatch, int i) {
            this.startLatch = startLatch;
            this.mobile += i;
        }

        @Override
        public void run() {
            try {
                // 线程等待
                startLatch.await();

                // 发送信息进入抢单队列
                log.info("用户的手机号为：[{}]", mobile);
                commonMqService.sendRobbingMsg(String.valueOf(mobile));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


