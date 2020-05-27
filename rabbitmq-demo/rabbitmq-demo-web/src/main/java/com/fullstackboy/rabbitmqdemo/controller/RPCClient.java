package com.fullstackboy.rabbitmqdemo.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.UUID;

/**
 * RPC客户端
 *
 * @author lyf
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-05-25 19:30
 */
@RestController
public class RPCClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public String send(String message) {
        //封装Message
        Message msg = this.con(message);
        System.out.println("客户端发送消息：" + msg.toString());

        //设置消息唯一id
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("Client发送消息的correlationId: " + correlationId);
        Message result = rabbitTemplate.sendAndReceive(QueueConstants.TOPIC_EXCHANGE, QueueConstants.TOPIC_QUEUE1, msg);
        // 提取rpc回应内容的header
        HashMap<String,Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
        // 获取消息id
        String msgId = (String) headers.get("spring_returned_message_correlation");
        System.out.println("Client收到消息的msgId:" + msgId) ;
        String response = "";
        if (msgId.equals(correlationId)) {

            // 提取rpc回应内容body
            response = new String(result.getBody());
            System.out.println("收到RPCServer返回的消息为：" + response);
        }
        return response;
    }

    public Message con(String s) {
        MessageProperties mp = new MessageProperties();
        byte[] src = s.getBytes(Charset.forName("UTF-8"));
        //mp.setCorrelationId("2222");   系统生成，这里设置没用
        mp.setContentType("application/json");
        mp.setContentEncoding("UTF-8");

        mp.setContentLength((long) s.length());
        return new Message(src, mp);
    }
}
