package xgx.github.com.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * 3.7 interrupt
 */
public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                // 休眠1分钟
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Oh,I am be interrupted.");
            }
        });
        thread.start();

        // 2毫秒后被打断
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
