package com.fullstackboy.springdemo.ioc.config;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import java.util.Map;


/**
 * 对应的代理实现类
 */
public class RoutingBeanProxyFactory {
 
    private final static String DEFAULT_BEAN_NAME = "helloServiceImpl1";
 
    public static Object createProxy(String name, Class type, Map<String, Object> candidates) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(name, candidates));
        return proxyFactory.getProxy();
    }

    /**
     * spring动态代理之MethodInterceptor拦截器
     *
     */
    static class VersionRoutingMethodInterceptor implements MethodInterceptor {
        private Object targetObject;
 
        public VersionRoutingMethodInterceptor(String name, Map<String, Object> beans) {
            this.targetObject = beans.get(name);
            if (this.targetObject == null) {
                this.targetObject = beans.get(DEFAULT_BEAN_NAME);
            }
        }

        /**
         *
         * @param invocation 额外功能增加的那个原始方法，比如，register()，login()
         * @return 因为每个方法的返回值不一样，所以返回object
         * @throws Throwable
         */
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("方法增强...");
            return invocation.getMethod().invoke(this.targetObject, invocation.getArguments());
        }
    }
}