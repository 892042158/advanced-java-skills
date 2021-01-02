package jdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.Data;

/**
 * http://www.cnblogs.com/shenlanzhizun/p/6027042.html
 * 
 * @学习java流
 * 
 * @ClassName ListTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年3月8日 上午11:40:43
 *
 */
public class ListTest {
    String[] strs = { "java8", "is", "easy", "to", "use" };
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

    // 只获取对象里面的一种元素
    @Test
    public void testMap() {
        List<String> names = students.stream().filter(student -> "计算机科学".equals(student.getMajor())).map(Student::getName)
                .collect(Collectors.toList());
        System.err.println(names);
    }

    @Test
    public void testFlatMap() {
        List<String> distinctStrs = Arrays.stream(strs).map(str -> str.split("")) // 映射成为Stream<String[]>
                .flatMap(Arrays::stream) // 扁平化为Stream<String>
                .distinct().collect(Collectors.toList());
        System.err.println(distinctStrs);

    }

}

@Data
class Student {

    /** 学号 */
    private long id;

    private String name;

    private int age;

    /** 年级 */
    private int grade;

    /** 专业 */
    private String major;

    /** 学校 */
    private String school;

    public Student(long id, String name, int age, int grade, String major, String school) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.major = major;
        this.school = school;
    }

    // 省略getter和setter

}