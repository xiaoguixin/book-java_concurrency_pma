package xgx.github.com.chapter05.sample3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;
import static java.lang.System.currentTimeMillis;

/**
 * 显式锁具备中断和超时的功能
 */
public class BooleanLock implements Lock{

    // 当前拥有锁的线程
    private Thread currentThread;

    // 开关
    private boolean locked = false;

    // 线程阻塞列表
    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){
                final Thread tempThread = currentThread();
                try {
                    if(!blockedList.contains(tempThread)){
                        blockedList.add(tempThread);
                    }
                    this.wait();
                } catch (InterruptedException e){
                    blockedList.remove(tempThread);
                    throw e;
                }
            }

            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills<=0){
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = remainingMills + currentTimeMillis();

                while (locked){
                    if(remainingMills<=0){
                        throw new TimeoutException("can not get the lock during "+ mills +" ms.");
                    }

                    if(!blockedList.contains(currentThread())){
                        blockedList.add(currentThread());
                    }

                    this.wait(remainingMills);
                    remainingMills = endMills - currentTimeMillis();
                }

                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            if(currentThread == currentThread()){
                this.locked = false;
                Optional.of(currentThread().getName()+" release the lock.")
                    .ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
