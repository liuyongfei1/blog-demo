package com.fullstackboy.mybatis.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表实体类
 *
 * @author Liuyongfei
 * @date 2021/11/27 17:37
 */
@Data
public class User implements Serializable {

    private int id;

    private String name;

    private String pwd;
}
