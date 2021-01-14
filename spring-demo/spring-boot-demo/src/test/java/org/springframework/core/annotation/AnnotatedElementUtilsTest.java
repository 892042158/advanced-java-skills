package org.springframework.core.annotation;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotatedElementUtilsTest {
    @Test
    public void test() {
        AnnotationChildMutilAttribute mergedAnnotation1 = AnnotatedElementUtils.findMergedAnnotation(ExtendMutilClass.extendValue1.class,
                AnnotationChildMutilAttribute.class);
        Assert.assertTrue(mergedAnnotation1.extendValue1().equals("extendValue1"));
        Assert.assertTrue(mergedAnnotation1.extendValue2().equals("extendValue1"));

        AnnotationChildMutilAttribute mergedAnnotation2 = AnnotatedElementUtils.findMergedAnnotation(ExtendMutilClass.extendValue2.class,
                AnnotationChildMutilAttribute.class);
        Assert.assertTrue(mergedAnnotation2.extendValue1().equals("extendValue2"));
        Assert.assertTrue(mergedAnnotation2.extendValue2().equals("extendValue2"));
    }

}

//编写元注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface AnnotaionBase {
    String value() default "";
}

//编写子注解，其中子注解打上了元注解@AnnotaionBase标识
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AnnotaionBase
@interface AnnotationChildMutilAttribute {

    @AliasFor(annotation = AnnotaionBase.class, attribute = "value")
    String extendValue1() default "";

    @AliasFor(annotation = AnnotaionBase.class, attribute = "value")
    String extendValue2() default "";
}

//编写测试类
class ExtendMutilClass {

    @AnnotationChildMutilAttribute(extendValue1 = "extendValue1")
    public static class extendValue1 {
    }

    @AnnotationChildMutilAttribute(extendValue2 = "extendValue2")
    public static class extendValue2 {
    }
}
