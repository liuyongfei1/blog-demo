package com.fullstackboy.rabbitmqdemo.config;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @author lyf
 * @date 2020-05-17 17:20
 */
@Configuration
public class RabbitConfig {

    /**
     * 声明队列
     * 参数说明：
     * durable 是否持久化，默认是false（持久化队列则数据会被存储在磁盘上，当消息代理重启时数据不会丢失；暂存队列只对当前连接有效）
     * exclusive 默认是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
     * autoDelete 默认是false，是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
     * 一般设置一下队列的持久化就好，其余两个就是默认false
     * @return Queue
     */
    @Bean
    Queue myQueue() {
        return new Queue(QueueConstants.QUEUE_NAME, true);
    }

    /**
     * 设置交换机，类型为 direct
     * @return DirectExchange
     */
    @Bean
    DirectExchange myExchange() {
        return new DirectExchange(QueueConstants.QUEUE_EXCHANGE_NAME, true, false);
    }

    /**
     * 绑定：将交换机和队列绑定，并设置路由匹配键
     * @return Binding
     */
    @Bean
    Binding queueBinding() {
        return BindingBuilder.bind(myQueue()).to(myExchange()).with(QueueConstants.QUEUE_ROUTING_KEY_NAME);
    }
}
