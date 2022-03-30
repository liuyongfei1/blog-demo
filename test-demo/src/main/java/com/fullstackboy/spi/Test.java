package com.fullstackboy.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 测试 Java 的SPI（全称 Service Provider Interface）机制
 * 是一种 Java 提供的服务发现机制
 * SPI的本质是将接口实现类的全限定名配置在文件中，然后由加载器读取文件里的内容，加载实现类。
 * 这样可以在运行时，动态为接口替换实现类。
 *
 * 在classpath下新建一个指定接口全限定名的文件，里面添加上接口实现类全限定名。
 * @author Liuyongfei
 * @date 2022/3/29 23:04
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Animal> serviceLoader = ServiceLoader.load(Animal.class);
        Iterator<Animal> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            System.out.println(animal.eat());
        }
    }
}
