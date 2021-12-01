package com.fullstackboy.springdemo.transaction.dao;

import com.fullstackboy.springdemo.transaction.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * 使用spring的声明式事务管理
 *
 * @author Liuyongfei
 * @date 2021/11/30 22:16
 */
public class User3MapperImpl extends SqlSessionDaoSupport implements UserMapper{
    @Override
    public List<User> getUserList() {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);

        // 先添加一个用户
        User newUser = new User(4,"test1","666");
        mapper.addUser(newUser);

//        int a = 2 / 0;

        // 再删除一个用户:李四
        mapper.deleteUser(2);
        return mapper.getUserList();
    }

    @Override
    public User getUserById(int id) {
        return getSqlSession().getMapper(UserMapper.class).getUserById(id);
    }

    @Override
    public void addUser(User user) {
        getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
