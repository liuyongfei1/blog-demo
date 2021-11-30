package com.fullstackboy.springdemo.transaction.dao;


import com.fullstackboy.springdemo.transaction.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> getUserList();

    User getUserById(int id);

    void addUser(User user);

    int deleteUser(int id);
}
