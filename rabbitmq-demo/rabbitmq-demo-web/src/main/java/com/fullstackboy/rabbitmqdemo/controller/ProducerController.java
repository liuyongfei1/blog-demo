package com.fullstackboy.rabbitmqdemo.controller;

import com.fullstackboy.rabbitmqdemo.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息生产端
 * @公众号 全栈在路上
 * @GitHub https://github.com/liuyongfei1
 * @author lyf
 * @date 2020-05-17 18:30
 */
@RestController
public class ProducerController {
    @Autowired
    private InitService initService;


    /**
     * 生产消息
     *
     * @Author Liuyongfei
     * @Date 上午12:12 2020/5/20 
     * @return java.lang.String
     **/
    @GetMapping("/robbing/thread")
    public String robbingThread() throws InterruptedException {
        initService.generateMultiThread();
        return "message send ok";
    }
}
