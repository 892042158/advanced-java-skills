package jdk.util;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionsTest {
    private List<User> users = Lists.newArrayList(new User("jack", 17, 10), new User("jack", 18, 10), new User("jack", 19, 11),
            new User("apple", 25, 15), new User("tommy", 23, 8), new User("jessica", 15, 13));
    List<String> list2 = Arrays.asList("Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday".split(","));

    // 向一个实现Collection 接口的任意集合 添加可变参数
    @Test
    public void addAll() {
        Set<Class<?>> primitiveTypes = new HashSet<>(32);
        primitiveTypes.add(Integer.class);
        Collections.addAll(primitiveTypes, boolean[].class, byte[].class, char[].class, double[].class, float[].class, int[].class,
                long[].class, short[].class);
        primitiveTypes.add(void.class);
        System.err.println(primitiveTypes);
        System.err.println(StringUtils.join(primitiveTypes, ","));
    }

    // public static <T> Queue<T> asLifoQueue(Deque<T> deque)
    // 返回Deque作为先进先出（ Lifo ） Queue的视图
    // 把一个集合变为后进先出的队列
    @Test
    public void asLifoQueue() {

        LinkedList<Integer> llist1 = new LinkedList<Integer>(Arrays.asList(2, 4, 6));
        System.out.println(llist1);// 默认 [2, 4, 6]

        System.out.println(llist1.add(5)); // 添加了5 ,在元素后边 [2, 4, 6, 5]
        System.out.println(llist1);

        System.out.println(llist1.offer(9));
        System.out.println(llist1); // [2, 4, 6, 5, 9]

        System.out.println(llist1.element()); // 返回第一个数据 2
        System.out.println(llist1.peek()); // 返回第一个数据2
        System.out.println(llist1); // 数据未发生改变[2, 4, 6, 5, 9]

        System.out.println(llist1.poll()); // 移除 2
        System.out.println(llist1); // [4, 6, 5, 9]

        System.out.println("-------------------------");

        LinkedList<Integer> llist2 = new LinkedList<Integer>(Arrays.asList(2, 4, 6));
        Queue<Integer> q = Collections.asLifoQueue(llist2);
        System.out.println(q); // [2, 4, 6]

        System.out.println(q.add(5)); // [5, 2, 4, 6]
        System.out.println(q); // 与LinkedList 的相比，再增加的元素就在头部 也就是说后进先出

        System.out.println(q.offer(9));
        System.out.println(q);

        System.out.println(q.element());
        System.out.println(q.peek());
        System.out.println(q);

        System.out.println(q.poll());
        System.out.println(q); // [5, 2, 4, 6]

        /*--------------------- 
        作者：小宇0000 
        来源：CSDN 
        原文：https://blog.csdn.net/liyuming0000/article/details/49491515 
        版权声明：本文为博主原创文章，转载请附上博文链接！*/
    }

    /**
     * 对数据进行随机排序
     *
     * @Title shuffle
     * @author 于国帅
     * @date 2019年1月10日 下午2:52:03 void
     */
    @Test
    public void shuffle() {
        Collections.shuffle(users);
        syso();
    }

    public void syso() {
        for (User user : users) {
            System.err.println(user);
        }
    }

    @Test
    public void map() {
        Map<String, Object> map = new HashMap<>();
        map.put("aa", 1);
//        CollectionUtils
    }

    @Test
    public void sort() {
        // 根据年龄排序
        Collections.sort(users, new Comparator<User>() {

            @Override
            public int compare(User arg0, User arg1) {
                // 年龄越大 排在最前面
                return arg0.getAge() - arg1.getAge(); // return的值越大 越靠后
            }
        });
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void traditionCompareByNameInJava8() {
        users.sort((o1, o2) -> o1.getAge() - o2.getAge());
        users.forEach(user -> System.out.println(user));
        users.sort(new Comparator<User>() {

            @Override
            public int compare(User arg0, User arg1) {
                // 年龄越大 排在最前面
                return arg0.getAge() - arg1.getAge(); // return的值越大 越靠后
            }
        });
//        Comparator.comparingInt(null);
    }

    @Test
    public void traditionCompareByNameInJava82() {
        users.sort(Comparator.comparingInt(User::getAge));
        users.forEach(System.out::println);
    }

    @Test
    public void max() {
//        Collections.max(users)
    }

    @Test
    public void min() {
//        Collections.max(users)
    }

    // 使用二叉搜索算法搜索指定对象的指定列表。
    @Test
    public void binarySearch() {
        int index1 = Collections.binarySearch(list2, "Thursday");
        System.err.println(index1);
        Assert.assertEquals(index1, 3);
    }

    @Test
    public void replaceAll() {
        boolean flag = Collections.replaceAll(list2, "Sunday", "tttttt");
        System.err.println();
    }

    @Test
    public void enumeration() {
        Enumeration<String> e = Collections.enumeration(list2);
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }

    // 该语言中的泛型机制提供了编译时（静态）类型检查，但是可以用未经检查的转换来击败此机制。
    // 一下这些作用相同
//  checkedList
//  checkedList
//  checkedNavigableMap
//  checkedNavigableMap
//  checkedNavigableMap
//  checkedNavigableMap
//  checkedSortedMap
//  checkedSortedSet
    @Test
    public void checkedCollection() {
        Collection<String> c = new HashSet<>(); // 可以暂时用这个替换：
        HashSet obj = (HashSet) c;
        obj.add(new Date());
        System.err.println(obj); // 数据竟然放进来了，而且能够正常输出
        Collection<String> c2 = Collections.checkedCollection(new HashSet<>(), String.class);
        obj = (HashSet) c2;
        obj.add(new Date());
        System.err.println(obj); // 报错 //只有执行到这一步才会抛出java.lang.ClassCastException
    }

    // 将所有元素从一个列表复制到另一个列表中
    // 我比较疑惑的是复制重复的数据会怎么处理，会按照集合的特性吗
    // 原来是直接把原先所存在索引地方的元素直接替换掉了 ，感觉用到的地方很少啊
    @Test
    public void copy() {
        List<String> strList = new ArrayList<String>();
        Collections.addAll(strList, "22", "困了", "完成每日任务", "坚持自己的核心任务");
        System.err.println(strList);
        List<String> strCopyList = new ArrayList<String>();
        strCopyList.add("22");
        strCopyList.add("223");

        // 注意问题 strList长度不能长于 strCopyList 否则会报错
        Collections.copy(strList, strCopyList); //
        System.err.println(strList);
        System.err.println(strCopyList);

    }

    // disjoint 如果两个指定的集合没有共同的元素，则返回 true 。
    // 我疑惑比较的是内存地址 还是类似于实现了 equals
    // 看源码才知道调用的集合本身的contains 方法
    @Test
    public void disjoint() {
        List<String> strList = new ArrayList<String>();
        Collections.addAll(strList, "22", "困了", "完成每日任务", "坚持自己的核心任务");
        List<String> strCopyList = new ArrayList<String>(strList);
        List<String> strCopyList2 = new ArrayList<String>();
        strCopyList2.add("33");
        boolean flag = Collections.disjoint(strList, strCopyList);
        System.err.println(flag);
        boolean flag2 = Collections.disjoint(strList, strCopyList2);
        System.err.println(flag2);
    }
//    static <T> Enumeration<T> emptyEnumeration() 
//    返回没有元素的枚举。  
//    static <T> Iterator<T> emptyIterator() 
//    返回没有元素的迭代器。  
//    static <T> List<T> emptyList() 
//    返回空列表（immutable）。  
//    static <T> ListIterator<T> emptyListIterator() 
//    返回没有元素的列表迭代器。  
//    static <K,V> Map<K,V> emptyMap() 
//    返回空的地图（不可变）。  
//    static <K,V> NavigableMap<K,V> emptyNavigableMap() 
//    返回空导航地图（不可变）。  
//    static <E> NavigableSet<E> emptyNavigableSet() 
//    返回一个空导航集（immutable）。  
//    static <T> Set<T> emptySet() 
//    返回一个空集（immutable）。  
//    static <K,V> SortedMap<K,V> emptySortedMap() 
//    返回空的排序映射（immutable）。  
//    static <E> SortedSet<E> emptySortedSet() 
//    返回一个空的排序集（immutable）。  
//    static <T> Enumeration<T> enumeration(Collection<T> c) 
//    返回指定集合的枚举。  

    // fill 用指定的元素代替指定列表的所有元素。
    @Test
    public void fill() {
        List<String> strList = new ArrayList<String>();
        Collections.addAll(strList, "22", "困了", "完成每日任务", "坚持自己的核心任务");
        System.err.println(strList);
        System.err.println("全部变成生日快乐");
        Collections.fill(strList, "生日快乐");
        System.err.println(strList);
    }
    // 返回指定集合中与指定对象相等的元素数。 更正式地，返回集合中的元素数量e，使得(o == null ? e == null : o.equals(e)) 。

    @Test
    public void frequency() {
        List<String> strList = new ArrayList<String>();
        Collections.addAll(strList, "22", "困了", "完成每日任务", "坚持自己的核心任务", "坚持自己的核心任务");
        System.err.println(Collections.frequency(strList, "坚持自己的核心任务"));

        // 如果采用流 该怎么筛选呢？
        String o = "坚持自己的核心任务";
        strList = strList.stream().filter(e -> (o == null ? e == null : o.equals(e))).collect(Collectors.toList());

        System.err.println(strList.size());
    }

    // 返回指定源列表中指定目标列表的第一次出现的起始位置，如果没有此类事件，则返回-1。
    @Test
    public void indexOfSubList() {

    }

    // 返回指定源列表中指定目标列表的最后一次出现的起始位置，如果没有此类事件则返回-1。
    @Test
    public void lastIndexOfSubList() {

    }

    // 枚举转变成List
    @Test
    public void list() {

    }

    // 反转指定列表中元素的顺序。
    @Test
    public void reverse() {
        List<Double> doubleList = new LinkedList<>();
        Collections.addAll(doubleList, 2d, 3d, 322d, 2345423d, 34d);
        System.err.println(doubleList);
        Collections.reverse(doubleList);
        System.err.println(doubleList);

        // 那么尝试一下数字的自然排序呢
        List<Double> doubleList2 = new ArrayList<>();
        Collections.addAll(doubleList2, 2d, 3d, 322d, 2345423d, 34d);
        System.err.println(doubleList2);
        Collections.reverse(doubleList2);
        System.err.println(doubleList2);
    }

    @Test
    public void reverseOrder() {
        // 返回实现排序的那个类
        Comparator comparator = Collections.reverseOrder();
        System.err.println(comparator);
    }

    // rotate方法是将链表旋转一定的distance，该方法常常与subList方法结合用户在List中移动某个元素到指定的位置，而不影响其他元素的顺序
    @Test
    public void rotate() {
//        Rotate方法需要一个参数distance，该方法将一个List旋转多少长度为distance。假如有个序列列list是[a,b,c,d]，调用方法Collections.rotate(list, 1)后，得到的list就变为了[d,a,b,c]。 
//        调用此方法后，位置i上的元素将变为位置(i - distance) mod list.size()的元素，0 <= i < list.size()。distance可以为正数、0、负数。正数代表向前（下标值变大的方向）旋转，负数代表向后旋转。调用方法Collections.rotate(list, -1)后，得到的list就变为了[b,c,d,a]。
//
//        这个方法常常和List的subList方法结合使用，用于将一个list的某个或多个元素进行移动，而不破坏其余元素的顺序。例如为了将某个list的位置j的元素向前移动到k的位置。（设移动的距离为d（d >= 0），k = j + d + 1）。
//
//        Collections.rotate(list.subList(j, k+1), -1);
//        1
//        举个栗子，例如[a,b,c,d,e]，将b元素向前移动三个位置
//
//        Collections.rotate(list.subList(1, 5), -1);
//        1
//        调用后得到list为[a,c,d,e,b]。
//        --------------------- 
//        作者：Spground 
//        来源：CSDN 
//        原文：https://blog.csdn.net/u014532901/article/details/52891183 
//        版权声明：本文为博主原创文章，转载请附上博文链接！
    }

    // unmodifiable 不可修改的集合

    /**
     * @see https://blog.csdn.net/weixin_39992480/article/details/100046941
     */
    @Test
    public void unmodifiableMap() {


    }
    // synchronized 线程安全的集合

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;
    private Integer age;
    private Integer credits;
}
