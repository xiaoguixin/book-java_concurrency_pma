package xgx.github.com.chapter08;

/**
 * 提供创建线程到接口，便于个性化地定制Thread
 * 如：Thread被加到那个Group、设置优先级、设置线程名称等
 */
@FunctionalInterface
public interface ThreadFactory {

    Thread createThred(Runnable runnable);
}
