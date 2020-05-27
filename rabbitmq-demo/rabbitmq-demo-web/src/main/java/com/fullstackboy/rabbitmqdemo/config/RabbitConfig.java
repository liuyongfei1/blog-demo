package com.fullstackboy.rabbitmqdemo.config;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
     * 设置同步RPC队列
     */
    @Bean
    public Queue syncRPCQueue() {
        return new Queue(QueueConstants.RPC_QUEUE1);
    }

    /**
     * 设置返回队列
     */
    @Bean
    public Queue replyQueue() {
        return new Queue(QueueConstants.RPC_QUEUE2);
    }

    /**
     * 设置交换机
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(QueueConstants.RPC_EXCHANGE);
    }

    /**
     * 请求队列和交换器绑定
     */
    @Bean
    public Binding tmpBinding() {
        return BindingBuilder.bind(syncRPCQueue()).to(exchange()).with(QueueConstants.RPC_QUEUE1);
    }

    /**
     * 返回队列和交换器绑定
     */
    @Bean
    public Binding replyBinding() {
        return BindingBuilder.bind(replyQueue()).to(exchange()).with(QueueConstants.RPC_QUEUE2);
    }


    /**
     * 使用 RabbitTemplate发送和接收消息
     * 并设置回调队列地址
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        // 设置回调队列地址
        template.setReplyAddress(QueueConstants.RPC_QUEUE2);
        // 设置请求超时时间为6s
        template.setReplyTimeout(60000);
        return template;
    }


    /**
     * 给返回队列设置监听器
     */
    @Bean
    public SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QueueConstants.RPC_QUEUE2);
        container.setMessageListener(rabbitTemplate(connectionFactory));
        return container;
    }
}
