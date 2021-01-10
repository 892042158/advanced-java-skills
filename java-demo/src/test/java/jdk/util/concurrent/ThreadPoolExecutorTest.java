package jdk.util.concurrent;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutorTest test = new ThreadPoolExecutorTest();
        test.testSum();
    }

    private int max = 10000 * 10000; // 1亿
    // 测试非异步计算 1-max 相加

    public void testSum() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int sum = 0;
        for (int i = 0; i <= max; i++) {
            sum += i;
        }
        stopWatch.stop();
        System.err.println("testSum总共用时" + stopWatch.getTime()); // 39s
        System.err.println("testSum总和值" + sum); // 987459712

    }

    // 测试异步计算1-max 相加
    public void testFutureSum() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        List<CallableTest> list = new ArrayList<>();
        CallableTest callableTest = null;
        for (int i = 0; i < max; i += 1000) { // 1,1000,1000,2000
            callableTest = new CallableTest(i + 1, i + 1000);
            list.add(callableTest);
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<Future<Integer>> futureList = threadPool.invokeAll(list);
        int sum = 0;
        for (Future<Integer> future : futureList) {
            sum += future.get();
        }
        stopWatch.stop();
        System.err.println("testSum总共用时" + stopWatch.getTime()); // 72s
        System.err.println("testSum总和值" + sum); // 987459712
    } // 887459712

    @Test
    public void execute() {
        // ThreadPoolExecutor
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(2);
        threadPoolExecutor.execute(new ThreadTest());
    }

    @Test
    public void submit() throws InterruptedException, ExecutionException {
        // ThreadPoolExecutor
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(2);
        Future future = threadPoolExecutor.submit(new ThreadTest());
        // 如果任务结束执行则返回 null
        System.out.println("future.get()=" + future.get());
    }

    public void submitCallable() throws InterruptedException, ExecutionException {
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(2);
        Future future = threadPoolExecutor.submit(new Callable() {
            public Object call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });
        System.out.println("future.get() = " + future.get());
    }

    public void shutdown() {
        // shutdown并不是直接关闭线程池，而是不再接受新的任务...如果线程池内有任务，那么把这些任务执行完毕后，关闭线程池....
    }

    public void shutdownNow() {
        // 这个方法表示不再接受新的任务，并把任务队列中的任务直接移出掉，如果有正在执行的，尝试进行停止...
    }
}

class ThreadTest implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(10); // 睡10秒
        } catch (InterruptedException e) {
            // Auto-generated method stub
            e.printStackTrace();
        }
        System.err.println(Thread.currentThread().getName());
    }

}

class CallableTest implements Callable<Integer> {
    public int min;
    public int max;

    public CallableTest(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (; min <= max; min++) {
            sum += min;
        }
        return sum;
    }

}
