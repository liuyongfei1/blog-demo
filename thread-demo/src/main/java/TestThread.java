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
}