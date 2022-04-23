package com.fullstackboy.reflection;

/**
 * 如何通过反射来创建对象
 * 顺便思考： new关键字 和 newInstance()方法 的区别
 * https://blog.csdn.net/shipfei_csdn/article/details/81939616
 *
 * 1、加载方式不同
 *    在使用 newInstance()的时候，必须保证这个类已经加载并且已经连接了 =》而这可以通过 Class的静态方法 forName() 来完成的
 *    使用 new 的时候，这个类可以没有被加载，一般也不需要在classpath 中设定，但可能需要通过 classloader 来加载
 *
 * 2、所调用的构造方法不尽相同
 *    new 关键字能调用任何构造方法
 *    newInstance() 只能调用无参构造方法
 *
 * 3、既然 new 可以创建对象，那为什么又多出来个 newInstance()来创建对象呢？ =》解耦，提高软件的可伸缩性、可扩展性
 *
 *    假设定义了一个接口 Door
 *    3.1 开始的时候使用的是木门，定义一个类为：WoodenDoor。在程序里就要这样写：     Door door = new WoodenDoor();
 *    3.2 假设后来生活条件提高了，换为自动门了，定义一个类 AutoDoor，这时程序要改为： Door door = new AutoDoor();
 *
 *    3.3 虽然只是改个标识符，但是如果这样的语句特别多，改动还是挺大的。于是出现了工厂模式，所有Door的实例均由DoorFactory提供，
 *        这时，换一种门的时候，只需要把工厂的生产模式改一下，还是要改一点代码。
 *
 *    4. 而如果使用 newInstance()，则可以在不改变代码的情况下，换为另外一种Door。
 *       4.1 具体方法是把 Door的具体实现类的全类名放到配置文件中。示例代码如下；
 *
 *       String className = 从配置文件中读取Door的具体实现类名称;
 *       Door door = (Door) Class.forName(className).newInstance();
 *
 *       4.2 上面这段代码，就消灭了 WoodenDoor 类，AutoDoor类，无论 这些类怎么变化，上述代码不变，只要他们继承 Door接口即可。
 *
 * 1、通过类对象调用newInstance()方法  =》 适用于无参构造方法
 * @author Liuyongfei
 * @date 2022/1/8 21:25
 */
public class Demo1 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        // 一般我们不用反射的时候，是这样创建对象
        // Demo1 demo = new Demo1();

        // 使用反射的几种方式
        Demo1 demo1 = Demo1.class.newInstance();

        Demo1 demo2 = demo1.getClass().newInstance();

        Class demoClass = Class.forName("com.fullstackboy.reflection.Demo1");
        Demo1 demo3 = (Demo1) demoClass.newInstance();

        System.out.println(demo1 instanceof Demo1);
        System.out.println(demo2 instanceof Demo1);
        System.out.println(demo3 instanceof Demo1);
    }
}
