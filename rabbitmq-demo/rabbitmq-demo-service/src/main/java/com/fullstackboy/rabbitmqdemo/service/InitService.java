package com.fullstackboy.rabbitmqdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class InitService {
    public static final int ThreadNum = 50000;
    private static int mobile = 0;

    public void generateMultiThread() {
        log.info("开始初始化线程数---");
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            for (int i = 0; i < ThreadNum; i++) {
                new Thread(new RunThread(countDownLatch)).start();
            }

            // 启动多个线程
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class RunThread implements Runnable {
        private final CountDownLatch startLatch;
        public RunThread(CountDownLatch startLatch) {
            this.startLatch = startLatch;
        }

        @Override
        public void run() {
            try {
                // 线程等待
                startLatch.await();
                mobile += 1;
                // TODO: 发送信息进入抢单队列
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


