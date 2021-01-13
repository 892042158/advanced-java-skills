package org.springframework.util;

import java.util.UUID;

import org.junit.Test;

/**
 * 每次调用都会会加1
 * 
 * @ClassName SimpleIdGeneratorTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月8日 下午3:12:50
 *
 */
public class SimpleIdGeneratorTest {
    @Test
    public void generateId() {
        System.err.println("srping-core 的uuid ");
        SimpleIdGenerator SimpleIdGenerator = new SimpleIdGenerator();
        System.err.println(SimpleIdGenerator.generateId());
        System.err.println(SimpleIdGenerator.generateId());
        System.err.println("jdk 的uuid ");
        System.err.println(UUID.randomUUID());
    }
}
