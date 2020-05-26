package com.fullstackboy.rabbitmqdemoconsumer.config;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Rabbit2Config {
    @Autowired
    CachingConnectionFactory cachingConnectionFactory;

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        // 在这里设置返回队列
        rabbitTemplate.setReplyAddress(QueueConstants.TOPIC_QUEUE2);
        rabbitTemplate.setReplyTimeout(60000);
        return rabbitTemplate;
    }

    // 设置队列监听器
    @Bean
    SimpleMessageListenerContainer createReplyListenerContainer() {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(cachingConnectionFactory);
        listenerContainer.setQueueNames(QueueConstants.TOPIC_QUEUE2);
        listenerContainer.setMessageListener(rabbitTemplate());
        return  listenerContainer;
    }

    //创建队列
    @Bean
    Queue topicQueue1() {
        return new Queue(QueueConstants.TOPIC_QUEUE1);
    }
    @Bean
    Queue topicQueue2() {
        return new Queue(QueueConstants.TOPIC_QUEUE2);
    }

    // 创建交换机
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(QueueConstants.TOPIC_EXCHANGE);
    }

    // 交换机与队列进行绑定
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(QueueConstants.TOPIC_QUEUE1);
    }
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(QueueConstants.TOPIC_QUEUE2);
    }

    /**
     * 声明队列
     * 参数说明：
     * durable 是否持久化，默认是false（持久化队列则数据会被存储在磁盘上，当消息代理重启时数据不会丢失；暂存队列只对当前连接有效）
     * exclusive 默认是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
     * autoDelete 默认是false，是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
     * 一般设置一下队列的持久化就好，其余两个就是默认false
     *
     * @return Queue
     */
    @Bean
    Queue myQueue() {
        return new Queue(QueueConstants.QUEUE_NAME, true);
    }

    /**
     * 设置交换机，类型为 direct
     *
     * @return DirectExchange
     */
    @Bean
    DirectExchange myExchange() {
        return new DirectExchange(QueueConstants.QUEUE_EXCHANGE_NAME, true, false);
    }

    /**
     * 绑定：将交换机和队列绑定，并设置路由匹配键
     *
     * @return Binding
     */
    @Bean
    Binding queueBinding() {
        return BindingBuilder.bind(myQueue()).to(myExchange()).with(QueueConstants.QUEUE_ROUTING_KEY_NAME);
    }
}
