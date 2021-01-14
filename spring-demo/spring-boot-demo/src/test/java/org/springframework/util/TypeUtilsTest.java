package org.springframework.util;

import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TypeUtilsTest {
    /**
     * 检查右侧类型是否可以分配到左侧 按照Java泛型规则输入。
     * 
     * @param lhsType目标类型
     * @param rhsType是应该分配给目标类型的值类型
     *            如果rhs可以赋值给lhs，则返回true
     */
    @Test
    public void isAssignable() {
        System.err.println(TypeUtils.isAssignable(Object.class, List.class)); // true
        System.err.println(TypeUtils.isAssignable(List.class, ArrayList.class)); // true
        System.err.println(TypeUtils.isAssignable(List.class, ArrayList.class)); // true
        System.err.println(TypeUtils.isAssignable(AbstractList.class, ArrayList.class));// true
        System.err.println(TypeUtils.isAssignable(List.class, HashMap.class)); // false
        // 这个方法可以判断 一个类和另一个类的关系 是否能够正确发生协变和逆变
    }
}
