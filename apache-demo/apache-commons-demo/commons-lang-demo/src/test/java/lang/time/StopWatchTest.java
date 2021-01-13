package lang.time;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;

public class StopWatchTest {
//    /** 
//     * @start(); //开始计时 
//     * @split(); //设置split点 
//     * @getSplitTime(); //获取从start 到 最后一次split的时间 
//     * @reset(); //重置计时 
//     * @suspend(); //暂停计时, 直到调用 
//     * @resume(); //恢复计时 
//     * @stop(); //停止计时  
//     * @getTime(); //统计从start到现在的计时 
//     */  

    @Test
    public void test() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(2000);
        stopWatch.stop();
        System.err.println(stopWatch.getTime());

    }

}
