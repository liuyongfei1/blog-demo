package com.fullstackboy.springdemo.aop.dao;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * UserDAO增强类
 *
 * @author Liuyongfei
 * @date 2021/11/3 09:30
 */
public class UserJDKProxy {

    public static void main(String[] args) {

        Class[] interfaces = {UserDAO.class};
        
        UserDAOImpl userDAO = new UserDAOImpl();

        // 创建代理对象代码
        UserDAO dao = (UserDAO) Proxy.newProxyInstance(UserJDKProxy.class.getClassLoader(),interfaces,
                new UserProxy(userDAO));

        // 执行方法
        int result = dao.add(10,20);
        System.out.println("result: " + result);
    }
}

/**
 * 创建代理对象
 */
class UserProxy implements InvocationHandler {

    /**
     * 创建的是谁的代理对象，就把谁传进来
     */
    private Object object;

    public UserProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 执行增强方法代码
        if ("add".equals(method.getName())) {
            System.out.println("增强add方法......");
        } else {
            System.out.println("增强update方法......");
        }

        // 执行被增强的方法
        Object res = method.invoke(object, args);

        System.out.println("方法之后执行......");

        return res;
    }
}
