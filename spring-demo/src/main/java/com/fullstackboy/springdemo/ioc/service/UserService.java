package com.fullstackboy.springdemo.ioc.service;

import com.fullstackboy.springdemo.ioc.dao.UserDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/11/1 13:00
 */
@Qualifier
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
