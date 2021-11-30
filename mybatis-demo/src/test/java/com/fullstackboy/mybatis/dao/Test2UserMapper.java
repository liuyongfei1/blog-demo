package com.fullstackboy.mybatis.dao;

import com.fullstackboy.mybatis.pojo.User;
import com.fullstackboy.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * 用户DAO类的单元测试类
 * 同一个会话内，同一个查询，查询多次，会走缓存
 * @author Liuyongfei
 * @date 2021/11/27 17:49
 */
public class Test2UserMapper {

    @Test
    public void getUserListTest() {

        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        User user2 = mapper.getUserById(1);
        System.out.println(user);
        System.out.println(user == user2); // true
        sqlSession.close();

    }
}
