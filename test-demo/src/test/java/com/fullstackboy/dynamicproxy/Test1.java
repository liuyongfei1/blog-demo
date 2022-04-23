package com.fullstackboy.dynamicproxy;


import org.junit.Test;
import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * 动态代理机制：https://zhuanlan.zhihu.com/p/126503023
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


    /**
     * 2、JDK动态代理
     * 可以设置JVM启动参数为：-Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
     * 或者
     * System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
     */
    @Test
    public void testJdkDynamicProxy() {
        Cat cat = new Cat();
        Animal proxy = (Animal) JDKProxyAnimal.getProxy(cat);

        String test = proxy.call();
        System.out.println("测试：" + test);
        System.out.println("测试2：" + proxy.sleep());
    }

    /**
     * 3、CGLIB动态代理
     */
    @Test
    public void testCglibDynamicProxy() {

        // 获取当前项目的根目录
        String userDir = System.getProperty("user.dir");
        // 可以将保存生成的字节码文件到 当前项目根目录下： test-demo/mycglib
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, userDir + "/mycglib");
        Animal cat = (Animal) CglibProxy.getProxy(Cat.class);
        cat.call();
    }
}
