package com.fullstackboy.rabbitmqdemo.config;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
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

    @Bean
    public TopicExchange tmpExchange() {
        return new TopicExchange(QueueConstants.TOPIC_EXCHANGE);
    }

    // 请求队列和交换器绑定
    @Bean
    public Binding tmpBinding() {
        return BindingBuilder.bind(syncRPCQueue()).to(tmpExchange()).with(QueueConstants.TOPIC_QUEUE1);
    }

    // 返回队列和交换器绑定
    @Bean
    public Binding replyBinding() {
        return BindingBuilder.bind(replyQueue()).to(tmpExchange()).with(QueueConstants.TOPIC_QUEUE2);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.setReplyAddress(QueueConstants.TOPIC_QUEUE2);
//        template.setUserCorrelationId(true);
        //设置请求超时时间为10s
        template.setReplyTimeout(60000);
        return template;
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
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QueueConstants.TOPIC_QUEUE2);
        container.setMessageListener(rabbitTemplate(connectionFactory));
        return container;
    }
}
