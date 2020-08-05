package com.alibaba.otter.canal.example.kafka;

import com.alibaba.otter.canal.example.BaseCanalClientTest;

/**
 * Kafka 测试基类
 *
 * @author machengyuan @ 2018-6-12
 * @version 1.0.0
 */
public abstract class AbstractKafkaTest extends BaseCanalClientTest {

    public static String  topic     = "demo_lyf";
    public static Integer partition = 0;
    public static String  groupId   = "g4";
    public static String  servers   = "10.200.23.108:9092,10.200.23.111:9092";
    public static String  zkServers = "10.200.23.109:2181,10.200.23.110:2181,10.200.23.111:2181";

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}
