package top.xmindguoguo.java8.issue.thread;

/**
 * 死锁的例子
 * 
 * @ClassName DeadLockDemo
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年2月25日 上午10:58:26
 * @see https://www.cnblogs.com/swiftma/p/6399548.html
 */
public class DeadLockDemo {
    private static Object lockA = new Object();
    private static Object lockB = new Object();

    private static void startThreadA() {
        Thread aThread = new Thread() {

            @Override
            public void run() {
                synchronized (lockA) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    synchronized (lockB) {
                    }
                }
            }
        };
        aThread.start();
    }

    private static void startThreadB() {
        Thread bThread = new Thread() {
            @Override
            public void run() {
                synchronized (lockB) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    synchronized (lockA) {
                    }
                }
            }
        };
        bThread.start();
    }

    public static void main(String[] args) {
        startThreadA();
        startThreadB();
        System.err.println("222");
    }
}
