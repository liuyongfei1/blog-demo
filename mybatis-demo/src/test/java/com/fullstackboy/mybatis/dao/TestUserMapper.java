package com.fullstackboy.mybatis.dao;

import com.fullstackboy.mybatis.util.MybatisUtil;
import com.fullstackboy.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * 用户DAO类的单元测试类
 *
 * @author Liuyongfei
 * @date 2021/11/27 17:49
 */
public class TestUserMapper {

    @Test
    public void getUserListTest() {
        SqlSession sqlSession = null;

        try {
            sqlSession = MybatisUtil.getSqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> list = mapper.getUserList();
            List<User> list2 = mapper.getUserList();

            for (User user : list) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 用完一定要记得关闭
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
}
