/**
 * 模拟开启单独线程的任务
 *
 * @author Liuyongfei
 * @date 2020/6/10 14:50
 */
class TestThread implements Runnable {
    private ResponseCallBack responseCallBack;

    public TestThread(ResponseCallBack responseCallBack) {
        this.responseCallBack = responseCallBack;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " start execute");
            Thread.sleep(10000L);
            System.out.println(Thread.currentThread().getName() + " end execute");
            this.responseCallBack.printMsg("Hello Call Back");
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 第一种方式
        class Thread1 extends Thread {

            @Override
            public void run() {
                System.out.println("执行代码...");
            }
        }
        new Thread1().start();

        // 第二种方式
        new Thread(() -> {
            System.out.println("执行代码......");
        },"线程2").start();

        // 第三种方式
        class MyRunnable implements Runnable {

            @Override
            public void run() {

            }
        }
        new Thread(new MyRunnable()).start();

        // 第四种方式
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
