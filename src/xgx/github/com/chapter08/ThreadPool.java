package xgx.github.com.chapter08;

/**
 * 自定义线程池接口
 */
public interface ThreadPool {

    // 获取线程池初始化大小
    int getInitSize();

    // 获取线程池核心线程数
    int getCoreSize();

    // 获取线程池最大线程数
    int getMaxSize();

    // 获取线程池中用于缓存任务队列的大小
    int getQueueSize();

    // 获取线程池中活跃的线程数量
    int getActiveCount();

    // 关闭线程池
    void shutdown();

    // 查看线程池是否已经被关闭
    boolean isShutdown();

    // 提交任务到线程池
    void execute(Runnable runnable);
}
