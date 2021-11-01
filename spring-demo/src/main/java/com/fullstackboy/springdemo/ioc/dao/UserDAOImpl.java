package com.fullstackboy.springdemo.ioc.dao;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/11/1 13:02
 */
public class UserDAOImpl implements UserDAO{
    @Override
    public void add() {
        System.out.println("user add ....");
    }
}
