package com.fullstackboy.mybatis.rabbitmqdemo.model;

import lombok.Data;

/**
 * TODO Liuyongfei
 *
 * @Author: Liuyongfei
 * @Date: 2020/6/1 22:26
 */
@Data
public class ProductRobbingRecord {
    public int id;

    public String mobile;

    public int productId;

    public int createTime;
}
