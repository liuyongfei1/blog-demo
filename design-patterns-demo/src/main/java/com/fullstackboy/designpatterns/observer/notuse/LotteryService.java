package com.fullstackboy.designpatterns.observer.notuse;

/**
 * 摇号业务接口
 *
 * @author Liuyongfei
 * @date 2022/3/4 16:56
 */
public interface LotteryService {

    /**
     * 定义 执行摇号业务 的方法
     * @param uId 用户id
     * @return 结果
     */
    LotteryResult doDraw(String uId);
}
