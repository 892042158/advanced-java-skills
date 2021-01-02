package jdk.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IteratorTest {
    List<String> list = new ArrayList<>();

    @Before
    public void before() {
        Collections.addAll(list, "name", "age", "更轻", "更强");
    }

    // 原理 可以参考实现迭代器接口的类
    @Test
    public void hasNext() {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.err.println(iterator.next());
        }
    }
}
