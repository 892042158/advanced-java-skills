package top.xmindguoguo.java8.issue;

import java.util.concurrent.TimeUnit;

/**
 * 问题 3.假设有两个线程a，b 如何让a执行完了b执行，b执行完了 用A执行
 * 
 * @ClassName ThreadAB
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年2月28日 下午4:03:07
 *
 */
public class ThreadAB {
    public static void main(String[] args) {
        test1();
    }

    /**
     * 第一种办法，采用信号常量
     * 
     * @Title test1
     * @author 于国帅
     * @date 2019年2月28日 下午4:06:21 void
     */
    public static void test1() {
        // a执行完毕之后 然后b执行 b执行完毕 A执行
        Flag flag = new Flag(true);
        ThreadA ta = new ThreadA(flag);
        ThreadB tb = new ThreadB(flag);
        ta.start();
        tb.start();

    }

    /**
     * 方法2 采用 join
     * 
     * @Title test1
     * @author 于国帅
     * @date 2019年2月28日 下午4:06:21 void
     */
    public static void test2() {
        Thread tA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("A执行");
            }
        });
        Thread tB = new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("B行");
            }
        });
        tA.start();
        tB.start();
    }

    /**
     * 方法三 提交到线程池一个线程顺序执行
     * 
     * 
     * @Title test3
     * @author 于国帅
     * @date 2019年2月28日 下午5:25:13 void
     */
    public static void test3() {

    }

    /**
     * 方法四 使用 CountDownLatch
     * 
     * @Title test3
     * @author 于国帅
     * @date 2019年2月28日 下午5:25:13 void
     */
    public static void test4() {
//            CountDownLatch 
        // 怎么才能够执行下去呢？
    }
}

class Flag {
    private boolean status;

    public Flag(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}

class ThreadA extends Thread {
    Flag flag;

    public ThreadA(Flag flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) { // flag true时执行
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                // Auto-generated method stub
                e.printStackTrace();
            } // 5s
            if (flag.getStatus()) {

                System.err.println("A执行完毕");
                flag.setStatus(false);
            }

        }

    }
}

class ThreadB extends Thread {
    Flag flag;

    public ThreadB(Flag flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) { // flag false时执行
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                // Auto-generated method stub
                e.printStackTrace();
            } // 5s
            if (!flag.getStatus()) {

                System.err.println("B执行完毕");
                flag.setStatus(true);
            }
        }
    }
}
