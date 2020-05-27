package com.fullstackboy.rabbitmqdemo.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * RPC服务端
 *
 * @author lyf
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-05-25 22:00
 */
@Component
public class RPCServer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = QueueConstants.TOPIC_QUEUE1)
    public void process(Message msg) {
        int millis = (int) (Math.random() * 2 * 1000);
        String msgBody = new String(msg.getBody());
        // 模拟处理业务逻辑
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Server收到发送的消息，correlationId为: " + msg.getMessageProperties().getCorrelationId());

        // 数据处理，返回Message
        String newMessage = msgBody + "，sleep " + millis + " ms";
        Message response = convertMessage(newMessage, msg.getMessageProperties().getCorrelationId());
        CorrelationData correlationData = new CorrelationData(msg.getMessageProperties().getCorrelationId());
        rabbitTemplate.send(QueueConstants.TOPIC_EXCHANGE, QueueConstants.TOPIC_QUEUE2, response, correlationData);
    }

    @RabbitListener(queues = QueueConstants.TOPIC_QUEUE2)
    public void receiveTopic2(Message msg) {
        System.out.println("队列2:" + msg.toString());
    }

    /**
     * 封装消息
     *
     * @Author Liuyongfei
     * @Date 下午1:25 2020/5/27
     * @param s 消息
     * @param id 消息id
     * @return org.springframework.amqp.core.Message
     **/
    public Message convertMessage(String s,String id) {
        MessageProperties mp = new MessageProperties();
        byte[] src = s.getBytes(Charset.forName("UTF-8"));
        mp.setContentType("application/json");
        mp.setContentEncoding("UTF-8");
        mp.setCorrelationId(id);
        return new Message(src, mp);
    }
}
