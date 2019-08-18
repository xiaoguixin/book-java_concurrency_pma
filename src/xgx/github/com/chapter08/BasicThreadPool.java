package xgx.github.com.chapter08;

import java.util.ArrayDeque;
import java.util.Queue;

public class BasicThreadPool extends Thread implements ThreadPool {
    @Override
    public int getInitSize() {
        return 0;
    }

    @Override
    public int getCoreSize() {
        return 0;
    }

    @Override
    public int getMaxSize() {
        return 0;
    }

    @Override
    public int getQueueSize() {
        return 0;
    }

    @Override
    public int getActiveCount() {
        return 0;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public void execute(Runnable runnable) {

    }

/*
    private final int initSize;
    private final int maxSize;
    private final int coreSize;
    private final int activeCount;
    private final ThreadFactory threadFactory;
    private final RunnableQueue runnableQueue;
    private volatile boolean isShutdown = false;
    private final Queue<InternalTask> threadQueue = new ArrayDeque<>();
    private final static ThreadFactory DEFAUL_THREAD_FACTORY ;

    @Override
    public int getInitSize() {
        return 0;
    }

    @Override
    public int getCoreSize() {
        return 0;
    }

    @Override
    public int getMaxSize() {
        return 0;
    }

    @Override
    public int getQueueSize() {
        return 0;
    }

    @Override
    public int getActiveCount() {
        return 0;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public void execute(Runnable runnable) {

    }*/
}
