package jdk.util.concurrent.locks;

import org.junit.Test;
import top.xmindguoguo.common.utils.CmUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    Lock lock = new ReentrantLock();
    int count = 0;

//    //错误的
//    public int countSum() {
//        return ++count;
//    }
//  //正确的
    public int countSum() {
        return ++count;
    }
    // 正确的
//    public int countSum() {
//        try {
//            lock.lock();
//            return ++count;
//        } catch (Exception e) {
//            // TODO: handle exception
//        } finally {
//            lock.unlock();
//        }
//        return 0;
//
//    }

    @Test
    public void testCountSum() throws IOException {
        Thread[] threads = new Thread[100000];
        List<Long> tiemList = new ArrayList<>();
        for (int i = 0, len = threads.length; i < len; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    long pkId = countSum();
                    System.err.println(pkId);
                }
            });
        }
        for (int i = 0, len = threads.length; i < len; i++) {
            threads[i].start();
        }
        System.in.read();
        System.out.println("最终结果：" + count); // 99988
    }

    @Test
    public void testGetTime() throws IOException {
        Thread[] threads = new Thread[100000];
        List<Long> tiemList = new ArrayList<>();
        for (int i = 0, len = threads.length; i < len; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    long pkId = CmUtil.getPkId();
                    System.err.println(pkId);
                    // 统一获取时间放到数据库中
                    tiemList.add(pkId);
                }
            });
        }
        for (int i = 0, len = threads.length; i < len; i++) {
            threads[i].start();
        }
        System.in.read();
        // 全部线程执行完毕了，在开始输出重复数据
        Set<Long> set = new HashSet<>(tiemList);
        System.err.println("重复数据总共" + (tiemList.size() - set.size())); // 909
    }

}

class InteriorUtil {
    static Lock lock = new ReentrantLock();

    // 错误的
//    public long getPkId() {
//        return System.currentTimeMillis();
//    }
//     正确的
    public static synchronized long getPkId() {
        lock.lock();
        long pkid = 0;
        try {
            pkid = System.currentTimeMillis();
            ++pkid;
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
        return pkid;
    }
}
