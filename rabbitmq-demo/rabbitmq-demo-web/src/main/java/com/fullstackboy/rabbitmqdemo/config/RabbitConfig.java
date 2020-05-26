package com.fullstackboy.rabbitmqdemo.config;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
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
    public static final String QUEUE_SYNC_RPC = "rpc.sync";

    @Bean
    public Queue syncRPCQueue() {
        return new Queue(QUEUE_SYNC_RPC);
    }

    @Bean
    public Queue repliesQueue() {
        return new AnonymousQueue();
    }

    @Bean
    @Primary
    public SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueueNames(repliesQueue().getName());
        return container;
    }
}
