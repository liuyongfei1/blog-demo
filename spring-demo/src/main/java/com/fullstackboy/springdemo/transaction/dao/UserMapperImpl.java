package com.fullstackboy.springdemo.transaction.dao;

import com.fullstackboy.springdemo.transaction.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

/**
 * UserMapper接口实现类
 *
 * @author Liuyongfei
 * @date 2021/11/29 23:37
 */
public class UserMapperImpl implements UserMapper{

    // 在Mybatis中，我们所有的CRUD操作都是基于SqlSession，与Spring整合后，在Spring中，都是使用SpringSqlSessionTemplate
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> getUserList() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.getUserList();
    }

    @Override
    public User getUserById(int id) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.getUserById(id);
    }
}
