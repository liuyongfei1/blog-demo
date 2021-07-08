package com.fullstackboy.rabbitmqdemo.model;

import lombok.Data;

/**
 * TODO Liuyongfei
 *
 * @Author: Liuyongfei
 * @Date: 2020/6/1 22:23
 */
@Data
public class Product {

    private int id;

    private String productNo;

    private int total;

    private int updateTime;
}
