package com.fullstackboy.designpatterns.jvm;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Arrays;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/3/3 18:16
 */
public class Demo3 {
    public static void main(String[] args) {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data[1] = 1;
        data[2] = 2;
        System.out.println(data.length);
        System.out.println(Arrays.toString(data));

    }

}
