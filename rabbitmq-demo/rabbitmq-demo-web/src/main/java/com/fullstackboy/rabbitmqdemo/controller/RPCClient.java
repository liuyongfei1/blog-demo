package com.fullstackboy.rabbitmqdemo.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * RPC客户端
 *
 * @author lyf
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-05-25 19:30
 */
@Slf4j
@RestController
public class RPCClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage")
    public String send(String message) {
        // 封装Message，直接发送message对象
        Message newMessage = convertMessage(message);

        log.info("客户端发送的消息：" + newMessage.toString());

        // 备注：使用sendAndReceive 这个方法发送消息时，消息的correlationId会变成系统动编制的 1,2,3 这种格式,因此通过手动set的方式没有用
        Message result = rabbitTemplate.sendAndReceive(QueueConstants.RPC_EXCHANGE, QueueConstants.RPC_QUEUE1,
                newMessage);

        String response = "";
        if (result != null) {
            // 获取已发送的消息的唯一消息id
            String correlationId = newMessage.getMessageProperties().getCorrelationId();

            // 提取RPC回应内容的header
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();

            // 获取RPC回应消息的消息id
            String msgId = (String) headers.get("spring_returned_message_correlation");

            // 客户端从回调队列获取消息，匹配与发送消息correlationId相同的消息为应答结果
            if (msgId.equals(correlationId)) {
                // 提取RPC回应内容body
                response = new String(result.getBody());
                log.info("收到RPCServer返回的消息为：" + response);
            }
        }
        return response;
    }

    /**
     * 将发送消息封装成Message
     *
     * @param message
     * @return org.springframework.amqp.core.Message
     * @Author Liuyongfei
     * @Date 下午1:23 2020/5/27
     **/
    public Message convertMessage(String message) {
        MessageProperties mp = new MessageProperties();
        byte[] src = message.getBytes(Charset.forName("UTF-8"));
        // 注意：由于在发送消息的时候，系统会自动生成消息唯一id，因此在这里手动设置的方式是无效的
        // CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        // mp.setCorrelationId("123456");
        mp.setContentType("application/json");
        mp.setContentEncoding("UTF-8");
        mp.setContentLength((long) message.length());
        return new Message(src, mp);
    }
}
