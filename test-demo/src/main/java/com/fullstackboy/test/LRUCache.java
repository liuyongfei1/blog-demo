package com.fullstackboy.test;

import java.util.*;

/**
 * 最近最少使用
 *
 * @author zhanghao
 * @version 1.0
 * @date 2022-03-29 18:09
 */
public class LRUCache {

    private int cacheMaxSize = 0;
    private ArrayList<Integer> pages = null; // Interger means page id

    public LRUCache(int cacheMaxSize) {
        this.cacheMaxSize = cacheMaxSize;
        pages = new ArrayList<Integer>();
    }

    public void add(Integer p) {
        if (pages.contains(p)) {
            pages.remove(p);
            pages.add(p);
        } else if (pages.size() == cacheMaxSize) {
            pages.remove(0);
            pages.add(p);
        } else {
            pages.add(p);
        }
    }
    public static class LRU {
        private Scanner sc;
        private LRUCache lc = null;
        private ArrayList<Integer> pages = null;

        public LRU() {
            sc = new Scanner(System.in);
            pages = new ArrayList<Integer>();

            init();
            op();
        }

        private void init() {
            int key = -2;

            System.out.print("<LRU> 请输入物理块个数：");
            lc = new LRUCache(sc.nextInt());

            System.out.println("<LRU> 请按顺序输入页号(exit: -1): ");
            while (key != -1) {
                key = sc.nextInt();

                if (key > 0) {
                    pages.add(key);
                }
            }
        }

        private void op() {
            for (int i = 0; i < pages.size(); i++) {
                lc.add(pages.get(i));
            }
        }
        public void printLRU(){
            lc.print();
        }
    }
    private void print(){
       if(!Objects.isNull(pages)){
           pages.stream().forEach(
                   e-> System.out.println(e)
           );
       }
    }
    public static void main(String[] args) {
        LRU lru = new LRU();
        lru.printLRU();

    }
}
