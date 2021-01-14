package top.xmindguoguo.common.utils.thread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;

public class AsyncEngineTest {
    /**
     * 多线程并发计算
     * 
     * @param min
     * @param max
     * @return
     */
    public static long sum1(int min, int max) {
        if (min > max) {
            return 0L;
        }
        List<Callable<Long>> tasks = Lists.newArrayList();
        while (min < max) {
            final int left = min;
            final int right = max;
            // 分割任务，每个任务最多只相加1000个数
            Callable<Long> task = new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    long sum = 0;
                    int r = (left + 1000) < right ? (left + 1000) : right;
                    for (int i = left; i < r; i++) {
                        sum += i;
                    }
                    return sum;
                }
            };
            tasks.add(task);
            min += 1000;
        }
        // 异步执行，执行完成后该方法返回
        List<Long> res = AsyncEngine.concurrentExecute(tasks);
        long sum = 0;
        // 归并结果
        for (Long count : res) {
            sum += count;
        }
        return sum;
    }

    /**
     * 单线程计算
     * 
     * @param min
     * @param max
     * @return
     */
    public static long sum2(int min, int max) {
        long sum = 0;
        for (int i = min; i < max; i++) {
            sum += i;
        }
        return sum;
    }

    /**
     * 多线程并发计算
     * 
     * @param min
     * @param max
     * @return
     */
    public static long sum3(int min, int max) {
        if (min > max) {
            return 0L;
        }
        List<Callable<Long>> tasks = Lists.newArrayList();
        while (min < max) {
            final int left = min;
            final int right = max;
            // 分割任务，每个任务最多只相加1000个数
            Callable<Long> task = new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    long sum = 0;
                    int r = (left + 1000) < right ? (left + 1000) : right;
                    for (int i = left; i < r; i++) {
                        sum += i;
                    }
                    return sum;
                }
            };
            tasks.add(task);
            min += 1000;
        }
        // 使用CompletionService执行任务
        CompletionService<Long> completionService = AsyncEngine.completionExecute(tasks);
        long sum = 0;
        for (int i = 0; i < tasks.size(); i++) {
            try {
                sum += completionService.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sum1(4, 9999));
        System.out.println(sum2(4, 9999));
        System.out.println(sum3(4, 9999));

    }

}
