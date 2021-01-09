package top.xmindguoguo.java8.issue.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//@简单总结一下使用
// 1.创建一个队列类 继承 NoRecoverRunningQueue 第一个参数是初始队列的大小，第二个参数是延时队列的使用时间
// 2.数据put进去，默认key重复的会采用最新对应的value，doing()做你队列取出来数据的事情
// 3.如果需要对重复的model 进行属性的处理,则重写mergeModel()
// 4.如果需要put进去对数据的队列执行些动洗saveQuque()

//复习一下思路
//1.创一个拥有类 拥有队列和map 队列的数据取出来放在map 然后循环 
//要是自己写会怎么写？
//先模仿这样一个类，然后用线程去获取，通过队列来阻塞 run方法  
//无需恢复执行队列（每个程序重新启动，不会自动加载上次未执行完数据）
@Slf4j
public abstract class NoRecoverRunningQueue<T> {

    private BlockingQueue<QueueEntry<T>> queue; // 执行的队列
    private Map<String, T> modelMap = new HashMap<String, T>();

    private long period = 0;
    private volatile boolean isShutdown = false;
    private volatile boolean isRunning = true;

    private Thread thread = new Thread() {
        private int tryCount = 0;// 记录modelMap为空时处理线程的尝试次数

        public void run() {
            while (true) {
                try {
                    queue2Map(); // 队列的数据保存到map 中 ， map key一样的model默认选择最新的model

                    if (modelMap.isEmpty()) { // 为什么map 为空的时候还要尝试呢？
                        if (isShutdown) { // 若果队列结束了 就执行
                            log.warn("队列“" + NoRecoverRunningQueue.this.getClass().getSimpleName() + "”执行完毕！");
                            return;
                        } else {
                            tryCount++;
                            if (tryCount == 10) {// 最多尝试10次（尽量让处理线程不wait）
                                synchronized (thread) {// 必须先synchronized
                                    isRunning = false;
                                    log.info("■■处理线程" + NoRecoverRunningQueue.this.getClass().getSimpleName() + "开始wait()...");
                                    try {
                                        wait();
                                    } catch (Exception e) {// 捕获所有wait方法可能抛出的异常
                                    }
                                    isRunning = true;// 执行notify时wait方法不抛异常，所以标识位在此处进行修改
                                    tryCount = 0;// 继续执行并重置tryCount
                                    log.info("■■处理线程" + NoRecoverRunningQueue.this.getClass().getSimpleName() + "结束wait()，继续执行！");
                                }
                            } else {
                                Thread.sleep(1000);// 休眠1秒
                            }
                            continue;
                        }
                    } else {
                        tryCount = 0;// modelMap不为空则重置tryCount
                    }

                    String[] keys = modelMap.keySet().toArray(new String[0]);
                    for (String key : keys) {
                        T model = modelMap.remove(key);
                        if (model != null) {
                            NoRecoverRunningQueue.this.doing(model); // 如果model 不为空 执行什么什么操作
                            saveModelMap(modelMap); // 对剩余的modelMap 做些事情
                        }
                    }
                    if (period != 0) { // 执行方法的等待时间 （为什么要暂停一段时间呢？
                        /*
                         * 在实际的业务中会遇到如下场景：
                        1）过1分钟失败任务重试
                        2）过1小时发送邮件
                         */
                        Thread.sleep(period);
                        log.info("队列" + NoRecoverRunningQueue.this.getClass().getSimpleName() + "按要求暂停一段时间" + period + "毫秒");
                    }
                } catch (InterruptedException e) {
                } catch (Exception e) {
                    log.error("队列“" + NoRecoverRunningQueue.this.getClass().getSimpleName() + "”执行发生异常。", e);
                }
            }
        }
    };

    /**
     * 默认创建一个队列长度为10000，线程数为1的自动运行队列
     */
    protected NoRecoverRunningQueue() {
        this(10000);
    }

    protected NoRecoverRunningQueue(int queueSize) {
        this(queueSize, 0);
    }

    protected NoRecoverRunningQueue(int queueSize, int period) {
        queue = new LinkedBlockingQueue<QueueEntry<T>>(queueSize);
        this.period = period;
        thread.start();
        NoRecoverRunningQueue.log.info("队列“" + getClass().getSimpleName() + "”创建，执行队列开始运行...");
    }

    protected void init(Map<String, T> modelMap, List<QueueEntry<T>> queue) {
        if (modelMap != null) {
            this.modelMap.putAll(modelMap);
        }
        if (queue != null && queue.size() > 0) {
            this.queue.addAll(queue);
        }
        synchronized (thread) {// 必须先synchronized
            thread.notify();// 唤醒
        }
    }

    // 将queue放入Map
    private void queue2Map() {
        log.info("处理队列" + NoRecoverRunningQueue.this.getClass().getSimpleName() + "，当前长度为：" + queue.size());
        for (int i = 0, len = queue.size(); i < len; i++) {
            try {
                QueueEntry<T> queueEntry = queue.take();
                T newModel = queueEntry.getModel();
                T model = newModel;
                T oldModel = modelMap.get(queueEntry.getKey()); // 是否合并model
                if (oldModel != null) {
                    model = mergeModel(oldModel, newModel); // 采用新model
                }
                String key = queueEntry.getKey();
                modelMap.put(key, model);
                saveQuque(queue); // 保存之前做点事? 现在是空的
                saveModelMap(modelMap); // 保存之前做点事? 现在是空的
            } catch (InterruptedException e) {
            }
        }
    }

    public boolean put(String key, T model) {
        if (isShutdown) {
            log.warn("执行队列" + NoRecoverRunningQueue.this.getClass().getSimpleName() + "已经关闭，无法添加新记录");
            return false;
        }
        try {
            queue.put(new QueueEntry<T>(key, model));
            saveQuque(queue);
            if (!isRunning) {// 如果处理线程没有在执行（执行了wait）
                synchronized (thread) {// 必须先synchronized
                    log.info("◆唤醒执行队列" + NoRecoverRunningQueue.this.getClass().getSimpleName());
                    thread.notify();// 唤醒
                }
            }
            return true;
        } catch (InterruptedException e) {
            log.error("", e);
            return false;
        }
    }

    // 如果队列中key进行合并操作，默认采用新数据，可重写
    protected T mergeModel(T oldModel, T newModel) {
        return newModel;
    }

    public boolean isEmpty() {
        return queue.isEmpty() && modelMap.isEmpty();
    }

    public int size() {
        return queue.size() + modelMap.size();
    }

    protected void saveQuque(BlockingQueue<QueueEntry<T>> queue) {
    }

    protected void saveModelMap(Map<String, T> modelMap) {
    }

    public void shutdown() {
        isShutdown = true;
        synchronized (thread) {// 必须先synchronized
            thread.notify();// 唤醒
        }
    }

    // 每次执行方法
    protected abstract void doing(T model) throws Exception;

    public Map<String, T> getModelMap() {
        return modelMap;
    }

    protected void clearModelMap() {
        modelMap.clear();
    }
}