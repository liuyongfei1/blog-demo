package com.fullstackboy.springmvcdemo.service;

import com.fullstackboy.springmvcdemo.pojo.User;
import org.springframework.stereotype.Service;

/**
 * 用户行为相关组件
 *
 * @author Liuyongfei
 * @date 2021/12/2 22:13
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public boolean register(User user) {
        if ("bianchengbang".equals(user.getName()) && "123456".equals(user.getPwd())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean login(User user) {
        if ("bianchengbang".equals(user.getName()) && "123456".equals(user.getPwd())) {
            return true;
        }
        return false;
    }
}
