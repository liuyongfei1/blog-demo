package com.fullstackboy.springdemo.ioc.service;

import org.springframework.stereotype.Service;

/**
 * 对应接口实现类
 *
 * @author Liuyongfei
 * @date 2022/1/12 13:18
 */
@Service
public class Hello2ServiceImpl implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("你好我是Hello2ServiceImpl");
    }
}
