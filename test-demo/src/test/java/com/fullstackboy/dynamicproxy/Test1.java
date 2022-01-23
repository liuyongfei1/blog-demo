package com.fullstackboy.dynamicproxy;


import org.junit.Test;

/**
 * 动态代理相关测试
 * 1、静态代理
 * 2、JDK动态代理
 * 3、CGLIB动态代理
 *
 * @author Liuyongfei
 * @date 2022/1/23 16:54
 */
public class Test1 {

    /**
     * 1、静态代理
     * 这种方式的不足之处：
     * 1> 如果接口类Animal增加方法时，不仅实现类Cat类需要新增该方法的实现，由于代理类也实现了Animal接口所以也需要新增该方法的实现。当项目规模较大时，在维护上就不太友好了
     * 2> 代理类实现Animal#call是针对Cat目标类的对象进行设置的，如果再需要添加Dog目标类的代理，那就必须再针对Dog类实现一个对应的代理类。这样就使得代码的重用性不太好
     *
     * 这些问题，在JDK动态代理中就得到了友好的解决。
     */
    @Test
    public void testStaticProxy() {
        Cat cat = new Cat();

        StaticProxyAnimal proxy = new StaticProxyAnimal(cat);
        proxy.call();
    }
}
