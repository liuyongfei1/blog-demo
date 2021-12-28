package com.fullstackboy.designpatterns.singleton;

/**
 * 单例模式demo
 *
 * @author Liuyongfei
 * @date 2021/3/1 11:11
 */
public class SingletonPatternDemo {
    // 饿汉模式
//    public static class Singleton {
//        private static final Singleton instance = new Singleton();
//
//        private Singleton() {
//
//        }
//        public static Singleton getInstance() {
//            return instance;
//        }
//    }
    // 饱汉模式 + 线程安全
    public static class Singleton {
        private static Singleton instance;

        private Singleton() {

        }
        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                        return instance;
                    }
                }
            }
            return instance;
        }
    }
}
