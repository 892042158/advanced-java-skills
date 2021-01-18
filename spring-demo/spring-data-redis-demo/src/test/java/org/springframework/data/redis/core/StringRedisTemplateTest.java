package org.springframework.data.redis.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.xmindguoguo.redis.ApplicationSuperTest;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/16 2:09
 * @Version: v1.0
 * test
 * @see org.springframework.data.redis.core.RedisOperations
 * 学习思路梳理
 * 1.首先先一下简单的set get
 * <p>
 * 2.**Operations 系列单独测试
 * private final ValueOperations<K, V> valueOps = new DefaultValueOperations(this);
 * private final ListOperations<K, V> listOps = new DefaultListOperations(this);
 * private final SetOperations<K, V> setOps = new DefaultSetOperations(this);
 * private final StreamOperations<K, ?, ?> streamOps = new DefaultStreamOperations(this, new ObjectHashMapper());
 * private final ZSetOperations<K, V> zSetOps = new DefaultZSetOperations(this);
 * private final GeoOperations<K, V> geoOps = new DefaultGeoOperations(this);
 * private final HyperLogLogOperations<K, V> hllOps = new DefaultHyperLogLogOperations(this);
 * private final ClusterOperations<K, V> clusterOps = new DefaultClusterOperations(this);
 * 3.然后 RedisOperations 的相关的实现接口在这个类里面去学习 同时看上边实现的源码
 * ScriptExecutor
 * 4.最后差不多都学习完毕了，去整理一下注意事项
 */

@Slf4j
public class StringRedisTemplateTest extends ApplicationSuperTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 生成一个数据库方便看到的key
     *
     * @param templateType
     * @param valueOperationKey
     * @param key
     * @return
     */
    public String genKey(String templateType, String valueOperationKey, String key) {
        return String.format("%s:%s:%s", templateType, valueOperationKey, key);
    }


    @Test
    public void testSetAndGet() {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }


}
