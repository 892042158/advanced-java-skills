package org.springframework.util;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClassUtilsTest {
    @Test
    public void testClassNameToString() {
        System.err.println(ClassUtils.classNamesToString(ClassUtils.class)); // [org.springframework.util.ClassUtils]
        Assert.assertEquals("[org.springframework.util.ClassUtils]", ClassUtils.classNamesToString(ClassUtils.class));
    }

    /**
     * 获取实现的所有的接口
     * 
     * @Title getAllInterfaces
     * @author 于国帅
     * @date 2019年1月11日 下午3:49:55 void
     */
    @Test
    public void getAllInterfaces() {
        Class<?>[] clazzs = ClassUtils.getAllInterfaces(ArrayList.class);
        System.err.println(clazzs.length); // [org.springframework.util.ClassUtils]

        System.err.println(StringUtils.join(clazzs, ",")); // [org.springframework.util.ClassUtils]
    }

    /**
     * 确定两个类是否继承了共同的类，或者接口
     * 
     * @Title determineCommonAncestor
     * @author 于国帅
     * @date 2019年1月11日 下午3:54:45 void
     */
    @Test
    public void determineCommonAncestor() {
        System.err.println(ClassUtils.determineCommonAncestor(List.class, Set.class)); // [org.springframework.util.ClassUtils]
        System.err.println(ClassUtils.determineCommonAncestor(List.class, ArrayList.class)); // [org.springframework.util.ClassUtils]
    }

}
