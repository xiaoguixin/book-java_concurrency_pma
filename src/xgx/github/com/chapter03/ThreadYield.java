package xgx.github.com.chapter03;

import java.util.stream.IntStream;

/**
 * 3.2 yield方法
 * 放弃当前的CPU资源，使当前线程从RUNNING状态切换到RUNNABLE状态
 * 如果CPU资源的资源不紧张，则会放弃操作
 */
public class ThreadYield {

    public static void main(String[] args) {
        IntStream.range(0,2)
                .mapToObj(ThreadYield::create)
                .forEach(Thread::start);
    }

    private static Thread create(int index){
        return new Thread(()->{
            if(index == 0)
                Thread.yield();
            System.out.println(index);
        });
    }
}
