package com.fullstackboy.reflection;

import java.lang.reflect.Method;

/**
 * 如何通过反射来创建对象
 *
 * 2、通过getConstructor或getDeclaredConstructor方法获得构造器（Constructor）对象；
 *    并调用其newInstance方法创建对象；
 *
 * 适用于无参和有参构造方法
 * @author Liuyongfei
 * @date 2022/1/8 21:50
 */
public class Demo2 {
    private String str;
    private int num;

    public Demo2() {
    }

    public Demo2(String str, int num) {
        this.str = str;
        this.num = num;
    }

    public String test() {
        System.out.println("执行test方法");
        return "test";
    }

    public Demo2(String str) {
        this.str = str;
    }

    public static void main(String[] args) throws Exception {
        Class[] classes = new Class[] {String.class, int.class};

        Demo2 demo = Demo2.class.getConstructor(classes).newInstance("hello", 30);
//        System.out.println(demo.str);

        Demo2 demo21 = Demo2.class.newInstance();
        Demo2 demo22 = Demo2.class.getDeclaredConstructor().newInstance();
//        System.out.println(demo21);
//        System.out.println(demo22);

        // ***************通过反射，拿到 Demo2 类的 test 方法
        Method method = Demo2.class.getMethod("test");
        // *******************执行test方法
        // 总结：手撸Spring-小傅哥 里的第8章，实现Bean对象的初始化方法(init-method) 就是采用的这种方式。
        // 1. xml配置里的init-method 会被资源解析器解析后，存储到 beanDefinition里
        // 2. 由 beanName 取出 beanDefinition
        // 3. 由 beanDefinition 拿到 initMethod
        // 4. 然后 Method method = beanDefinition.getBeanClass.getMethod(initMethod);
        // 5. method.invoke(bean); 执行 initMethod 方法  =》其实就是执行了 我们写好的bean里的那些 initMethodName方法，比如 UserDao。
        method.invoke(demo21);

//        System.out.println("-----------method: " + method);

        Demo2 demo2 = Demo2.class.getDeclaredConstructor(String.class).newInstance("hello2");
//        System.out.println(demo2.str);

        Demo2 demo3 = (Demo2) Class.forName("com.fullstackboy.reflection.Demo2").getConstructor().newInstance();
//        System.out.println(demo3 instanceof Demo2);
    }
}
