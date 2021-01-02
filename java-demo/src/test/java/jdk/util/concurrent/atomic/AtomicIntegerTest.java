package jdk.util.concurrent.atomic;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class AtomicIntegerTest {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    class Visitor extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                atomicInteger.incrementAndGet();// 原子性方式+1
                Thread.yield();
            }
        }
    }

    @Test
    public void test() throws InterruptedException, IOException {
        int num = 100;
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new Visitor();
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            threads[i].join();
        }
        System.in.read();
        System.out.println(atomicInteger.get());
    }
}
