package com.fullstackboy.springdemo.aop.dao;

/**
 * UserDAO实现类
 *
 * @author Liuyongfei
 * @date 2021/11/3 09:26
 */
public class UserDAOImpl implements UserDAO{
    @Override
    public int add(int a,int b) {
        System.out.println("UserDAOImpl中执行add方法......");
        return a + b;
    }

    @Override
    public String update(String msg) {
        System.out.println("UserDAOImpl中执行update方法......");
        return msg;
    }
}
