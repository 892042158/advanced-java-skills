package top.xmindguoguo.java8.issue.queue;

import java.util.concurrent.LinkedBlockingQueue;

//重点多参考ld 的代码
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) {
        LinkedBlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
//        LinkedList<String> linkedList = new LinkedList<>();
        // 队列与List和Map的区别是什么
        // 模拟一个插入日志 场景 一个简单生产者消费者模型（这样的话都不会关闭）
        /* 访问了之后获取日志，放入队列，然后 如果队列里面有数据我就获取处理
         * 先主线程放入日志，然后异步获取队列的数据进行处理 
         */
        MyQueue queue = new MyQueue();
        queue.put("日志1");
        queue.put("日志2");
        queue.put("日志3");
        queue.put("日志4");
        queue.put("日志5");

//        queue.isStop = false; //线程取到的数据不一定是同步的，这样不一定能够完整的关闭啊
        // 解决方案 队列变为1个 这样一定会执行这一个, 或者数据传入时执行
        queue.put("日志6");
        new LinkedBlockingQueueDemo().printAllCurrentThread();
        // 简单化 弄一下

    }

    public void printAllCurrentThread() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        // 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
        // 获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slackList);
        // copy into a list that is the exact size
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
        System.out.println("Thread list size == " + list.length);
        for (Thread thread : list) {
            System.out.println(thread.getName());
        }
    }
}
