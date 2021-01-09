package top.xmindguoguo.java8.issue.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class TimerTest {
    protected static Timer clearTimer;
    protected static Long period;

    public static void main(String[] args) {
        clearTimer = new Timer();
        period = 1000 * 60 * 60 * 24L;
        clearTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("执行的时间");
                } catch (Exception e) {
                    log.error("执行清理excel文件出错 ", e);
                }
            }
        }, 10, period); // 从10毫秒后 每天执行一次
    }
}
