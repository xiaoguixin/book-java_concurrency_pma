package xgx.github.com.chapter05.sample3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * 多个线程通过lock方法竞争锁
 */
public class BooleanLockTest {

    private final Lock lock = new BooleanLock();

    /**
     * 可中断
     */
    public void syncMethod(){
        try {
            lock.lock();
            int randomInt = current().nextInt(10);
            System.out.println(currentThread()+" get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 可中断可超时
     */
    public void syncMethodTimeoutable(){
        try {
            lock.lock(1000);
            int randomInt = current().nextInt(10);
            System.out.println(currentThread()+" get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest blt = new BooleanLockTest();

        int caseType = 2;
        if(caseType==1){// 演示可中断
            new Thread(blt::syncMethod,"T1").start();
            TimeUnit.MILLISECONDS.sleep(2);
            Thread t2 = new Thread(blt::syncMethod,"T2");
            t2.start();
            TimeUnit.MILLISECONDS.sleep(10);
            t2.interrupt();
        } if(caseType==2){// 演示可中断可超时
            new Thread(blt::syncMethod,"T1").start();
            TimeUnit.MILLISECONDS.sleep(2);
            Thread t2 = new Thread(blt::syncMethodTimeoutable,"T2");
            t2.start();
            TimeUnit.MILLISECONDS.sleep(10);
        } else {// 定义多个线程并启动
            IntStream.range(0,10)
                    .mapToObj(i->new Thread(blt::syncMethod))
                    .forEach(Thread::start);
        }
    }
}
