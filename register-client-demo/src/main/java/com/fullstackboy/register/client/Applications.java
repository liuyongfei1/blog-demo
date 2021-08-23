package com.fullstackboy.register.client;

import java.util.HashMap;
import java.util.Map;

/**
 * 包装registry
 *
 * @author Liuyongfei
 * @date 2021/8/24 06:36
 */
public class Applications {
    private Map<String, Map<String, ServiceInstance>> registry =
            new HashMap<String, Map<String, ServiceInstance>>();

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
