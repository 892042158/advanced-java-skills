package top.xmindguoguo.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: LambdaMain
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/1 22:49
 * @Version: v1.0
 * * stream() − 为集合创建串行流。
 * * parallelStream() − 为集合创建并行流。
 * @link https://www.cnblogs.com/shenlanzhizun/p/6027042.html
 * <p>
 * 一个流式处理可以分为三个部分：转换成流、中间操作、终端操作
 * @流=》中间操作=》终端操作 流: 集合流，文件流，数组流
 * 中间操作：filter(),distinct(),limit(),skip(),sorted(),map(),flatMap()
 * 终端操作：allMatch(),anyMatch(),noneMatch(),findFirst(),findAny(),count(),reduce(),collect()
 * @see java.util.stream.Collectors
 */
@Slf4j
public class StreamMainTest {
    // 初始化
    List<Student> students = new ArrayList<Student>() {
        {
            add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
            add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
            add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
            add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
            add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
            add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
            add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
            add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
            add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
            add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
        }
    };

    /**
     * 1.怎么获得流
     * 2.流有哪些方法
     */
    @Test
    public void getStream() {
        //1.stream() − 为集合创建串行流。

        //2.parallelStream() − 为集合创建并行流。

        //两者之间的区别

        //arrays 将数组提供流
    }

    /**
     * Stream 提供了新的方法 'forEach' 来迭代流中的每个数据。以下代码片段使用 forEach 输出了10个随机数：
     */
    @Test
    public void foreach() {
        Random random = new Random();
//        random.ints().limit(10).forEach(System.out::println);
        random.ints(1, 10).limit(10).forEach(System.out::println);
    }


    /**
     * filter 方法用于通过设置的条件过滤出元素。以下代码片段使用 filter 方法过滤出空字符串：
     */
    @Test
    public void filter() {
        //demo1
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();
        //demo2
        List<Student> whuStudents = students.stream()
                .filter(student -> "武汉大学".equals(student.getSchool()))

                .collect(Collectors.toList());
    }

    @Test
    public void distinct() {
        //demo1
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long count = strings.stream().filter(string -> string.isEmpty()).count();

    }


    /**
     * limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：
     */
    @Test
    public void limit() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }


    /**
     * sorted 方法用于对流进行排序。以下代码片段使用 sorted 方法对输出的 10 个随机数进行排序：
     */
    @Test
    public void sorted() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
        //

        List<Student> sortedCivilStudents = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor())).sorted((s1, s2) -> s1.getAge() - s2.getAge())
                .limit(2)
                .collect(Collectors.toList());
    }

    @Test
    public void skip() {
        List<Student> civilStudents = students.stream()
                .filter(student -> "土木工程".equals(student.getMajor()))
                .skip(2)
                .collect(Collectors.toList());
    }

    /**
     * map 举例说明，假设我们希望筛选出所有专业为计算机科学的学生姓名，那么我们可以在filter筛选的基础之上，通过map将学生实体映射成为学生姓名字符串，
     * 具体实现如下：
     */
    @Test
    public void map() {
        int totalAge = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .mapToInt(Student::getAge).sum();

    }

    @Test
    public void mapToInt() {

        int totalAge = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .mapToInt(Student::getAge).sum();

    }

    /**
     * flatMap与map的区别在于 flatMap是将一个流中的每个值都转成一个个流，然后再将这些流扁平化成为一个流 。
     * 举例说明，假设我们有一个字符串数组String[] strs = {"java8", "is", "easy", "to", "use"};，
     * 我们希望输出构成这一数组的所有非重复字符，那么我们可能首先会想到如下实现：
     */
    @Test
    public void flatMap() {
        String[] strs = {"java8", "is", "easy", "to", "use"};
        List<String[]> distinctStrs = Arrays.stream(strs)
                .map(str -> str.split(""))  // 映射成为Stream<String[]>
                .distinct()
                .collect(Collectors.toList());
        log.info(Arrays.toString(strs));
        log.info(distinctStrs.toString());


        List<String> distinctStrs2 = Arrays.stream(strs)
                .map(str -> str.split(""))  // 映射成为Stream<String[]>
                .flatMap(Arrays::stream)  // 扁平化为Stream<String>
                .distinct()
                .collect(Collectors.toList());
        log.info(distinctStrs2.toString());


    }

    /**
     * allMatch用于检测是否全部都满足指定的参数行为，如果全部满足则返回true，
     * 例如我们希望检测是否所有的学生都已满18周岁，那么可以实现为：
     */
    @Test
    public void allMatch() {
        boolean isAdult = students.stream().allMatch(student -> student.getAge() >= 18);

    }

    /**
     * anyMatch则是检测是否存在一个或多个满足指定的参数行为，如果满足则返回true，
     * 例如我们希望检测是否有来自武汉大学的学生，那么可以实现为：
     */
    @Test
    public void anyMatch() {
        boolean hasWhu = students.stream().anyMatch(student -> "武汉大学".equals(student.getSchool()));

    }

    /**
     * noneMatch用于检测是否不存在满足指定行为的元素，如果不存在则返回true，
     * 例如我们希望检测是否不存在专业为计算机科学的学生，可以实现如下：，
     */
    @Test
    public void noneMatch() {
        boolean noneCs = students.stream().noneMatch(student -> "计算机科学".equals(student.getMajor()));

    }

    /**
     * findFirst用于返回满足条件的第一个元素，比如我们希望选出专业为土木工程的排在第一个学生，那么可以实现如下：
     */
    @Test
    public void findFirst() {
        Optional<Student> optStu = students.stream().filter(student -> "土木工程".equals(student.getMajor())).findFirst();

    }

    /**
     * findAny 相对于findFirst的区别在于，findAny不一定返回第一个，而是返回任意一个，比如我们希望返回任意一个专业为土木工程的学生，可以实现如下：
     * 实际上对于顺序流式处理而言，findFirst和findAny返回的结果是一样的，至于为什么会这样设计，是因为在下一篇我们介绍的并行流式处理，当我们启用并行流式处理的时候，查找第一个元素往往会有很多限制，如果不是特别需求，在并行流式处理中使用findAny的性能要比findFirst好。
     */
    @Test
    public void findAny() {
        Optional<Student> optStu = students.stream().filter(student -> "土木工程".equals(student.getMajor())).findAny();

    }

    //归约操作
    @Test
    public void reduce() {
// 前面例子中的方法
        int totalAge = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .mapToInt(Student::getAge).sum();
// 归约操作
        int totalAge1 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(0, (a, b) -> a + b);

// 进一步简化
        int totalAge2 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(0, Integer::sum);

// 采用无初始值的重载版本，需要注意返回Optional
        Optional<Integer> totalAge3 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(Integer::sum);  // 去掉初始值

    }


    //收集

    /**
     * 收集器也提供了相应的归约操作
     * ，但是与reduce在内部实现上是有区别的，收集器更加适用于可变容器上的归约操作，
     * 这些收集器广义上均基于Collectors.reducing()实现。
     * 详情
     *
     * @see jdk.util.stream.CollectorsTest
     */


    @Test
    public void count() {
        long count = students.stream().collect(Collectors.counting());

        // 进一步简化
        long count2 = students.stream().count();
        log.info(String.valueOf(count));
        log.info(String.valueOf(count2));

    }


    //======================并行（parallel）程序====================
    @Test
    public void parallelStream() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
// 获取空字符串的数量
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
    }

    /**
     * Collectors
     */
    @Test
    public void testCollectors() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }

    /**
     * 统计
     * 另外，一些产生统计结果的收集器也非常有用。它们主要用于int、double、long等基本类型上，它们可以用来产生类似如下的统计结果。
     */
    @Test
    public void test() {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }
}

@Data
@AllArgsConstructor
class Student {
    /**
     * 学号
     */
    private long id;

    private String name;

    private int age;

    /**
     * 年级
     */
    private int grade;

    /**
     * 专业
     */
    private String major;

    /**
     * 学校
     */
    private String school;
}
