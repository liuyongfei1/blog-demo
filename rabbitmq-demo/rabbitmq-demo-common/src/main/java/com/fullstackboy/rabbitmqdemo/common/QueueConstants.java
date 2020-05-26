package com.fullstackboy.rabbitmqdemo.common;

/**
 * 定义队列所需要的常量
 *
 * @author lyf
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-05-17 18:30
 */
public class QueueConstants {
    public static final String QUEUE_NAME = "demo1_queue";
    public static final String QUEUE_EXCHANGE_NAME = "demo1_exchange";
    public static final String QUEUE_ROUTING_KEY_NAME = "demo1_routing_key";

    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topic.exchange";
    public static final String TOPIC_ROUTING_KEY_NAME = "topic.queue1";
}
