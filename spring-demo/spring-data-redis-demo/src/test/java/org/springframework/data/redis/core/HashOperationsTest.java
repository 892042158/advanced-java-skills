package org.springframework.data.redis.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/16 18:29
 * @Version: v1.0
 * 操作Redis String的 key Value
 * @see https://blog.csdn.net/baidu_17353319/article/details/88972777
 */
@Slf4j
public class HashOperationsTest extends StringRedisTemplateTest {
    HashOperations<String, Object, Object> hashOperations;

    @Before
    public void setUp() {
        hashOperations = stringRedisTemplate.opsForHash();
    }

    public String genKey(String key) {
        String templateType = "StringRedisTemplate";
        String valueOperationKey = "HashOperations";
        return super.genKey(templateType, valueOperationKey, key);
    }

    @Test
    public void put() {
        String key = genKey("put");
        String mapKey = "mapKey";
        String mapValue = "mapValue";
        hashOperations.put(key, mapKey, mapValue);
        Assert.assertEquals(mapValue, hashOperations.get(key, mapKey));
    }
    @Test
    public void putAll() {
        String key = genKey("putAll");
        Map<String,String> map = new HashMap<>();
        map.put("putAll1","putAll1");
        map.put("putAll2","putAll2");
        map.put("putAll3","putAll3");
        map.put("putAll4","putAll4");
        hashOperations.putAll(key, map);
    }


    /**
     * 想一下明天会优先用到的
     * 缓存 key  mapkey=操作者  value =缓存数据
     * 失效时间
     */
    @Test
    public void expire() throws InterruptedException {
        String key = genKey("expire");
        Map<String,String> map = new HashMap<>();
        map.put("expire1","expire1");
        map.put("expire2","expire2");
        map.put("expire3","expire3");
        hashOperations.putAll(key, map);
        long timeout = 30;
        TimeUnit unit = TimeUnit.SECONDS;
        stringRedisTemplate.expire(key,timeout,unit);
        TimeUnit.SECONDS.sleep(timeout);
//        hashOperations.get(key,"expire1"); //执行一次查询操作 惰性策略保证肯定失去value
        Assert.assertNull(hashOperations.get(key,"expire1"));
    }


//    @Test
//    public void setExpiration() throws InterruptedException {
//        String key = genKey("setExpiration");
//        String value = "这是一个带有失效时间的key";
//        long timeout = 30;
//        TimeUnit unit = TimeUnit.SECONDS;
//        valueOperations.set(key, value, timeout, unit);
//        Assert.assertEquals(value, valueOperations.get(key));
//
//        TimeUnit.SECONDS.sleep(timeout);
//        //这里需要注意的是 redis采用的是定期删除+惰性删除策略
//        valueOperations.get(key); //执行一次查询操作 惰性策略保证肯定失去value
//        Assert.assertNull(valueOperations.get(key));
//
//    }
//
//    /**
//     * * Set {@code key} to hold the string {@code value} if {@code key} is absent.
//     */
//    @Test
//    public void setIfAbsent() {
//        String key = genKey("setIfAbsent");
//        String value = "这是一个 setIfAbsent";
//        boolean isSet = valueOperations.setIfAbsent(key, value);
//        Assert.assertTrue(isSet);
//        isSet = valueOperations.setIfAbsent(key, value);
//        Assert.assertFalse(isSet);
//    }
//
//    /**
//     * #see https://blog.csdn.net/jinheng_/article/details/107963527
//     * 可能会遇到的问题
//     *
//     * @throws InterruptedException
//     */
//    @Test
//    public void setIfAbsentExpiration() throws InterruptedException {
//        String key = genKey("setIfAbsentExpiration");
//        String value = "这是一个setIfAbsentExpiration";
//        long timeout = 30;
//        TimeUnit unit = TimeUnit.SECONDS;
//        boolean isSet = valueOperations.setIfAbsent(key, value, timeout, unit);
//        Assert.assertTrue(isSet);
//        //再去存放肯定是已经存在 返回fasle
//        isSet = valueOperations.setIfAbsent(key, value, timeout, unit);
//        Assert.assertFalse(isSet);
//        //时间过了再去放是true
//        TimeUnit.SECONDS.sleep(timeout);
//        //这里需要注意的是 redis采用的是定期删除+惰性删除策略
//        valueOperations.get(key); //执行一次查询操作 惰性策略保证肯定失去value
//        isSet = valueOperations.setIfAbsent(key, value, timeout, unit);
//        Assert.assertTrue(isSet);
//    }
//
//    /**
//     * 区别在与会判断当前的键的值是否为v，是的话不作操作，不实的话进行替换。如果没有这个键也不会做任何操作。
//     */
//    @Test
//    public void setIfPresent() {
//        String key = genKey("setIfPresent");
//        String value = "这是一个 setIfPresent";
//        valueOperations.set(key, value);
//        boolean isSet = valueOperations.setIfPresent(key, value);
//        Assert.assertTrue(isSet);
//        isSet = valueOperations.setIfPresent(key, value);
//        Assert.assertFalse(isSet);
//
//    }
//
//    @Test
//    public void multiSet() {
//        Map<String, String> map = new HashMap<>();
//        map.put(genKey("multiSet1"), "multiSet1");
//        map.put(genKey("multiSet2"), "multiSet2");
//        map.put(genKey("multiSet3"), "multiSet3");
//        valueOperations.multiSet(map);
//    }

}
