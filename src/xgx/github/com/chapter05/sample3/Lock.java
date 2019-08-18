package xgx.github.com.chapter05.sample3;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {

    /**
     * 永远阻塞，除非获取到锁
     * 可被中断，中断抛出异常
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 除了可被中断，还可以设置超时
     * @param mills
     * @throws InterruptedException
     * @throws TimeoutException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    /**
     * 进行锁到释放
     */
    void unlock();

    /**
     * 获取当前有哪些线程被阻塞
     * @return
     */
    List<Thread> getBlockedThreads();
}
