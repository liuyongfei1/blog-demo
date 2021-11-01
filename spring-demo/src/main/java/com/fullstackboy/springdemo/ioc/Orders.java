package com.fullstackboy.springdemo.ioc;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/11/1 09:14
 */
public class Orders {

    private String oNo;

    private String oAddress;

    public Orders(String oNo, String oAddress) {
        this.oNo = oNo;
        this.oAddress = oAddress;
    }

    public void printOrders() {
        System.out.println(oNo + ": " + oAddress);
    }

}
