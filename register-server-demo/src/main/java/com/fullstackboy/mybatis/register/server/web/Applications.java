package com.fullstackboy.mybatis.register.server.web;

import com.fullstackboy.mybatis.register.server.core.ServiceInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * 包装Registry
 *
 * @author Liuyongfei
 * @date 2021/8/28 18:40
 */
public class Applications {

    Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();

    public Applications() {
    }

    public Applications(Map<String, Map<String, ServiceInstance>> registry) {
        this.registry = registry;
    }

    public Map<String, Map<String, ServiceInstance>> getRegistry() {
        return registry;
    }

    public void setRegistry(Map<String, Map<String, ServiceInstance>> registry) {
        this.registry = registry;
    }
}
