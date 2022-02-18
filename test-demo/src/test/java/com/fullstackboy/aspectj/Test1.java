package com.fullstackboy.aspectj;

import org.junit.Test;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/2/10 10:30
 */
public class Test1 {

    @Test
    public void test() {
        Account account = new Account();
        System.out.println("================ 分割线 ==================");
        account.withDraw(10);
        account.withDraw(100);
        System.out.println("================ 结束 ==================");
    }
}
