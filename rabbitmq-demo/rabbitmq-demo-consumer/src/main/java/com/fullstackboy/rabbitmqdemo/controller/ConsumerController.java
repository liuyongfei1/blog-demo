package com.fullstackboy.rabbitmqdemo.controller;

import com.fullstackboy.rabbitmqdemo.common.QueueConstants;
import com.fullstackboy.rabbitmqdemo.service.ConcurrencyService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息消费端
 *
 * @author Liuyongfei
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @date 2020-05-21 18:00
 */
@Slf4j
@Component
public class ConsumerController {

    @Autowired
    private ConcurrencyService concurrencyService;

    @RabbitListener(queues = {QueueConstants.QUEUE_NAME}, containerFactory = "customContainerFactory")
    public void handler(Message message, Channel channel) throws IOException {

        channel.basicQos(1);

        // 获取手机号
        String mobile = (String) message.getPayload();
        log.info("监听到抢单手机号：[{}]", mobile);

        // 获取消息投递序号
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        try {
            // 处理抢单逻辑
            concurrencyService.manageRobbing(mobile);

            log.info("手机号：[{}] 的用户抢单成功", mobile);

            // 确认消息已经消费
            channel.basicAck(tag, false);

            // 异步通知用户抢单成功
        } catch (Exception e) {
            log.error("用户 : [{}] 商城抢单时发生异常", mobile, e.fillInStackTrace());
            // 确认消息已经消费（basicReject 和 basicNack 的区别在于，basicNack 一次可以拒绝多条消息，而basicReject一次只能拒绝一条消息）
            channel.basicReject(tag, false);
        }

    }
}
