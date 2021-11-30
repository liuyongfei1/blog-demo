package com.fullstackboy.springdemo.transaction.dao;

import com.fullstackboy.springdemo.transaction.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * 整合Mybatis方式二
 *
 * @author Liuyongfei
 * @date 2021/11/30 13:21
 */
public class User2MapperImpl extends SqlSessionDaoSupport implements UserMapper{
    @Override
    public List<User> getUserList() {
        return getSqlSession().getMapper(UserMapper.class).getUserList();
    }

    @Override
    public User getUserById(int id) {
        return getSqlSession().getMapper(UserMapper.class).getUserById(id);
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public int deleteUser(int id) {
        return 0;
    }
}
