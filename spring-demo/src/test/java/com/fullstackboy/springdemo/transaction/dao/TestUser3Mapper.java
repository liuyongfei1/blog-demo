package com.fullstackboy.springdemo.transaction.dao;

import com.fullstackboy.springdemo.transaction.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 单元测试类
 * 使用第二种整合方式： 使用SqlSessionDaoSupport
 * @author Liuyongfei
 * @date 2021/11/29 23:49
 */

public class TestUser3Mapper {

    @Test
    public void getUserListTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserMapper mapper = applicationContext.getBean("userMapper3",UserMapper.class);

        // 查看现在的用户列表
        for (User user : mapper.getUserList()) {
            System.out.println(user);
        }

       /* 输出结果
        User(id=1, name=张三, pwd=123)
        User(id=2, name=李四, pwd=567)
        User(id=3, name=王五, pwd=789)*/

    }
}
