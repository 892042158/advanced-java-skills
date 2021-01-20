package org.springframework.beans.factory.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.xmindguoguo.springboot.ApplicationSuperTest;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/21 0:55
 * @Version: v1.0
 */
@Slf4j
public class ValueAnnotationTest extends ApplicationSuperTest {
    @Value("${test.boolean}")
    private Boolean testBoolean;

    @Value("${test.string}")
    private String testString;

    @Value("${test.integer}")
    private Integer testInteger;

    @Value("${test.long}")
    private Long testLong;

    @Value("${test.float}")
    private Float testFloat;

    @Value("${test.double}")
    private Double testDouble;

    @Value("#{'${test.array}'.split(',')}")
    private String[] testArray;

    @Value("#{'${test.list}'.split(',')}")
    private List<String> testList;

    @Value("#{'${test.set}'.split(',')}")
    private Set<String> testSet;

    @Value("#{${test.map}}")
    private Map<String, Object> testMap;

    /**
     * 遇到的问题 中文乱码 https://blog.csdn.net/m0_37701381/article/details/85062480
     * Spring boot @Value 注入 boolean 设置默认值问题 https://blog.csdn.net/mochi_li/article/details/106349403
     *
     *
     **/
    @Test
    public void testValue() {
        log.info("testBoolean:{}", testBoolean);
        log.info("testString:{}", testString);
        log.info("testInteger:{}", testInteger);
        log.info("testLong:{}", testLong);
        log.info("testFloat:{}", testFloat);
        log.info("testDouble:{}", testDouble);
        log.info("testArray:{}", testArray);
        log.info("testList:{}", testList);
        log.info("testSet:{}", testSet);
        log.info("testMap:{}", testMap);
    }
}
