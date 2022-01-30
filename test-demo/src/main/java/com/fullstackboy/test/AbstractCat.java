package com.fullstackboy.test;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/1/30 21:55
 */
public abstract class AbstractCat implements Animal{
    @Override
    public void sound() {
        System.out.println("~~~喵喵喵~~~" + this);
    }
}
