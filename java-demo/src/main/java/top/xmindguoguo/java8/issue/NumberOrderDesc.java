package top.xmindguoguo.java8.issue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * 想办法 一个数字全部倒序， 或者一组数字按照大小倒序输出
 * 
 * @ClassName NumberOrderDesc
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月22日 下午4:44:27
 *
 */
public class NumberOrderDesc {

    public static void main(String[] args) {
        Integer[] numbers = { 1, 5, 875, 98, 9, 8, 6, 8, 65, 8, 56, 8, 65, 465, 465, 4 };
        // 第一种办法 数组工具类实现
        Arrays.sort(numbers, ((x, y) -> {
            if (x > y) {
                return -1;
            } else if (x < y) {
                return 1;
            }
            return 0;
        }));
        System.err.println(Arrays.toString(numbers));
        // 第二种办法 集合工具类
        // 先把数据改为自然数字排序,然后反转
        Integer[] numbers2 = { 1, 5, 875, 98, 9, 8, 6, 8, 65, 8, 56, 8, 65, 465, 465, 4 };
        Arrays.sort(numbers2);
        Collections.reverse(Arrays.asList(numbers2));
        System.err.println(Arrays.toString(numbers2));

        // 看看源码怎么显示的

        // 看着有难度啊

        // 如果自己实现会怎么做
        Integer[] numbers3 = { 1, 5, 875, 98, 9, 8, 6, 8, 65, 8, 56, 8, 65, 465, 465, 4 };
        orderDesc(numbers3);
        System.err.println(Arrays.toString(numbers3));

    }

    private static void orderDesc(Integer[] numbers3) {
        if (Objects.nonNull(numbers3)) {
            int temp = 0;
            for (int i = 0; i < numbers3.length; i++) {
                // for循环两次 比较 ，如果第一个大于第二个 那么 跳过，否则改变两个的位置
                for (int j = 0; j < numbers3.length; j++) {

                    if (numbers3[i] > numbers3[j]) {
                        temp = numbers3[i];
                        numbers3[i] = numbers3[j];
                        numbers3[j] = temp;
                    }
                }
            }

        }

    }

}
