package com.fullstackboy.aspectj;

public aspect AccountAspect {
    final int MIN_BALANCE = 10;

    /*
    	定义切点，对应 Pointcut
    	这里的切点定义在调用 Account 对象在调用 witdraw 方法
    */
    pointcut callWithDraw(int amount, Account acc):
            call(boolean Account.withdraw(int)) && args(amount) && target(acc);

    /*
    	在上文定义的切点执行之前采取的行为，这就被称之为 Advice
    */
    before(int amount, Account acc): callWithDraw(amount, acc) {
        System.out.println("[AccountAspect] 付款前总额: " + acc.balance);
        System.out.println("[AccountAspect] 需要付款: " + amount);
    }

    /*
    	在对应的切点执行前后采取的行为
    */
    boolean around(int amount, Account acc):
            callWithDraw(amount, acc) {
        if (acc.balance < amount) {
            System.out.println("[AccountAspect] 拒绝付款！");
            return false;
        }
        return proceed(amount, acc);
    }

    /*
    	对应的切点执行后的采取的行为
    */
    after(int amount, Account balance): callWithDraw(amount, balance) {
        System.out.println("[AccountAspect] 付款后剩余：" + balance.balance);
    }
}
