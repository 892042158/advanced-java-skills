package top.xmindguoguo.aop.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/21 0:05
 * @Version: v1.0
 * aop注解测试
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AopDemo {
    String value() default "aopDemo";
}
