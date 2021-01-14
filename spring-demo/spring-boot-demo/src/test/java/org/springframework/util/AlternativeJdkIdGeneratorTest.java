package org.springframework.util;

import org.junit.Test;

import java.util.UUID;
/**
 * 基于 原生UUID   生成三种算法
 * AlternativeJdkIdGenerator
 * SimpleIdGenerator
 *JdkIdGenerator
 * 
 * @ClassName AlternativeJdkIdGeneratorTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月8日 下午3:21:59
 *
 */
public class AlternativeJdkIdGeneratorTest {
    @Test
    public void generateId() {
        System.err.println("srping-core 的uuid ");
        System.err.println(new AlternativeJdkIdGenerator().generateId());
        System.err.println("jdk 的uuid ");
        System.err.println(UUID.randomUUID());
    }
}
