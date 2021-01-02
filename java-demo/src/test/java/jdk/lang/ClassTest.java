package jdk.lang;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class ClassTest {
    Class<List> listClzz = List.class;

    @Test
    public void test() {

    }

    // 将对象转换为字符串。 字符串表示是字符串“类”或“接口”，后面加一个空格，然后在返回的格式类的完全限定名getName 。 如果此类对象表示原始类型，则此方法返回原始类型的名称。 如果这个类对象表示void，则此方法返回“void”。
    @Test
    public void testToString() {
        System.err.println(listClzz.toString()); // interface java.util.List
//        public String toString() {  //源码
//            return (this.isInterface()?"interface ":(this.isPrimitive()?"":"class ")) + this.getName();
//         }
    }

    /**
     * 返回描述此类的字符串，包括有关修饰符和类型参数的信息。
     * 
     * @Title toGenericString
     * @author 于国帅
     * @date 2019年2月12日 上午11:20:49 void
     */
    @Test
    public void toGenericString() {
        System.err.println(listClzz.toGenericString()); // public abstract interface java.util.List<E>

    }

    /**
     * 回与给定字符串名称的类或接口相关联的类对象
     * 
     * @比较好奇除了jdbc用还有那些方面用到了
     * @Title forName
     * @author 于国帅
     * @throws ClassNotFoundException
     * @date 2019年2月12日 上午11:21:10 void
     */
    @Test
    public void forName() throws ClassNotFoundException {
        Class t = Class.forName("java.lang.Thread");
        System.err.println(t.getName()); // java.lang.Thread
    }

    /**
     * 创建由此类对象表示的类的新实例。 该类被实例化为一个具有空参数列表的new表达式。 如果类尚未初始化，则初始化该类。
     * 
     * @构造函数 无参数必须是public
     * @Title newInstance
     * @author 于国帅
     * @date 2019年2月12日 上午11:31:56
     * @throws ClassNotFoundException
     *             void
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ArrayList arr = ArrayList.class.newInstance();
        arr.add("test");
        for (Object object : arr) {
            System.err.println(object);
        }
//        arr.getClass().isInstance(arr);
    }

    /**
     * 
     * 判断一个对象是否是一个类的实例化对象
     * 
     * @Title isInstance
     * @author 于国帅
     * @date 2019年2月12日 上午11:44:01 void
     */
    @Test
    public void isInstance() {
        ArrayList arr = new ArrayList<>();
        Map map = new HashMap();
        System.err.println(listClzz.isInstance(arr)); // true
        System.err.println(listClzz.isInstance(map)); // false

    }

    /**
     * 确定由此类对象表示的类或接口是否与由指定的Class 类表示的类或接口相同或是超类或类接口。 如果是，则返回true ; 否则返回false
     */
    @Test
    public void isAssignableFrom() {
        ArrayList arr = new ArrayList<>();
        Map map = new HashMap();

        System.err.println(listClzz.isAssignableFrom(arr.getClass())); // true
        System.err.println(listClzz.isAssignableFrom(map.getClass())); // false
        System.err.println(arr.getClass().isAssignableFrom(listClzz)); // false
        // 结论 A检查参数B ,A是B的兄弟或者爸爸或者祖先，那么 true ，否则false
        // 且儿子不能检查爸爸，检查就爱挨打（false）
    }

    // 判断一个对象是否为数组类型
    @Test
    public void isArray() {
        String[] strs = new String[10];
        ArrayList arr = new ArrayList<>();
        System.err.println(strs.getClass().isArray()); // true
        System.err.println(arr.getClass().isArray()); // fasle

    }

    // 判断一个对象是否为基本类型 类型（boolean,byte,,char,short,int,float,double,long,void）
    @Test
    public void isPrimitive() {
        Class stringClass = String.class;
        System.out.println("String is primitive type：" + stringClass.isPrimitive()); // false

        Class booleanClass = Boolean.class;
        System.out.println("Boolean is primitive type：" + booleanClass.isPrimitive()); // false

        Class booleanType = boolean.class;
        System.out.println("boolean is primitive type：" + booleanType.isPrimitive()); // true

    }

    // 判断一个class 是否为注解
    @Test
    public void isAnnotation() throws InstantiationException, IllegalAccessException {

        System.err.println(Test.class.isAnnotation()); // true
        System.err.println(String.class.isAnnotation()); // false

    }

    /**
     * 判断一个类是否为合成类
     * 
     * @合成类定义 由java编译器生成的（除了像默认构造函数这一类的）方法，或者类
     *        synthetic总的来说，是由编译器引入的字段、方法、类或其他结构，主要用于JVM内部使用，为了遵循某些规范而作的一些小技巧从而绕过这些规范，有点作弊的感觉，只不过是由编译器光明正大的，人为是没有权限的（但事实上有时候还是能被利用到的）。
     * @see https://www.cnblogs.com/bethunebtj/p/7761596.html
     * @see https://blog.csdn.net/a327369238/article/details/52608805
     */
    @Test
    public void isSynthetic() {
        // 看完上边两篇博客感觉用的少（实际自己用不到，偷笑gif），所以就不写了
    }

    /**
     * @接口+类 返回 包名称+类名称
     * @数组 返回则该名称的内部形式由表示数组嵌套深度的一个或多个“ [ ”字符前面的元素类型的名称组成。
     * @基本类型 返回基本类型字符串
     * @Title getName
     * @author 于国帅
     * @date 2019年2月18日 下午3:48:44 void
     */
    @Test
    public void getName() {
        // 接口
        System.err.println(List.class.getName()); // java.util.List
        // 类
        System.err.println(String.class.getName()); // java.lang.String
        // 数组
        ArrayList[] arr = new ArrayList[10];
        System.err.println(arr.getClass().getName()); // [Ljava.util.ArrayList;
        // 基本类型
        System.err.println(boolean.class.getName()); // boolean

    }

    // 返回类的加载器 ，等待学习
    @Test
    public void getClassLoader() {
        System.err.println(ClassTest.class.getClassLoader());

    }

    // 返回一个TypeVariable对象的数组，它们以声明顺序表示由此GenericDeclaration对象表示的通用声明声明的类型变量。 如果底层通用声明不声明类型变量，则返回长度为0的数组。
    @Test
    public void getTypeParameters() {
        // 注意 是反射包下的类
        // 查看List接口下的
        TypeVariable[] tValue = List.class.getTypeParameters();
        System.out.println(Arrays.toString(tValue));
        // 未定义泛型参数的
        TypeVariable[] tValue0 = TypeParamsTest0.class.getTypeParameters();
        System.out.println(Arrays.toString(tValue0));
        // 定义泛型参数的
        TypeVariable[] tValue1 = TypeParamsTest1.class.getTypeParameters();
        System.out.println(Arrays.toString(tValue1));
        // 定义 继承泛型参数的
        TypeVariable[] tValue2 = TypeParamsTest2.class.getTypeParameters();
        System.out.println(Arrays.toString(tValue2));
        // 定义复杂参数的
        TypeVariable[] tValue3 = TypeParamsTest2.class.getTypeParameters();
        System.out.println(Arrays.toString(tValue3));
    }

    // 返回类表示此所表示的实体（类，接口，基本类型或void）的超类类
    // 通常实现基于对象的框架时会用到，例如递归查找到对应所有的model字段
    @Test
    public void getSuperclass() {
        // ArrayList 的父类
        System.err.println(ArrayList.class.getSuperclass()); // class java.util.AbstractList
        // 递归查找所有的ArrayList的祖宗们
        List<Object> list = new ArrayList<>();
        list = getAllSuperclass(list, ArrayList.class);
        // 输出
        System.err.println(StringUtils.join(list.toArray()));
        // 如何区分集成的类 与接口呢？
        // 继续深入探究
    }

    public List<Object> getAllSuperclass(List<Object> list, Class<?> c) {
        if (Objects.nonNull(c)) {
            list.add(c.getClass());
            return getAllSuperclass(list, c.getSuperclass());
        }
        return list;
    }

    // 同getSuperclass 一样，不过会额外获取他的类上的泛型
    @Test
    public void getGenericSuperclass() {
        System.err.println(ArrayList.class.getGenericSuperclass()); // java.util.AbstractList<E>

    }

    // 获取此类的包对象
    @Test
    public void getPackage() {

        System.err.println(ArrayList.class.getPackage()); // package java.util, Java Platform API Specification, version 1.8

    }

    // 获取此类的实现的接口
    @Test
    public void getInterfaces() {
        Class<?>[] classs = ArrayList.class.getInterfaces();
        System.err.println(Arrays.toString(classs)); //
        // [interface java.util.List, interface java.util.RandomAccess, interface java.lang.Cloneable, interface java.io.Serializable]
        // 同理 根据递归也能够获取所有的实现的接口
    }

    // 获取此类的实现的接口包括泛型
    @Test
    public void getGenericInterfaces() {
        Type[] classs = ArrayList.class.getGenericInterfaces();
        System.err.println(Arrays.toString(classs)); //
        // [interface java.util.List, interface java.util.RandomAccess, interface java.lang.Cloneable, interface java.io.Serializable]
        // 同理 根据递归也能够获取所有的实现的接口
    }
    // 返回类数组的组件类型的Class。 如果此类不表示数组类，则此方法返回null。

    @Test
    public void getComponentType() {
        Class<?> classs = ArrayList.class.getComponentType();
        System.err.println(classs); //
        Type[] classsArr = ArrayList.class.getGenericInterfaces();
        System.err.println(Arrays.toString(classsArr)); //
        Class<?> classs3 = classsArr.getClass().getComponentType();
        System.err.println(classs3); //
    }

    @Test
    public void getModifiers() {

    }

    // 获得这个类的签名者。 感觉需要深入 ，先了解一下签名这个类？
    // XXX 需要了解这个方法
    @Test
    public void getSigners() {
        try {
//            Class cls = Class.forName("ClassDemo");
//            Class cls = Class.forName(TypeParamsTest0.class.getName());
            Class cls = Class.forName(Arrays.class.getName());
//          Class cls = Class.forName(TypeParamsTest0.class.getName());

            // returns the name of the class
            System.out.println("Class = " + cls.getName());

            Object[] obj = cls.getSigners();
            System.out.println("Value = " + obj);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Test
    public void getEnclosingMethod() {

    }

    @Test
    public void getEnclosingConstructor() {

    }

    @Test
    public void getDeclaringClass() {

    }

    public static void main(String[] args) {
        int i = 0 / 0;
    }

}

class TypeParamsTest0 {

}

class TypeParamsTest1<String> {

}

class TypeParamsTest2<T extends Test> {

}
