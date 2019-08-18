package xgx.github.com.chapter03;

/**
 * 3.3 线程优先级
 * CPu忙时，优先级高的线程会获取更多的时间片，CPU空闲时线程优先级的高低不会有任何作用
 * 线程优先级不能小于1大于10
 */
public class ThreadPriority {

    public static void main(String[] args) {
        example3();
    }

    /**
     * 线程优先级介绍
     */
    public static void example1(){
        Thread t1 = new Thread(()->{
            while (true){
                System.out.println("t1");
            }
        });
        t1.setPriority(3);

        Thread t2 = new Thread(()->{
            while (true){
                System.out.println("t2");
            }
        });
        t2.setPriority(10);

        t1.start();
        t2.start();
    }

    /**
     * 线程的优先级不能大于线程组的优先级
     */
    public static void example2(){
        ThreadGroup group = new ThreadGroup("test");
        group.setMaxPriority(7);

        Thread thread = new Thread(group,"test-thread");
        thread.setPriority(10);

        System.out.println(thread.getPriority());// 输出7
    }

    /**
     * 默认优先级是5，派生出来的优先级也是5
     */
    public static void example3(){
        Thread t1 = new Thread();
        System.out.println("t1 priority "+t1.getPriority());

        Thread t2 = new Thread(()->{
            Thread t3 = new Thread();
            System.out.println("t3 priority "+t3.getPriority());
        });
        t2.setPriority(6);
        t2.start();
        System.out.println("t2 priority "+t2.getPriority());
    }
}
