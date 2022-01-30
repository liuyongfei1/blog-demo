package com.fullstackboy.test;

import org.junit.Test;

/**
 * 测试 this 指向
 * 在本例中，WhiteCat 的构造函数中调用 sound()，
 * 在AbstractCat中的 this 指向的是 WhiteCat
 *
 * @author Liuyongfei
 * @date 2022/1/30 22:01
 */
public class Test1 {

    @Test
    public void test() {
        WhiteCat whiteCat = new WhiteCat("lucy");
        System.out.println(whiteCat);
    }
}
