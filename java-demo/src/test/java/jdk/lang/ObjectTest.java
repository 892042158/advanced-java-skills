package jdk.lang;

import org.junit.Test;

public class ObjectTest {
    @Test
    public void waitAndNotify() throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(1000);
        System.out.println("fire");
        waitThread.fire();
    }
}

class WaitThread extends Thread {
    private volatile boolean fire = false;

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (!fire) {
                    wait();
                }
            }
            System.out.println("fired");
        } catch (InterruptedException e) {
        }
    }

    public synchronized void fire() {
        this.fire = true;
        notify();
    }
}