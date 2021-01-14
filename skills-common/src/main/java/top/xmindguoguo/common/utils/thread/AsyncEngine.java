package top.xmindguoguo.common.utils.thread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

public class AsyncEngine {
    /**
     * 线程池的线程数量不需要太多，它是和CPU核数量有关，太多反而上下文切换效率不好
     */
    private static ExecutorService executorService = new ThreadPoolExecutor(2, 10, 30, TimeUnit.SECONDS, new SynchronousQueue());

    /**
     * 定时任务线程池
     */
    private static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(2);

    /**
     * 完成服务线程池
     */
    private static CompletionService completionService = new ExecutorCompletionService(executorService);

    /**
     * 异步执行任务，所有任务执行完成后该方法才返回，如果任务执行失败，该任务返回值为null
     * 
     * @param tasks
     *            待执行的任务
     * @param <T>
     *            任务的返回值
     * @return
     */
    public static <T> List<T> concurrentExecute(List<Callable<T>> tasks) {
        if (tasks == null || tasks.size() == 0) {
            return Lists.newArrayList();
        }
        List<T> res = Lists.newArrayList();
        try {
            List<Future<T>> futures = executorService.invokeAll(tasks);
            for (Future<T> future : futures) {
                T t = null;
                try {
                    t = future.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                res.add(t);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 定时执行任务
     * 
     * @param runnable
     *            执行的任务
     * @param initialDelay
     *            初始延时
     * @param delay
     *            任务间的等待时间
     * @param unit
     *            时间单位
     * @return
     */
    public static ScheduledFuture<?> scheduledWithFixedDelay(Runnable runnable, long initialDelay, long delay, TimeUnit unit) {
        return scheduledExecutorService.scheduleWithFixedDelay(runnable, initialDelay, delay, unit);
    }

    /**
     * 完成服务
     * 
     * @param tasks
     * @param <T>
     * @return
     */
    public static <T> CompletionService completionExecute(List<Callable<T>> tasks) {
        if (tasks == null || tasks.size() == 0) {
            return completionService;
        }
        for (Callable<T> task : tasks) {
            completionService.submit(task);
        }
        return completionService;
    }
}
