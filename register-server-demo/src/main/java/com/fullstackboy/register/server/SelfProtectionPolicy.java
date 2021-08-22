package com.fullstackboy.register.server;

/**
 * 自我保护机制
 *
 * @author Liuyongfei
 * @date 2021/8/21 10:39
 */
public class SelfProtectionPolicy {

    /**
     * 单例实例
     */
    private static final SelfProtectionPolicy instance = new SelfProtectionPolicy();

    private SelfProtectionPolicy() {}


    /**
     * 期望的心跳次数：
     * 比如有10个实例，这个数值就是 10 * 2 = 20次
     */
    private long expectedHeartbeatRate;

    /**
     * 期望的心跳次数的阈值：
     * 比如有10个实例，这个数值就是 10 * 2 * 0.85 = 17
     */
    private long expectedHeartbeatThreshold;

    /**
     * 是否需要开启自我保护机制
     * @return true：开启自我保护机制
     */
    public Boolean enabled() {
        HeartbeatCounter rate = HeartbeatCounter.getInstance();
        // 获取最近1分钟的心跳次数
        long latestMinuteHeartbeatRate = rate.getLatestMinuteHeartbeatRate();

        // 如果最近1分钟的心跳次数 < 期望的心跳次数阈值
        if (latestMinuteHeartbeatRate < expectedHeartbeatThreshold) {
            // 需要开启自我保护机制
            System.out.println("自我保护机制开启，最近1分钟心跳次数：【" + latestMinuteHeartbeatRate + "】，期望心跳次数：【" + expectedHeartbeatThreshold + "】");
            return true;
        }

        System.out.println("自我保护机制未开启，最近1分钟心跳次数：【" + latestMinuteHeartbeatRate + "】，期望心跳次数：【" + expectedHeartbeatThreshold + "】");
        return false;
    }

    public long getExpectedHeartbeatRate() {
        return expectedHeartbeatRate;
    }

    public void setExpectedHeartbeatRate(long expectedHeartbeatRate) {
        this.expectedHeartbeatRate = expectedHeartbeatRate;
    }

    public long getExpectedHeartbeatThreshold() {
        return expectedHeartbeatThreshold;
    }

    public void setExpectedHeartbeatThreshold(long expectedHeartbeatThreshold) {
        this.expectedHeartbeatThreshold = expectedHeartbeatThreshold;
    }

    /**
     * 获取自我保护机制单例
     * @return 单例
     */
    public static SelfProtectionPolicy getInstance() {
        return instance;
    }
}
