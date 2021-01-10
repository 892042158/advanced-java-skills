package jdk.util.concurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TimeUnitTest {
    // 时间转换单位，看实现的源码因为用的Long 所以小数部分会自动干掉了
    @Test
    public void convert() {
        // 10m秒转化为纳秒
        long CONN_TIME_OUT = TimeUnit.NANOSECONDS.convert(10, TimeUnit.SECONDS);
        System.err.println(CONN_TIME_OUT);
        // 1小时转化成分钟
        System.err.println("输出1小时转化成分钟");
        System.err.println(TimeUnit.MINUTES.convert(1, TimeUnit.HOURS));

    }

    // 沉睡一定时间
    @Test
    public void sleep() throws InterruptedException {
        System.err.println("延迟1秒输出");
        TimeUnit.SECONDS.sleep(1);
        // 内部调用的 以纳秒分割 500000 也就是说以半毫秒来控制延迟
        // 测试一下 纳秒这些是多少时间
        System.err.println(TimeUnit.NANOSECONDS.toSeconds(500000L)); // 竟然是0 才发现竟然不能保留小数部分
        // 那么输出 1毫秒是多少纳秒把
        System.err.println(TimeUnit.MILLISECONDS.toNanos(1));

    }

    // values()
//    按照它们声明的顺序返回一个包含此枚举类型常量的数组
    @Test
    public void values() {
        TimeUnit[] timeUnits = TimeUnit.values();
        System.err.println(Arrays.toString(timeUnits));
        // [NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS, MINUTES, HOURS, DAYS]

    }

    // 让父线程等待子线程结束之后才能继续运行。
    @Test
    public void timedJoin() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("timedJoin");
            }
        });

        TimeUnit.MINUTES.timedJoin(thread, 60); // 等待一分钟后输出
        thread.start(); // 会先等当前线程执行完毕 然后执行线程的任务
        System.err.println("1");
    }

    // 此处需要使用synchronized关键字将方法锁住
    public synchronized void show() throws InterruptedException {
        // 下意识联想到生产者消费者模型
        TimeUnit.SECONDS.timedWait(this, 2);
        System.out.println("HelloWorld");
    }

    // timedWait
    public static void main(String[] args) throws InterruptedException {
        TimeUnitTest test = new TimeUnitTest();
        // 两秒之后执行
        test.show();
    }

}
