package top.xmindguoguo.java8.issue.thread;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 简单的生产者 消费者模式
 * 
 * @ClassName ProducerAndConsumerDemo
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年2月25日 上午11:41:15
 *
 */
public class ProducerAndConsumerDemo {
    // 1.先重写一个队列
    // 2.生产者线程 随机根据时间生产产品 假设每10秒生产一个
    // 3.消费者线程 假设每5秒消费一个 运行两个消费者
    // 主线程运行
    // 推测运行 先输出消费1,2等待 ，然后输出生产，然后消费等待
    // 然后如此3次之后 ，修改为 每2秒生产一个，此时 出现 生产过多 需要等待

    public static void main(String[] args) {
        // 主线程运行
        int limit = 10;
        MyBlockingQueue<String> queue = new MyBlockingQueue(limit);
        ProducerThread producerThread = new ProducerThread(queue);
        producerThread.start(); // 生产者开始生产
        // 消费者开始消费
        ConsumerThread consumerThread1 = new ConsumerThread(queue);
        consumerThread1.setName("消费者1");
        consumerThread1.start();
        ConsumerThread consumerThread2 = new ConsumerThread(queue);
        consumerThread2.setName("消费者2");
        consumerThread2.start();
        // 当产品冗余 大于5 的时候 停掉消费者1
        while (queue.size() > 5) { // 估计
//            consumerThread1.interrupt(); // 并不是真正的停掉，还是自己用标志位把
            consumerThread1.flagStop();
            System.err.println("仓库超过一半存量了停掉消费者1");
        }
        while (queue.size() == limit) {
//            producerThread.interrupt();
            producerThread.flagStop();
            System.err.println("仓库满了，停掉仓库");
        }
        // 估测答案
        // 第一分钟 生产了6 个产品 第二分钟 生产30个产品 36 3分钟 66个 96个
        // 消费者 群体 开始消费 两个人 1分钟消费 12*2 = 24 个产品 48个 3分钟72个 96个

        // 也就是说 4分钟 全部条件都会输出

    }

}

// 1.先重写一个队列
class MyBlockingQueue<E> {

    private int limit; // 生产过多时的限制长度
    private Queue<E> queue;

    public int size() {
        Objects.requireNonNull(queue);
        return queue.size();
    }

    public MyBlockingQueue(int limit) {
        this.limit = limit;
        queue = new LinkedBlockingDeque<>(limit);
    }

    // 生产者 存放货物
    public synchronized void put(E e) throws InterruptedException {
        // 先判断是否满仓库了
        while (this.queue.size() >= limit) {
            wait(); // 释放对象锁，阻塞等待
            System.err.println("仓库满了，货物===" + String.valueOf(e) + "===等待进入仓库中");
        }
        this.queue.add(e);
        notifyAll(); // 唤醒消费者
    }

    // 消费者拿货
    public synchronized E take() throws InterruptedException {
        // 先判断是否仓库没货了
        while (this.queue.isEmpty()) {
            wait(); // 释放对象锁，阻塞等待
            System.err.println("仓库没货了 ");
        }
        E e = this.queue.poll();
        notifyAll();
        return e;
    }

}

//2.生产者线程 随机根据时间生产产品 假设每10秒生产一个    1分钟之后改变策略  没2秒生产一个 
class ProducerThread extends Thread {
    MyBlockingQueue<String> queue;
    private boolean stopFlag = false; // 默认不停

    public ProducerThread(MyBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (!stopFlag) {
                String task = String.valueOf(num);
                queue.put(task);
                System.out.println("produce task " + task);
                num++;
                // 消费者 前一分钟 10秒一个 后面 每两秒一个
                if (num > 6) {
                    TimeUnit.SECONDS.sleep(2);
                } else {
                    TimeUnit.SECONDS.sleep(2);
                }
//                Thread.sleep((int) (Math.random() * 100));
            }
        } catch (InterruptedException e) {
        }
    }

    public void flagStop() {
        this.stopFlag = true;
    }
}

// 3.消费者线程 假设每5秒消费一个 运行两个消费者
class ConsumerThread extends Thread {
    // 突然觉菜的难受 和刚开始不再出名公司的差距
    MyBlockingQueue<String> queue;
    private boolean stopFlag = false; // 默认不停

    public ConsumerThread(MyBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        // 消费者 运行开始消耗
        while (!stopFlag) { // 消费者 可以手动停掉，所以设置标志位
            String task;
            try {
                task = this.queue.take();
                TimeUnit.SECONDS.sleep(5); // 每5秒消费一个
                System.err.println(Thread.currentThread().getName() + "===拿掉了仓库货物===" + task);
            } catch (InterruptedException e) {
                // Auto-generated method stub
                e.printStackTrace();
            }
        }
    }

    public void flagStop() {
        this.stopFlag = true;
    }
}
