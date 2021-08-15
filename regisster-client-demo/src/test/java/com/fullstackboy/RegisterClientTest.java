package com.fullstackboy;

import com.fullstackboy.register.client.RegisterClient;

/**
 * 微服务注册client端
 *
 * @author Liuyongfei
 * @date 2021/8/15 18:30
 */
public class RegisterClientTest {

    public static void main(String[] args) {
        // 服务依赖这个jar包后，就可以这样的去使用这个client
        RegisterClient client = new RegisterClient();
        client.start();
    }
}
