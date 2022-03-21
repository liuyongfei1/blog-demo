package com.fullstackboy.test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 将大量的消息 分成n个桶，每个桶装一批消息
 * 比如分成m个桶，每个桶装n个用户
 * 然后批量发送消息，一桶一个桶的发给 MQ
 * 比如： 10W 个用户
 * userBucket1 = [1, 1001]
 * userBucket2 = [1001, 2001]
 * ......
 * ......
 * 批量发送次数 = 10次，这样就把发消息的次数由10W次降到10次
 * @author Liuyongfei
 * @date 2022/3/21 16:31
 */
public class Demo11 {

    public static void main(String[] args) {
        final int userBucketSize = 1000;
        AtomicBoolean flagRef = new AtomicBoolean(true);
        long startUserId = 1L;
        Long maxUserId = 500L;
        
        Map<Long, Long> userBuckets = new LinkedHashMap<>();

        while (flagRef.get()) {
            if (startUserId > maxUserId) {
                flagRef.compareAndSet(true, false);
            }
            userBuckets.put(startUserId,startUserId + userBucketSize);
            startUserId += userBucketSize;
        }

        System.out.println(userBuckets);

        for(Map.Entry<Long, Long> userBucket : userBuckets.entrySet()) {
            System.out.println("key ===>" + userBucket.getKey() + ", value ===>" + userBucket.getValue());
        }
    }
}
