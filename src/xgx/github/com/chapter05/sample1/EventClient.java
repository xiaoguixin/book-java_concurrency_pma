package xgx.github.com.chapter05.sample1;

import java.util.concurrent.TimeUnit;

/**
 * 使用两个线程测试增加和删除队列
 */
public class EventClient {

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(()->{
            for(;;){
                eventQueue.offer(new EventQueue.Event());
            }
        },"Producer").start();

        new Thread(()->{
            for(;;){
                eventQueue.take();
                try{
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();}
}
