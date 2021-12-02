package com.fullstackboy.springmvcdemo.service;

import com.fullstackboy.springmvcdemo.pojo.User;

/**
 * 用户行为相关接口
 */
public interface UserService {

    boolean register(User user);

    boolean login(User user);
}
