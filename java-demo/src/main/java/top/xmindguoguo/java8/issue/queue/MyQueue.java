package top.xmindguoguo.java8.issue.queue;

import java.util.concurrent.LinkedBlockingQueue;

public class MyQueue {
    LinkedBlockingQueue<String> logQueue = new LinkedBlockingQueue<>(1); // 暂时只能放一个
    boolean isStop = true; // 设置这个值为false 即可停止线程

    private Thread thread = new Thread() {
        public void run() {
            while (isStop) {
                try {
                    String value = logQueue.take();
                    MyQueue.this.execute(value); // 获得值,然后做一些事情 MyQueue.this之所以用这个是匿名对象访问非静态方法
                    System.out.println(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    };

    MyQueue() {
        thread.start();
    }

    protected void execute(String value) {
        System.out.println(this.getClass().getSimpleName() + "执行" + value);
        try {
//            TimeUnit.MINUTES.sleep(1);
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }

    public LinkedBlockingQueue put(String value) {
        try {
            logQueue.put(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return logQueue;
    }

}
