package jdk.util.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    public static void main(String[] args) {
        testConcurrent();
    }

    /**
     * 1、模拟所有子线程都执行完成后再执行主线程 countdownLatch计数，模拟子线程执行完成之后再执行主线程 这个也可以用future来实现
     * 
     */
    private static void testWaitThread() {
        CountDownLatch latch = new CountDownLatch(2);
        ThreadA threadA = new ThreadA(latch, "A");
        ThreadA threadB = new ThreadA(latch, "B");
        threadA.start();
        threadB.start();
        try {
            System.err.println("等待两个线程执行完毕");
            latch.await();
            System.err.println("执行完毕");
            System.err.println("主线程回来了");

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * 模拟并发
     * 
     * @Title testConcurrent
     * @author 于国帅
     * @date 2019年3月1日 下午5:01:30 void
     */
    public static void testConcurrent() {
        final int guestNumber = 100; // 假设100个访问，每个访问一个线程处理
        CountDownLatch countDownLatc = new CountDownLatch(1);
        for (int i = 0; i < guestNumber; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatc.await();
                    } catch (InterruptedException e) {
                        // Auto-generated method stub
                        e.printStackTrace();
                    }
                    System.err.println(Thread.currentThread().getName());

                }
            }).start();
        }
        countDownLatc.countDown();
    }
}

class ThreadA extends Thread {
    CountDownLatch latch;
    public String name;

    public ThreadA(CountDownLatch latch, String name) {
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        System.err.println(name + "执行完毕");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            // Auto-generated method stub
            e.printStackTrace();
        }
        latch.countDown();
    }
}