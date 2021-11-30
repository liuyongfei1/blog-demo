package com.fullstackboy.mybatis.dao;

import com.fullstackboy.mybatis.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> getUserList();

    User getUserById(int id);
}
