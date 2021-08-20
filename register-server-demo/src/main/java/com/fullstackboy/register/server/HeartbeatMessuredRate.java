package com.fullstackboy.register.server;

/**
 * 心跳测量计数器
 *
 * @author Liuyongfei
 * @date 2021/8/20 09:19
 */
public class HeartbeatMessuredRate {

    /**
     * 最近1分钟的心跳次数
     */
    private long latestMinuteHeartbeatRate;

    /**
     * 最近1分钟的时间戳
     */
    private long latestMinuteTimeStamp = System.currentTimeMillis();

    /**
     * 单例实例
     */
    public static HeartbeatMessuredRate instance = new HeartbeatMessuredRate();

    /**
     * 增加最近1分钟的心跳次数
     */
    public void increment() {
        // 当前时间戳
        long currentTimestamp = System.currentTimeMillis();
        if (currentTimestamp - latestMinuteTimeStamp >  60 * 1000) {
            latestMinuteHeartbeatRate = 0L;
            latestMinuteTimeStamp = System.currentTimeMillis();
        }
        latestMinuteHeartbeatRate++;
    }

    /**
     * 获取单例实例
     * @return
     */
    public static HeartbeatMessuredRate getInstance() {
        return instance;
    }
}
