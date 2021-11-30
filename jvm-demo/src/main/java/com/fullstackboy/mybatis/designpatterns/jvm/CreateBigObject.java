package com.fullstackboy.mybatis.designpatterns.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 该demo用来模拟在代码里创建一大堆的对象出来，然后尝试获取他的dump内存快照，再用MAT来进行分析。
 *
 * @author Liuyongfei
 * @date 2021/6/11 17:34
 */
public class CreateBigObject {
    public static void main(String[] args) throws Exception {
        List<Data> datas = new ArrayList<Data>();
        for (int i = 0; i < 10000; i++) {
            datas.add(new Data());
        }
        Thread.sleep(1 * 60 * 60 * 1000);
    }

    static class Data {

    }
}
