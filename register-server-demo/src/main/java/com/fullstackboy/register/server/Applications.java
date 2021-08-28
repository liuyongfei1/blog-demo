package com.fullstackboy.register.server;

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

    public Map<String, Map<String, ServiceInstance>> getRegistry() {
        return registry;
    }

    public void setRegistry(Map<String, Map<String, ServiceInstance>> registry) {
        this.registry = registry;
    }
}
