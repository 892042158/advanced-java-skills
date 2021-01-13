package org.springframework.util;

import org.junit.Test;

/**
 * @see https://blog.csdn.net/u012834750/article/details/72403978?utm_source=blogxgwz3
 * @ConcurrentReferenceHashMap与ConcurrentHashMap的区别是ConcurrentReferenceHashMap能指定所存放对象的引用级别，适用于并发下Map的数据缓存。 @ClassName
 *                                                                                                          ConcurrentReferenceHashMapTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年4月13日 下午2:09:12
 *
 */
public class ConcurrentReferenceHashMapTest {
    /**
     * @see https://www.cnblogs.com/fengbs/p/7019687.html
     *      弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
     */
    @Test
    public void testWeak() {
        ConcurrentReferenceHashMap map = new ConcurrentReferenceHashMap(16, ConcurrentReferenceHashMap.ReferenceType.WEAK);
        map.put("key", "val");
        System.out.println(map);

        System.gc();
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }
}
