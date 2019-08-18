package xgx.github.com.chapter05.sample2;

import java.util.concurrent.TimeUnit;

/**
 * 演示Synchronized关键字
 * 1. 阻塞时长不可控制
 * 2. 线程阻塞不可中断
 */
public class SynchronizedDefect {

    public synchronized void syncMethod(){
        try{
            // 休眠1小时
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDefect defect = new SynchronizedDefect();
        Thread t1 = new Thread(defect::syncMethod,"T1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);

        Thread t2 = new Thread(defect::syncMethod,"T2");
        t2.start();

        // t2中断不会抛出异常
        TimeUnit.MILLISECONDS.sleep(2);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
        System.out.println(t2.getState());

    }
}
