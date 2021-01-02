package jdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.IntStream;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 1.有返回值的方法不能直接测试
 * 
 * 2.带参数的方法不能直接测试
 * 
 * 3.访问权限在public一下的方法不能直接测试
 * 
 * 4.static静态方法不能直接测试
 * 
 * 5.不能给出现前四个条件中任意一个的方法添加@Test注解，否则执行满足@Test条件的方法也会出现initializationerror初始化异常 --------------------- 作者：Java仗剑走天涯 来源：CSDN
 * 原文：https://blog.csdn.net/baidu_37107022/article/details/73658343 版权声明：本文为博主原创文章，转载请附上博文链接！
 * 
 * @ClassName ArraysTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月21日 上午10:39:39
 *
 */
public class ArraysTest {
    // 将任意数组以“,” 拼接转变成字符串
    @Test
    public void testToString() {
        String[] strs = new String[] { "22", "sadas", "23dwd", "21ea" };
        System.err.println(Arrays.toString(strs));
        List<String> strsList = new ArrayList<>();
        strsList.add("222");
//        System.err.println(strsList.get("222"));  //发现报错
        System.err.println(Arrays.binarySearch(strsList.toArray(), "222"));
        StringUtils.join(strs, ",");
    }

    // 将任意数组及其子元素以“,” 拼接转变成字符串
    @Test
    public void deepToString() {
        int[][] stuGrades = { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } };
        System.out.println(Arrays.deepToString(stuGrades));
    }

    @Test
    public void asList() {
        List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
        // 测试是否能够删除
//        stooges.remove(1); // java.lang.UnsupportedOperationException
        // 测试是否能够添加
//        stooges.add("22"); // java.lang.UnsupportedOperationException
    }

    // 使用二进制搜索算法搜索指定对象的所在数组的索引，如果不存在返回 -1 看api可以发现 基本类型，Object。T 囊括了各大类型
    // 支持从一个数组查找一个key，也可以指定一个数组从索引x到索引y 的范围内查找
    // binarySearch(Object[] a, int fromIndex, int toIndex, Object key)
    // binarySearch(Object[] a, Object key)
    @Test
    public void binarySearch() {
        // 我们简单测试一下Obect的
        Object[] objs = new Object[] { "name", 2, 3L, "dsadda" }; // 测试发现爆类型转换错误，实际还是需要 类型一致啊
        objs = new Object[] { "name", "dsadda" };
        Object obj = "name";
        System.err.println(Arrays.binarySearch(objs, obj));

        // 下面是测试 指定索引的 ，我们发现上面的函数实际上调用的也是指定索引的
        System.err.println(Arrays.binarySearch(objs, 1, 1, obj)); // 什么，我们竟然神器的发现了-2？？？？？ api不是说找不到，返回-1 吗
        System.err.println(Arrays.binarySearch(objs, 1, objs.length, obj)); // ？？？-3l了 只好看看源码了

        // 原来是采用 数组的Comparable 比较的，然后 源码注释存在这句 -数代表 // key not found.
    }

    // 复制指定的数组，使用空值截断或填充（如有必要），以使副本具有指定的长度。对于在原始数组和副本中都有效的所有索引，这两个数组将包含相同的值。对于在副本中有效但不在原始副本中的任何索引，副本将包含null。当且仅当指定的长度大于原始数组的长度时，这些索引才会存在。生成的数组与原始数组完全相同。
    // 代码可知，数组拷贝时调用的是本地方法 System.arraycopy() ；
    // Arrays.copyOf()方法返回的数组是新的数组对象，原数组对象仍是原数组对象，不变
    @Test
    public void copyOf() {
        String[] strs = new String[] { "22", "sadas", "23dwd", "21ea" };
        String[] strs2 = Arrays.copyOf(strs, strs.length);
        System.err.println(Arrays.toString(strs2)); // 我们发现完全的复制了
        // 那么他们两个的指向的内存地址是否一致呢，是否复制了改变一个另一个也会发生改变呢？
        System.err.println(strs); // [Ljava.lang.String;@aec6354
        System.err.println(strs2); // [Ljava.lang.String;@1c655221
        // 那么复制短长度 答案是没有问题
        String[] strs3 = Arrays.copyOf(strs, strs.length - 1);
        System.err.println(Arrays.toString(strs3)); //
        // 超过长度的话又会怎么样呢 答案是复制多余的元素 会显示null [22, sadas, 23dwd, 21ea, null, null, null]
        String[] strs4 = Arrays.copyOf(strs, strs.length + 3);
        System.err.println(Arrays.toString(strs4));
        // 我们可以调用 fill Arrays.fill(strs, 2, 9, "2222");
        Arrays.fill(strs4, strs.length, strs.length + 3, "我是填充的元素");
        System.err.println(Arrays.toString(strs4)); // 我们可以看到，已经填充好了元素，[22, sadas, 23dwd, 21ea, 我是填充的元素, 我是填充的元素, 我是填充的元素]
        // 我们再看看API copyOf(U[] original, int newLength, Class<? extends T[]> newType)
        String[] strs5 = Arrays.copyOf(strs, strs.length + 3, strs.getClass()); // 上边的代码本质是实际也是调用这种代码
        System.err.println(Arrays.toString(strs5)); //
        // 我猜想一下复制二维数组会怎么样呢？
        int[][] stuGrades = { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } };

//        int[][] stuGrades2  = (int[][]) Arrays.copyOf(strs, strs.length - 1);   //我们发现直接报编译错误了

    }

    // 和上面的copyOf 一样，只不过是指定数组的指定范围进行copy
    @Test
    public void copyOfRange() {
        String[] strs = new String[] { "22", "sadas", "23dwd", "21ea" };
        // copy 索引1,2两个元素
        String[] strs2 = Arrays.copyOfRange(strs, 1, 2); // [sadas]
        System.err.println(Arrays.toString(strs2));
        String[] strs3 = Arrays.copyOfRange(strs, 1, 2 + 1); // [sadas,23dwd]
        System.err.println(Arrays.toString(strs2));
    }

    // 初始化填充一些数据
    @Test
    public void fill() {
        String[] strs = new String[10];
        System.out.println("init befor" + Arrays.toString(strs));
        Arrays.fill(strs, "2222");
        System.out.println("init after" + Arrays.toString(strs));
    }

    @Test
    public void fillParams() {
        String[] strs = new String[10];
        System.out.println("init befor" + Arrays.toString(strs));
        Arrays.fill(strs, 2, 9, "2222");
        System.out.println("init after" + Arrays.toString(strs));
    }

    // 如果两个指定的数组彼此非常相等，则返回true。
    @Test
    public void testEquals() {
        int[] ints = { 1, 2, 3, 6 };
        int[] ints2 = { 1, 2, 3, 6 };
        int[] ints3 = { 1, 2, 3 };
        System.out.println(Arrays.equals(ints, ints2)); // true
        System.out.println(Arrays.equals(ints, ints3)); // false

        // 有了这一招，我们可以巧妙的比较一下两个集合是否相等

    }

//如果两个指定的数组深层次彼此非常相等，则返回true。
    @Test
    public void deepEquals() {
        String[] name1 = { "G", "a", "o", "H", "u", "a", "n", "j", "i", "e" };
        String[] name2 = { "G", "a", "o", "H", "u", "a", "n", "j", "i", "e" };
        System.out.println(Arrays.equals(name1, name2)); // true
        System.out.println(Arrays.deepEquals(name1, name2)); // true

        // 改变顺序
        String[][] name12 = { { "G", "a", "o" }, { "H", "u", "a", "n" }, { "j", "i", "e" } };
        String[][] name22 = { { "G", "a", "o" }, { "H", "u", "a", "n" }, { "j", "i", "e" } };

        System.out.println(Arrays.equals(name12, name22)); // false
        System.out.println(Arrays.deepEquals(name12, name22));// true
    }

    @Test
    public void testHashCode() {
        int[] ints = { 1, 2, 3, 6 };
        System.out.println(Arrays.hashCode(ints)); // 955333
    }

    // 根据指定数组的“深层内容”返回哈希码。
    @Test
    public void deepHashCode() {
        Object[] name1 = { "G", "a", "o", "H", "u", "a", "n", "j", "i", "e" };
        System.out.println(Arrays.deepHashCode(name1)); // 1002816792
    }

    // 一个数组 按照数字顺序 升序排列
    // 或者指定比较器进行数据顺序比较
    @Test
    public void sort() {
        Integer[] values2 = { 1, 3, 432, 445, 2, 116 };
        Arrays.sort(values2);
        System.err.println(Arrays.toString(values2)); // [1, 2, 3, 116, 432, 445]
        // 使用 Comparator，改为倒序排序
        Arrays.sort(values2, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
//                if (o1 < o2) { // 第一个数小于第二个数则
//                    return 1;
//                } else if (o1 > o2) {
//                    return -1;
//                }
//                return 0;
                // 根据源码探索，实际上返回-1就是倒序
                return -1;
            }
        });
        System.err.println(Arrays.toString(values2));

    }

    // 一个数组全部做表达式操作 一个数组的所有的元素都被表达式操作对应的数据
    @Test
    public void setAll() {
        int[] values2 = { 1, 2, 3, 4, 5, 6 };
        Arrays.setAll(values2, x -> x + 10);
        System.err.println(Arrays.toString(values2));
    }

    // 将数组转为流式，对array进行流式处理，可用一切流式处理的方法
    @Test
    public void stream() {
        int[] values2 = { 1, 2, 3, 4, 5, 6 };
        IntStream intStream = Arrays.stream(values2);
        // 查找所有大于3的元素，并且倒序输出
        values2 = intStream.filter(x -> x > 3).sorted().toArray(); // 必须一次性操作完毕，否则会报流已经关闭

        System.err.println(Arrays.toString(values2));
    }

//============================================根据参考资料  以下的就全部都是有关于多线程的了   parallel===================================================
    // parallelPrefix(double[] array,DoubleBinaryOperator op)
    // DoubleBinaryOperator 这个是jdk8新增的函数调用接口在java8
    // 根据传递的函数接口实现，对应的元素依次进行计算 二元迭代，对原数组内容进行二元操作
    // parallelPrefix(long[] array, int fromIndex, int toIndex, LongBinaryOperator op) 指定范围内计算
    @Test
    public void parallelPrefix() {
        /*
         *下面的计算依然可以并行完成，在log(n)步之后，处理完毕，如果有足够的处理器，它的性能要好过线性运算
         */
        int[] values2 = { 1, 2, 3, 4, 5, 6 };
        // 执行了 1*2 ，1*2*3 数组计算
        Arrays.parallelPrefix(values2, (x, y) -> x * y);// [1, 2, 6, 24, 120, 720]
        System.err.println(Arrays.toString(values2));
        int[] values = { 1, 2, 3, 4, 5, 6 };
        // 运行这一行
        Arrays.parallelPrefix(values, 2, 4, (x, y) -> x * y);// [1, 2, 3, 12, 5, 6]
        // 我们发现结果只对 索引 3 发生了改变 ，也就是说 他的计算是从fromIndex 开始 到toIndex结束
        System.err.println(Arrays.toString(values));
        // 一次类推 只会计算索引 3，4 发生改变
        Arrays.parallelPrefix(values, 2, 5, (x, y) -> x * y);// [1, 2, 3, 12, 5, 6]
        System.err.println(Arrays.toString(values));
        // 这里就十分好奇 为什么可以并行计算了 ，我们查看源码
//new ArrayPrefixHelpers.CumulateTask<>
        // (null, op, array, 0, array.length).invoke();

    }

    // parallelSetAll会将数组中的值按照一个函数的计算结果过滤出来。该函数会获得元素索引，并计算该位置处的值
    // 一个数组全部做表达式操作
    @Test
    public void parallelSetAll() {
        int[] values2 = { 1, 2, 3, 4, 5, 6 };
        // 同样是并行计算，我们发现，他只是对数组的每个元素进行lambda处理，而不是迭代处理
        Arrays.parallelSetAll(values2, (x -> x * 10));
        System.err.println(Arrays.toString(values2)); // [0, 10, 20, 30, 40, 50]
        // 这是为啥呢？怎么最后一个元素丢了，那个0是什么梗？
        // 根据源码探索之后，才知道 原来这个流 传递的参数不是当前数组的元素，而是当前数组的索引
        // 如果想每个元素* 10 需要这样操作
        int[] values = { 1, 2, 3, 4, 5, 6 };
        Arrays.parallelSetAll(values, (x -> values[x] * 10));
        System.err.println(Arrays.toString(values)); // [10, 20, 30, 40, 50, 60]
    }

    @Test
    public void parallelSort() {
        Integer[] values2 = { 1, 3, 432, 445, 2, 116 };
        Arrays.parallelSort(values2);
        System.err.println(Arrays.toString(values2)); // [1, 2, 3, 116, 432, 445]
        // 这里比较奇怪的是 同时调用默认排序和 这个多线程排序 就会存在问题 暂定
        // 使用 Comparator，改为倒序排序
        Arrays.parallelSort(values2, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
//                if (o1 < o2) { // 第一个数小于第二个数则
//                    return 1;
//                } else if (o1 > o2) {
//                    return -1;
//                }
//                return 0;
                // 根据源码探索，实际上返回-1就是倒序
                return -1;
            }
        });
        System.err.println(Arrays.toString(values2));

    }

    // 返回一个并行同步迭代器
    // 暂时找不到在工作场景使用，所以等待用到回来复习
    // @see
    // https://www.baidu.com/link?url=7IGPIX6DtqtisP4eUHsGIjMtBngkcmnXEtn6Rdb2JacxFlWB6ot2q80luElh4eiqAUXxP5ScLNCrVObdLFX3k9Nk7s9yqEicspnQ52U7QzO&wd=&eqid=a1a8d32c00046abb000000045c455d11
    @Test
    public void spliterator() {
        Integer[] values2 = { 1, 3, 432, 445, 2, 116 };
        // 转变成迭代器返回回来
        Spliterator<Integer> spliterator = Arrays.spliterator(values2);
        spliterator.forEachRemaining(a -> System.out.print(a + " "));
        // public static <T> Spliterator<T> spliterator(Object[] arg, int arg0) {
        // return new ArraySpliterator((Object[])Objects.requireNonNull(arg), arg0);
    }

}
