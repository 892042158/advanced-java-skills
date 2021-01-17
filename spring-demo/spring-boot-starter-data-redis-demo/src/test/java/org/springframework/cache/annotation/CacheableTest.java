package org.springframework.cache.annotation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.xmindguoguo.redis.ApplicationSuperTest;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/16 2:22
 * @Version: v1.0
 */
@Slf4j
public class CacheableTest extends ApplicationSuperTest {

    @Test
    public void testCacheable() {
        print(getName());
    }

    @Cacheable("yu")
    public String getName() {
        return "guo";
    }
}
