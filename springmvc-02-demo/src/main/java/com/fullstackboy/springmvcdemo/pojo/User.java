package com.fullstackboy.springmvcdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author Liuyongfei
 * @date 2021/12/2 22:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;

    private String pwd;
}
