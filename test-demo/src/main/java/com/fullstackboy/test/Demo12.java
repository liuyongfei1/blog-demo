package com.fullstackboy.test;

import java.util.*;

/**
 * 1、使用List的迭代器 iterator
 * 2、使用Map的entrySet 遍历
 * @author Liuyongfei
 * @date 2022/3/23 10:02
 */
public class Demo12 {
    public static void main(String[] args) {

        // 1、遍历 List
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);
        ids.add(5L);

        Iterator<Long> iterator = ids.iterator();
//        while (iterator.hasNext()) {
//            Long next = iterator.next();
//            System.out.println("--->" + next);
//        }

        // 2、遍历Map
        HashMap<String,String> maps = new HashMap<String,String>();
        maps.put("a","a1");
        maps.put("b","b1");
        maps.put("c","c1");


        for (Map.Entry<String, String> entry : maps.entrySet()) {
            System.out.println("~~~~~~key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}
