package com.fullstackboy.springdemo.ioc.service;

import org.springframework.stereotype.Component;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/11/2 22:31
 */
@Component
public class DeptService {
    public void add() {
        System.out.println("service add......");
    }
}
