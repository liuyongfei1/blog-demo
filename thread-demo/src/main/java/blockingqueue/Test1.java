package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列
 *
 * 第一组API：
 * add()、remove()、element() 会抛出异常
 * @author Liuyongfei
 * @date 2021/11/25 13:05
 */
public class Test1 {

    public static void main(String[] args) {
        ArrayBlockingQueue queue =  new ArrayBlockingQueue<>(3);

        // 第一组API，会抛出异常： add,remove,element
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
//        System.out.println(queue.add("d"));  // Exception in thread "main" java.lang.IllegalStateException: Queue full

        queue.remove();
        queue.remove();
        queue.remove();
//        queue.remove(); // Exception in thread "main" java.util.NoSuchElementException

//        System.out.println(queue.element()); // Exception in thread "main" java.util.NoSuchElementException
    }
}
