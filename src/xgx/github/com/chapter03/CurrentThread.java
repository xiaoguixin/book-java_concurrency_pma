package xgx.github.com.chapter03;

/**
 * 3.5 获取当前线程
 */
public class CurrentThread {

    public static void main(String[] args) {

        Thread thread = new Thread(){

            @Override
            public void run(){
                System.out.println(Thread.currentThread() == this);
            }
        };
        thread.start();

        String name = Thread.currentThread().getName();
        System.out.println("main".equals(name));
    }
}
