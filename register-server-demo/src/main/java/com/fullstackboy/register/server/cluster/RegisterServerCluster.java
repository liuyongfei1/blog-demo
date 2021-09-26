package com.fullstackboy.register.server.cluster;

import java.util.ArrayList;
import java.util.List;

/**
 * 微服务注册中心集群
 *
 * @author Liuyongfei
 * @date 2021/9/26 12:45
 */
public class RegisterServerCluster {

    private static final List<String> peers = new ArrayList<String>();

    static {
        // 从配置文件中读取集群都有哪些机器
    }

    public static List<String> getPeers() {
        return peers;
    }
}
