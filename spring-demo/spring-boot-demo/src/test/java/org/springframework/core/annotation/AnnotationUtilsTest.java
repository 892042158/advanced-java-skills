package org.springframework.core.annotation;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.annotation.*;

/**
 * 用于处理注解，处理元注解，桥接方法（编译器为通用声明生成）以及超级方法（用于可选注解继承）的常规实用程序方法。请注意，JDK的内省工具本身并不提供此类的大多数功能。作为运行时保留注解的一般规则（例如，用于事务控制，授权或服务公开），始终在此类上使用查找方法（例如，findAnnotation（Method，Class），getAnnotation（Method，Class）和getAnnotations（方法））而不是JDK中的普通注解查找方法。您仍然可以在给定类级别的get查找（getAnnotation（Method，Class））和给定方法的整个继承层次结构中的查找查找（findAnnotation（Method，Class））之间明确选择。
 * 
 * @ClassName AnnotationUtilsTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年2月12日 上午11:00:54
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//========testAliasFor1=====
//@ContextConfiguration(value = "aa.xml", locations = "aa.xml") // 会报错
//@ContextConfiguration(value = "aa.xml") // 不会报错
//========testAliasFor2.1=====
//@ContextConfiguration(classes = ConfigurationTest.class)
@CC(cs = ConfigurationTest.class)
//========testAliasFor2.2=====
@CustomCC()
// =======
public class AnnotationUtilsTest {
    @Autowired
    TestSerivce testBean;

    /**
     * 用法 1 A注解有属性a,b ,用上@AliasFor 这个注解，设置a或者b的其中一个值的时候，另一个属性值也会相等
     * 
     * @spring典型 注解 例子 @RequestMapping
     * @Title testAliasFor1
     * @author 于国帅
     * @date 2019年3月5日 下午2:22:12 void
     */
    @Test
    public void testAliasFor1() {
        ContextConfiguration contextConfiguration = AnnotationUtils.findAnnotation(getClass(), ContextConfiguration.class);
        System.err.println(StringUtils.join(contextConfiguration.value()));
        System.err.println(StringUtils.join(contextConfiguration.locations()));

    }

    /**
     * 用法 2 显示的覆盖元注解中的属性；实现类似于注解继承的效果
     * 
     * @spring典型 注解 例子 @Controller,@Service,Repository
     * @Title testAliasFor2
     * @author 于国帅
     * @date 2019年3月5日 下午2:29:33 void
     */
    @Test
    public void testAliasFor2() {
        // 我们先说一下 @ContextConfiguration 用来加载配置ApplicationContext，其中classes属性用来加载配置类
        // 下面模拟一下这个注解的作用 ，然后测试一下不是spring 管理的注解是否能够实现
        // @CC
        System.err.println(testBean); // 存在值 正确
        // 继续测试 不是spring注解的时候 该怎么操作，我的想法是自定义注解，然后通过工具类 获取对应的值
        // 获取对应的值
        CustomCC cc = AnnotationUtils.findAnnotation(getClass(), CustomCC.class);
        System.err.println(cc.cc()); // 输出cc 才是正确的猜想，结果不是 ，等待看完源码回来分析
    }

    /**
     * 
     * 
     * @Title testAliasFor3
     * @author 于国帅
     * @date 2019年3月5日 下午3:34:09 void
     */
    @Test
    public void testAliasFor3() {

    }

    @Test
    public void test() {
//        AnnotationUtils
    }
}

//实现一个内部注解
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration
@interface CC {
    @AliasFor(value = "classes", annotation = ContextConfiguration.class)
    Class<?>[] cs() default {};
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface CustomContextConfiguration {
    String value() default "cc";
}

@Retention(RetentionPolicy.RUNTIME)
@CustomContextConfiguration()
@interface CustomCC {
    @AliasFor(value = "value", annotation = CustomContextConfiguration.class)
    String cc() default "";
}
