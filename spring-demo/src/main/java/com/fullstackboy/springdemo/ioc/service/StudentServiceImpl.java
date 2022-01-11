package com.fullstackboy.springdemo.ioc.service;

import com.fullstackboy.springdemo.ioc.bean.Student;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/1/11 09:32
 */
public class StudentServiceImpl implements StudentService{

    @Override
    public int save(Student student) {
        System.out.println("调用了当前方法");
        return 1;
    }
}
