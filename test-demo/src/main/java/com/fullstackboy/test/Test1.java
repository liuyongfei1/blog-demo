package com.fullstackboy.test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/4/15 13:58
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        // 获取并打印当前JVM是32位还是64位的
        String arch = System.getProperty("sun.arch.data.model");
//        System.out.println(arch + "-bit");  // 64-bit
//
//        ArrayBlockingQueue queue = new ArrayBlockingQueue(100);

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
//        System.out.println(list);

        LinkedList<Integer> list2 = new LinkedList<Integer>();
        list2.add(10);
        list2.add(20);
        list2.add(30);
//        System.out.println((list2.set(2,300))); // 返回修改前的值
//        System.out.println(list2);

        // 这个时候会出现，并发修改异常
        // java.util.
        // 原因：
        // public E next() {
        //            checkForComodification();
        //            if (!hasNext())
        //                throw new NoSuchElementException();
        //
        //            lastReturned = next;
        //            next = next.next;
        //            nextIndex++;
        //            return lastReturned.item;
        //        }

//        final void checkForComodification() {
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//        }
        Iterator<Integer> iterator = list2.iterator();
        while (iterator.hasNext()) {
            list2.add(40);
            Integer next = iterator.next();
            System.out.println(next);
        }

        // 解决办法：
//        ListIterator<Integer> iterator2 = list2.listIterator();
//        while (iterator2.hasNext()) {
//            iterator2.add(40);
//            Integer next = iterator2.next();
//            System.out.println(next);
//        }

        CopyOnWriteArrayList list3 = new CopyOnWriteArrayList();
        
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

        ReentrantLock lock= new ReentrantLock();

        ArrayBlockingQueue queue1 = new ArrayBlockingQueue(3);
        
        LinkedBlockingQueue<Integer> queue2 = new LinkedBlockingQueue();
        queue2.put(1);
        queue2.take();

        try {
            lock.lock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
