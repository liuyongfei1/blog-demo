package com.fullstackboy.mybatis.util;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis工具类
 *
 * @author Liuyongfei
 * @date 2021/11/27 16:34
 */
public class MybatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {

        try {
            // 使用Mybatis第一步，获取SqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 有了SqlSessionFactory，就可以获取SqlSession实例
     * 获取SqlSession实例,SqlSession实例包含了面向数据库执行sql命令的各种方法
     * @return SqlSession实例
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
