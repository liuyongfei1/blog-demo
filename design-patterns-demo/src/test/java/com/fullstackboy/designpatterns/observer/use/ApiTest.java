package com.fullstackboy.designpatterns.observer.use;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test() {
        LotteryService  service = new LotteryServiceImpl();
        LotteryResult lotteryResult = service.draw("2765789109876");
        logger.info("测试结果：{}", JSON.toJSONString(lotteryResult));
        System.out.println("测试结果1：{}" + JSON.toJSONString(lotteryResult));
    }
}