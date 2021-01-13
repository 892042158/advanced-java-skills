package org.apache.commons.configuration;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.junit.Test;

public class XMLConfigurationTest {

    @Test
    public void test() throws ConfigurationException {
        XMLConfiguration config = new XMLConfiguration("commons/config.xml");
        // 对于单独的元素，可以直接通过元素名称获取值
        String str = config.getString("boy");
        System.out.println("boy:\t" + str);

        // 对于循环出现的嵌套元素，可以通过父元素.子元素来获取集合值
        List<Object> names = config.getList("student.name");
        System.out.println("student.name:\t" + Arrays.toString(names.toArray()));

        // 对于单独的元素包含的值有多个的话如：a,b,c,d 可以通过获取集合
        List<Object> titles = config.getList("title");
        System.out.println("title:\t" + Arrays.toString(titles.toArray()));

        // 对于标签元素的属性，可以通过 标签[@属性名]这样的方式来获取
        String size = config.getString("ball[@size]");
        System.out.println("ball[@size]:\t" + size);

        // 对于嵌套标签的话，想获得某一项的话可以通过 标签名(索引名)这样的方式来获取
        String id = config.getString("student(1)[@id]");
        System.out.println("student(1)[@id]:\t" + id);

        // 对于标签里面的属性名称可以这么取
        String go = config.getString("student(0).name[@go]");
        System.out.println("student(0).name[@go]:\t" + go);

        // 对于标签里面的属性名称还可以这么取
        go = config.getString("student.name(0)[@go]");
        System.out.println("student.name(0)[@go]:\t" + go);

        /**
         * 依次输出结果如下： boy: tom student.name: [lily, lucy] title: [abc, cbc, bbc, bbs] ball[@size]: 20 student(1)[@id]: 2
         * student(0).name[@go]: common1 student.name(0)[@go]: common1
         */
    }

}
