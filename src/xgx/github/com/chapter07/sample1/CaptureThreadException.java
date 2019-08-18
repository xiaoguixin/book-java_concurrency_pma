package xgx.github.com.chapter07.sample1;

import java.util.concurrent.TimeUnit;

/**
 * 演示获取线程的异常
 * ExceptionHandler
 */
public class CaptureThreadException {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println(t.getName()+" occur exception.");
            e.printStackTrace();
        });

        final Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e){

            }

            // 出现Unchecked异常
            System.out.println(1/0);
        },"Test-thread");
        thread.start();
    }
}
