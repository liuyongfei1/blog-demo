package com.fullstackboy.designpatterns.observer.use.event;

import com.fullstackboy.designpatterns.observer.use.LotteryResult;
import com.fullstackboy.designpatterns.observer.use.LotteryService;
import com.fullstackboy.designpatterns.observer.use.MinibusTargetService;

import java.util.Date;

/**
 * 业务接口实现类
 *
 * 将核心业务流程和 辅助流程剥离开，这里只处理 核心业务流程。
 * @author Liuyongfei
 * @date 2022/3/4 18:35
 */
public class LotteryServiceImpl extends LotteryService {

    MinibusTargetService service = new MinibusTargetService();

    @Override
    protected LotteryResult doDraw(String uId) {

        // 摇号
        String lottery = service.lottery(uId);
        // 结果
        return new LotteryResult(uId, lottery, new Date());
    }
}
