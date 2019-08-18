package xgx.github.com.chapter03;

/**
 * 3.1 sleep方法
 * 使当前线程进入指定的毫秒数的休眠，暂停执行但不会放弃monitor锁的所有权
 */
public class ThreadSleep {

    public static void main(String[] args) {
        // 自定义线程休眠
        new Thread(()->{
            long startTime = System.currentTimeMillis();
            sleep(2_000l);
            long endTime = System.currentTimeMillis();
            System.out.println(String.format("Total thread total spend %d ms",(endTime-startTime)));
        }).start();

        // 主线程休眠
        long startTime = System.currentTimeMillis();
        sleep(3_000l);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Main thread total spend %d ms",(endTime-startTime)));
    }

    private static void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
