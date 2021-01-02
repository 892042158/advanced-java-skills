package jdk.lang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) throws Exception {
        ThreadTest test = new ThreadTest();
        test.test2();
    }

    Object o = new Object();

    public void holdsLock() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    System.out.println("child thread: holdLock: " + Thread.holdsLock(o));
                }
            }
        }).start();
        System.out.println("main thread: holdLock: " + Thread.holdsLock(o));
        Thread.sleep(2000);

    }

    // 有三个线程T1，T2，T3，怎么确保它们按顺序执行？ 方法1
    public void test1() throws Exception {
        T1 t1 = new T1();
        T2 t2 = new T2();
        T3 t3 = new T3();
        t1.start();
        t1.join();
        t2.start();
        t1.join();
        t3.start();
        t1.join();

    }

    // 有三个线程T1，T2，T3，怎么确保它们按顺序执行？ 方法2
    // 一个线程执行三个任务
    public void test2() throws Exception {
        T1 t1 = new T1();
        T2 t2 = new T2();
        T3 t3 = new T3();
        // 创建一个单例的线程池 ，根据 队列来执行
        ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
        threadPoolExecutor.submit(t2);
        threadPoolExecutor.submit(t3);
        threadPoolExecutor.submit(t1);
        // 执行完毕 然后关闭线程池
        threadPoolExecutor.shutdown();
        // 立即关闭 shutdownNow()
    }

    // 有三个线程T1，T2，T3，怎么确保它们按顺序执行？
    // 方法三 一个线程运行结束了，在run方法里面启动另一个 ，这样用没啥意义
    public void test3() throws Exception {

    }

    // 有三个线程T1，T2，T3，怎么确保它们按顺序执行？
    // 方法四 定义一个 同时启动
    public void test4() throws Exception {
        T4[] ts = new T4[3];
        int len = ts.length;
        for (int i = 0; i < len; i++) {
            ts[i] = new T4();
            ts[i].setName("线程" + i);
        }
        for (int i = 0; i < len; i++) {
            ts[i].start();
            ts[i].join();
        }
    }

    // 有三个线程T1，T2，T3，怎么确保它们按顺序执行？
    // 方法五 应用场景 具体实战 感觉应该是一个线程基于队列来执行任务，不对 队列执行的是相同的方法
    // 而线程是不同的方法，那么为什么不能够拆分呢
//    使用多线程是为了提高程序运行的效率。假如有一个程序，要求用户输入多个算式，计算出结果，并分别打印到屏幕上。如果用户一直没有输入，那么无法计算，更无法打印。如果用户输入了，必须要全部输入完，才能计算出结果，再打印到屏幕。
//    使用线程的话，一个线程用来等待用户输入，一个用来计算结果，一个用来打印。用户在输入算式3的时候，计算线程在计算算式2，打印线程在打印算式1，三个线程同时进行，减少了等待，这样就提高了运行效率
    public void test5() throws Exception {

    }

    // 有时候并不是在任何场景下使用线程池，效率都比顺序执行程序快，请看下面例子程序：
    public void threadPoolExecutorTest() {

    }

}

class WaitObject {

}

class T4 extends Thread {

    @Override
    public void run() {
        System.err.println(Thread.currentThread().getName());
    }
}

class T1 extends Thread {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            // Auto-generated method stub
            e.printStackTrace();
        }
        System.err.println(this.getClass().getName());
    }
}

class T2 extends Thread {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            // Auto-generated method stub
            e.printStackTrace();
        }
        System.err.println(this.getClass().getName());
    }
}

class T3 extends Thread {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            // Auto-generated method stub
            e.printStackTrace();
        }
        System.err.println(this.getClass().getName());
    }
}