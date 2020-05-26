package com.fullstackboy.rabbitmqdemo.config;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * RabbitMQ配置
 *
 * @author lyf
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-05-17 17:20
 */
@Slf4j
@Configuration
public class RabbitConfig {
    /**
     * 同步RPC队列
     */
//    public static final String QUEUE_SYNC_RPC = "rpc.sync";

    @Bean
    public Queue syncRPCQueue() {
        return new Queue(QueueConstants.TOPIC_QUEUE1);
    }

    /**
     * 设置返回队列
     *
     * @Author Liuyongfei
     * @Date 下午6:12 2020/5/26
     * @return org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue replyQueue() {
        return new Queue(QueueConstants.TOPIC_QUEUE2);
    }

    /**
     * 给返回队列设置监听器
     *
     * @Author Liuyongfei
     * @Date 下午6:07 2020/5/26
     * @param connectionFactory
     * @return org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
     **/
    @Bean
    @Primary
    public SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(replyQueue().getName());
        return container;
    }
}
