package jdk.util;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

/**
 * 比较器探索
 * 
 * @see https://blog.csdn.net/u013066244/article/details/78997869?t=123
 * 
 * @ClassName ComparatorTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月21日 上午11:52:13
 *
 */
public class ComparatorTest {
//    ① jdk官方默认是升序，是基于：
//
//    < return -1
//    = return 0
//    > return -1
    @Test
    public void sort() {
        Integer[] values2 = { 1, 3, 432, 445, 2, 116 };
        Arrays.sort(values2);
        System.err.println(Arrays.toString(values2)); // [1, 2, 3, 116, 432, 445]
        // 使用 Comparator，改为倒序排序
        Arrays.sort(values2, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
//              if (o1 < o2) { // 第一个数小于第二个数则
//              return 1;
//          } else if (o1 > o2) {
//              return -1;
//          }
//          return 0;
                // 根据源码探索，实际上返回-1就是倒序
                return -1;
            }
        });
        System.err.println(Arrays.toString(values2));

    }
}
