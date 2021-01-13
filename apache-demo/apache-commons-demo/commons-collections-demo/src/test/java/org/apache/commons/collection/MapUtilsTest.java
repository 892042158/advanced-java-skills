package org.apache.commons.collection;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapUtilsTest {
    @Test
    public void map() {
        Map<String, Object> map = new HashMap<>();
        map.put("aa", 1);
        boolean a = MapUtils.isEmpty(map);
        System.out.println(a);
    }
}
