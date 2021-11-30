package com.fullstackboy.mybatis.register.server.core;

import java.util.concurrent.atomic.LongAdder;

/**
 * 心跳测量计数器
 *
 * @author Liuyongfei
 * @date 2021/8/20 09:19
 */
public class HeartbeatCounter {

    /**
     * 最近1分钟的心跳次数
     */
//    private long latestMinuteHeartbeatRate;

    /**
     * 由于AtomicLong会存在的三大问题，JDK1.8中提供了更好解决办法的LongAdder
     */
//    private AtomicLong latestMinuteHeartbeatRate = new AtomicLong(0L);
    private LongAdder latestMinuteHeartbeatRate = new LongAdder();

    /**
     * 最近1分钟的时间戳
     */
    private long latestMinuteTimeStamp = System.currentTimeMillis();

    /**
     * 单例实例
     */
    public static HeartbeatCounter instance = new HeartbeatCounter();

    /**
     * 增加最近1分钟的心跳次数
     * 这个synchronized上锁，性能是很低的。
     * 当有较多的实例，不断的发送心跳请求时，就会来增加心跳次数
     * 多线程就会卡在这里，一个一个的排队，影响性能
     * 这时，就可以使用AtomicLong原子类来进行优化
     * 换成AtomicLong类之后，不加锁，CAS操作，保证原子性，还可以多线程并发执行。
     */
    public /**synchronized*/ void increment() {
        // 当前时间戳
        long currentTimestamp = System.currentTimeMillis();
        if (currentTimestamp - latestMinuteTimeStamp >  60 * 1000) {
//            latestMinuteHeartbeatRate = 0L;
//            latestMinuteHeartbeatRate = new AtomicLong(0L);
            latestMinuteHeartbeatRate = new LongAdder();
            latestMinuteTimeStamp = System.currentTimeMillis();
        }
//        latestMinuteHeartbeatRate++;
//        latestMinuteHeartbeatRate.incrementAndGet();
        latestMinuteHeartbeatRate.increment();
    }

    /**
     * 获取单例实例
     * @return
     */
    public static HeartbeatCounter getInstance() {
        return instance;
    }

    public /**synchronized*/ long getLatestMinuteHeartbeatRate() {
//        return latestMinuteHeartbeatRate.get();
        return latestMinuteHeartbeatRate.longValue();
    }

}
