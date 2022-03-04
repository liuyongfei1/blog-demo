package com.fullstackboy.designpatterns.observer.notuse;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

/**
 * 单元测试- 未使用 观察者模式
 *
 * 这里使用logback.xml 不生效
 *
 * @author Liuyongfei
 * @date 2022/3/4 17:27
 */
@ContextConfiguration({"classpath:logback.xml"})
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test() {
        LotteryService  service = new LotteryServiceImpl();
        LotteryResult lotteryResult = service.doDraw("2765789109876");
        logger.info("测试结果：{}", JSON.toJSONString(lotteryResult));
        System.out.println("测试结果：{}" + JSON.toJSONString(lotteryResult));
    }
}
