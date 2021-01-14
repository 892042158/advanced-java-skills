package org.springframework.util;

import org.junit.Test;

public class StopWatchTest {

    @Test
    public void test2() throws InterruptedException {
        StopWatch sw = new StopWatch();

        sw.start("起床");
        Thread.sleep(1000);
        sw.stop();

        sw.start("洗漱");
        Thread.sleep(2000);
        sw.stop();

        sw.start("锁门");
        Thread.sleep(500);
        sw.stop();

        System.out.println(sw.prettyPrint()); // 输出统计图表
        System.out.println(sw.getTotalTimeMillis()); // 3500
        System.out.println(sw.getLastTaskName()); // 锁门
        System.out.println(sw.getLastTaskInfo());// org.springframework.util.StopWatch$TaskInfo@6996db8
        System.out.println(sw.getTaskCount());// 3
    }

}
