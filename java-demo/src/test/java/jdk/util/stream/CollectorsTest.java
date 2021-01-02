package jdk.util.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 收集器也提供了相应的归约操作
 * ，但是与reduce在内部实现上是有区别的，收集器更加适用于可变容器上的归约操作，
 * 这些收集器广义上均基于Collectors.reducing()实现。
 * 详情@see
 */
@Slf4j
public class CollectorsTest {
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
            add(new Student(20163002, "丁奉", 27, 5, "土木工程", "南京大学"));

        }
    };
    //====================================数据转换start=======================================================
    //toCollection(),toList(),toSet(),mapMerger(),mapping(),collectingAndThen(),toConcurrentMap

    /**
     * 使用toMap()函数之后，返回的就是一个Map了，自然会需要key和value。
     * toMap()的第一个参数就是用来生成key值的，第二个参数就是用来生成value值的。
     * 第三个参数用在key值冲突的情况下：如果新元素产生的key在Map中已经出现过了，第三个参数就会定义解决的办法。
     */
    @Test
    public void toMap() {
        //现在学生列表 存在重名的学生，我们筛选年龄大的那个
        Map<String, Student> map = students.stream().collect(
                Collectors.toMap(model -> model.getName(), model -> model, (oldValue, newValue) -> {
                    return oldValue.getAge() > newValue.getAge() ? oldValue : newValue;
                }));
        //输出27岁的
        Student dingfeng = map.get("丁奉");
        log.info(dingfeng.toString());
    }

    /**
     * collectingAndThen
     * @param <T> the type of the input elements
     * @param <A> intermediate accumulation type of the downstream collector
     * @param <R> result type of the downstream collector
     * @param <RR> result type of the resulting collector
     * @param downstream a collector
     * @param finisher a function to be applied to the final result of the downstream collector
     * @return a collector which performs the action of the downstream collector,
     *
     * @https://www.cnblogs.com/xuwenjin/p/9660393.html
     */
    @Test
    public void collectingAndThen() {
        //现在学生列表 存在重名的学生，我们筛选年龄大的那个

        //没java8流的写法
        TreeSet<Student> treeSet = new TreeSet<>(Comparator.comparing(Student::getName));
        treeSet.addAll(students);
        treeSet.forEach(d -> System.out.println("age:" + d.getAge() + ", name:" + d.getName()));

        //java8写法
        TreeSet<Student> treeSet1 = students.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))));
        List<Student> newDishList = new ArrayList<>(treeSet1);
        newDishList.forEach(d -> System.out.println("age:" + d.getAge() + ", name:" + d.getName()));

        //java 简化写法
        List<Student> newDishList2 = students.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new));


    }
    //====================================数据转换end=========================================================

    //====================================工具start=======================================================
    //joining()

    /**
     * 在JDK8中，可以采用函数式编程（使用 Collectors.joining 收集器）的方式对字符串进行更优雅的连接。
     * Collectors.joining 收集器 支持灵活的参数配置，可以指定字符串连接时的 分隔符，前缀 和 后缀 字符串。
     */
    @Test
    public void joining() {
        //现在学生列表 存在重名的学生，我们筛选年龄大的那个
        final String[] names = {"Zebe", "Hebe", "Mary", "July", "David"};
        Stream<String> stream1 = Stream.of(names);
        Stream<String> stream2 = Stream.of(names);
        Stream<String> stream3 = Stream.of(names);
// 拼接成 [x, y, z] 形式
        String result1 = stream1.collect(Collectors.joining(", ", "[", "]"));
// 拼接成 x | y | z 形式
        String result2 = stream2.collect(Collectors.joining(" | ", "", ""));
// 拼接成 x -> y -> z] 形式
        String result3 = stream3.collect(Collectors.joining(" -> ", "", ""));
        System.out.println(result1);
        System.out.println(result2);

        System.out.println(result3);
    }

    //====================================工具end=========================================================
    //====================================类似数据库的处理start=======================================================
    //counting(),minBy(),maxBy(),summingInt(),summingLong(),summingDouble(),sumWithCompensation(),
    //computeFinalSum(),averagingInt()，averagingLong(),averagingDouble(),summarizingInt(),summarizingLong(),summarizingDouble()
    //groupingBy()，groupingByConcurrent(),partitioningBy(),
    @Test
    public void counting() {
        long count = students.stream().collect(Collectors.counting());

        // 进一步简化
        long count2 = students.stream().count();
        log.info(String.valueOf(count));
        log.info(String.valueOf(count2));

    }

    /**
     *
     */
    @Test
    public void maxBy() {
// 求最大年龄
        Optional<Student> olderStudent = students.stream().collect(Collectors.maxBy((s1, s2) -> s1.getAge() - s2.getAge()));

// 进一步简化
        Optional<Student> olderStudent2 = students.stream().collect(Collectors.maxBy(Comparator.comparing(Student::getAge)));

// 求最小年龄
        Optional<Student> olderStudent3 = students.stream().collect(Collectors.minBy(Comparator.comparing(Student::getAge)));

    }

    /**
     * 对应的还有summingLong、summingDouble。
     */
    @Test
    public void summingInt() {
        int totalAge4 = students.stream().collect(Collectors.summingInt(Student::getAge));
    }

    @Test
    public void avgAge() {
        //例4：求年龄的平均值
        double avgAge = students.stream().collect(Collectors.averagingInt(Student::getAge));
    }

    /**
     * 例5：一次性得到元素个数、总和、均值、最大值、最小值
     */
    @Test
    public void summarizingInt() {
        //例4：求年龄的平均值
        IntSummaryStatistics statistics = students.stream().collect(Collectors.summarizingInt(Student::getAge));
        log.info(statistics.toString());
    }

    /**
     * 在数据库操作中，我们可以通过GROUP BY关键字对查询到的数据进行分组，
     * java8的流式处理也为我们提供了这样的功能Collectors.groupingBy来操作集合。比如我们可以按学校对上面的学生进行分组：
     */
    @Test
    public void groupingBy() {
        //例4：求年龄的平均值
        Map<String, List<Student>> groups = students.stream().collect(Collectors.groupingBy(Student::getSchool));

        log.info(groups.toString());
        //多级分组
        Map<String, Map<String, List<Student>>> groups2 = students.stream().collect(
                Collectors.groupingBy(Student::getSchool,  // 一级分组，按学校
                        Collectors.groupingBy(Student::getMajor)));  // 二级分组，按专业

        log.info(groups2.toString());

    }

    /**
     * 分区
     * 分区可以看做是分组的一种特殊情况，在分区中key只有两种情况：true或false，目的是将待分区集合按照条件一分为二，
     * java8的流式处理利用ollectors.partitioningBy()方法实现分区，
     * 该方法接收一个谓词，例如我们希望将学生分为武大学生和非武大学生，那么可以实现如下：
     */
    @Test
    public void partitioningBy() {
        //例4：求年龄的平均值
        Map<Boolean, List<Student>> partition = students.stream().collect(Collectors.partitioningBy(student -> "武汉大学".equals(student.getSchool())));
        log.info(partition.toString());

    }

    //====================================类似数据库的处理end=========================================================


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

