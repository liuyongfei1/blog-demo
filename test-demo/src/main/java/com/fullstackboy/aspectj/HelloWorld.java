package com.fullstackboy.aspectj;

/**
 * 测试aspectJ
 *
 * @author Liuyongfei
 * @date 2022/2/10 12:43
 */
public class HelloWorld {
    public void sayHello(){
        System.out.println("hello world1 !");
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }
}
