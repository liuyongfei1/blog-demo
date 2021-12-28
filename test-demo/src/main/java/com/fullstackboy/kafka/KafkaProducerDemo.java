package com.fullstackboy.mybatis;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.UUID;


/**
 * Kafka生产者
 *
 * @author Liuyongfei
 * @date 2021/10/12 16:24
 */
public class KafkaProducerDemo {

    public static void main(String[] args) {
        KafkaProducer<String, String> producer = createKafkaProducer();

        JSONObject order = createOrder();

        ProducerRecord<String,String> record = new ProducerRecord<String,String>("create-order-topic2",
                order.getString("orderId"), order.toString());

        long startTime = System.currentTimeMillis();

        try {
            // 异步发送模式
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        System.out.println("消息发送成功了");
                    } else {
                        System.out.println("消息发送失败");
                    }
                }
            });

            long endTime = System.currentTimeMillis();
            if (endTime - startTime > 10) {
                System.out.println("---消息发送成功耗时超过10毫秒，报警。。。。");
            }

            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.close();

    }

    /**
     * 创建一个用户测试的订单
     * @return 订单
     */
    private static JSONObject createOrder() {
        JSONObject order = new JSONObject();
        order.put("orderId", 1);
        order.put("orderNo", UUID.randomUUID().toString());
        order.put("userId", 121);
        order.put("productId", 690);
        order.put("productCount", 2);
        order.put("productPrice", 100.0);
        order.put("_OPERATION_", "PAY");
        return order;
    }


    /**
     * 创建一个Kafka生产者
     * @return
     */
    private static KafkaProducer<String,String> createKafkaProducer() {
        Properties props = new Properties();

        // 配置broker
        props.put("bootstrap.servers", "10.200.47.37:9092,10.200.47.38:9092");

        // 将发送的key字符串系列化为字节数组
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 这个就是负责把你发送的实际的message从字符串序列化为字节数组
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("buffer.memory", 67108864);
        props.put("batch.size", 131072); // 一般来说是要自己手动设置的，不是纯粹依靠默认值的，16kb
        props.put("linger.ms", 100); // 发送一条消息出去，100ms内还没有凑成一个batch发送，必须立即发送出去
        props.put("max.request.size", 10485760); // 这个是说你最多可以发送多大的一条消息出去
        props.put("acks", "1"); // follower有没有同步成功你就不管了
        props.put("retries", 10); // 这个重试，一般来说，给个3次~5次就足够了，可以cover住一般的异常场景
        props.put("retry.backoff.ms", 500); // 每次重试间隔100ms

        return new KafkaProducer<String, String>(props);
    }
}
