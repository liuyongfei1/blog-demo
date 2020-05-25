package com.fullstackboy.rabbitmqdemo.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import com.fullstackboy.rabbitmqdemo.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产端
 *
 * @author lyf
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-05-17 18:30
 */
@Slf4j
@RestController
public class ProducerController {

    /**
     * RabbitTemplate提供了发送/接收消息的方法
     */
    @Autowired
    RabbitTemplate rabbitTemplate;


    /**
     * 生产消息（RPC客户端）
     *
     * @return java.lang.String
     * @Author Liuyongfei
     * @Date 上午10:00 2020/5/24
     **/
    @GetMapping("/sendMessage")
    public String sendDirectMessage() {
        //报文body
        String sss = "报文的内容";
        //封装Message
        Message msg = this.con(sss);
        log.info("客户端--------------------" + msg.toString());

        //使用sendAndReceive方法完成rpc调用
        Message message = rabbitTemplate.sendAndReceive(QueueConstants.TOPIC_EXCHANGE, QueueConstants.TOPIC_QUEUE1, msg);

        //提取rpc回应内容body
        String response = new String(message.getBody());
        log.info("回应：" + response);
        log.info("rpc完成---------------------------------------------");
        return "rpc完成---------------------------------------------";
    }

    public Message con(String s) {
        MessageProperties mp = new MessageProperties();
        byte[] src = s.getBytes(Charset.forName("UTF-8"));
        //mp.setReplyTo("adsdas");   加载AmqpTemplate时设置，这里设置没用
        //mp.setCorrelationId("2222");   系统生成，这里设置没用
        mp.setContentType("application/json");
        mp.setContentEncoding("UTF-8");
        mp.setContentLength((long) s.length());
        return new Message(src, mp);
    }


    public String sendAndReceive(String request) throws TimeoutException {
        log.info("请求报文：{}", request);

        //请求结果
        String result = null;

        //设置消息唯一id
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        // 直接发送消息
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("10000");
        messageProperties.setCorrelationId(correlationId.getId());

        Message message = new Message(request.getBytes(), messageProperties);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("direct模式下rpc请求耗时");
        Message response = rabbitTemplate.sendAndReceive(QueueConstants.QUEUE_EXCHANGE_NAME,
                QueueConstants.QUEUE_ROUTING_KEY_NAME, message, correlationId);

        stopWatch.stop();
        log.info(stopWatch.getLastTaskName() + ":" + stopWatch.getTotalTimeMillis());
        if (response != null) {
            result = new String(response.getBody());
            log.info("请求成功，返回的结果为：{}", result);
        } else {
            log.error("请求超时");

            // 为了方便jmeter测试，这里抛出异常
            throw new TimeoutException("请求超时");
        }
        return result;
    }
}
