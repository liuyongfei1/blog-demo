import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMonitor implements Runnable {
    private int data;                    // 可设置通过构造传参
    private int control = 0;
    private static final int MAX = 3;    // 设置重试次数

    public ThreadMonitor(int i) {
        this.data = i;
    }
    public ThreadMonitor() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread arg0, Throwable e) {
                // TODO Auto-generated method stub
                System.out.println("==Exception: " + e.getMessage());
                String message = e.getMessage();
                if( control==MAX ){
                    return ;
                }else if( "ok".equals(message) ){
                    return ;
                }else if ( "error".equals(message) ) {
                    new Thread() {
                        public void run() {
                            try {
                                System.out.println("开始睡眠。");
                                Thread.sleep(1 * 1000);
                                control++ ;
                                System.out.println("睡眠结束，control: "+ control);
                                myTask(data) ;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        };
                    }.start();
                }else{
                    return ;
                }
            }
        });

        myTask(data) ;

    }

    @SuppressWarnings("finally")
    public void myTask(int data){
        boolean flag = true ;
        try {
            System.out.println(4 / data);
        } catch (Exception e) {
            flag = false ;
        } finally {
            if( flag ){
                throw new RuntimeException("ok");
            }else{
                throw new RuntimeException("error");
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ThreadMonitor threadMonitor = new ThreadMonitor(0);
        exec.execute(threadMonitor);
        exec.shutdown();
    }
}