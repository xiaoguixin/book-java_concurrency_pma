package xgx.github.com.chapter08;

/**
 * 用于当Queue中的Runnable达到了limit上限时，决定采用什么策略通知提交者
 * 接口中定义了三种默认的实现
 */
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable,ThreadPool threadPool);

    /**
     * 直接丢弃
     */
    class DiscardDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }

    /**
     * 向任务提交者抛出异常
     */
    class AbortDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("The Runnable "+runnable+" will be abort.");
        }
    }

    /**
     * 拒绝任务者提交任务
     */
    class RunnerDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if(!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }
}
