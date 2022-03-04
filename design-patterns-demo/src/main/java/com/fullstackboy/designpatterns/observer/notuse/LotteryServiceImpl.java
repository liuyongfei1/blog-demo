package com.fullstackboy.designpatterns.observer.notuse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 摇号业务实现类
 *
 * @author Liuyongfei
 * @date 2022/3/4 16:59
 */
public class LotteryServiceImpl implements LotteryService{

    private Logger logger = LoggerFactory.getLogger(LotteryServiceImpl.class);

    private MinibusTargetService service = new MinibusTargetService();

    /**
     * 定义 执行摇号业务 的方法
     *
     * @param uId 用户id
     * @return 结果
     */
    public LotteryResult doDraw(String uId) {

        // 摇号
        String lottery = service.lottery(uId);

        // 发短信
        logger.info("给用户 {} 发送短信通知(短信)： {}", uId, lottery);

        // 发MQ消息
        logger.info("记录用户 {} 摇号结果(MQ)： {}", uId, lottery);

        // 返回结果
        return new LotteryResult(uId, lottery, new Date());
    }
}
