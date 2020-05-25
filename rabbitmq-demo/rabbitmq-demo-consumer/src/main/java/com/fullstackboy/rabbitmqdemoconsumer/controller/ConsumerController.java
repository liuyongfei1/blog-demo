package com.fullstackboy.rabbitmqdemoconsumer.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息消费端1
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @author Liuyongfei
 * @date 2020-05-21 18:00
 */
@Component
public class ConsumerController {

    @RabbitListener(queues = {QueueConstants.QUEUE_NAME})
    public void handler(Message message, Channel channel) throws IOException {
        System.out.println("收到消息：" + message.toString());
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        try {
            // 手动确认消息已消费
            channel.basicAck(tag,false);
        } catch (IOException e) {
            // 把消费失败的消息重新放入到队列，以后可以继续消费该条消息
            channel.basicNack(tag, false, true);
            e.printStackTrace();
        }
    }
}
