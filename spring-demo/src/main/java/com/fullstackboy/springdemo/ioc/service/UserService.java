package com.fullstackboy.springdemo.ioc.service;

import com.fullstackboy.springdemo.ioc.dao.UserDAO;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/11/1 13:00
 */
public class UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void add() {
        System.out.println("service add......");
        userDAO.add();
    }
}
