package com.fullstackboy.mybatis.dao;

import com.fullstackboy.mybatis.pojo.User;
import com.fullstackboy.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * 用户DAO类的单元测试类
 * 当前会话结束，一级缓存没了，这时数据保存到了二级缓存中去，新来的会话查询这个数据时，从二级缓存里取。
 * 前提：使用的是同一个Mapper。
 * @author Liuyongfei
 * @date 2021/11/27 17:49
 */
public class Test3UserMapper {

    @Test
    public void getUserListTest() {

        SqlSession sqlSession = MybatisUtil.getSqlSession();
        SqlSession sqlSession2 = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();

        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.getUserById(1);
        System.out.println(user2);
        System.out.println(user == user2); // true
        sqlSession2.close();
    }
}
