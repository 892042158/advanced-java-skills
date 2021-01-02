package jdk.util;

import java.util.HashSet;

import org.junit.Test;

public class HashSetTest {

    @Test
    public void testRemoveAll() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("/systemUser/toSystemUserList");
        hashSet.add("/systemDept/toSystemDeptList");
        hashSet.add("/systemDept/toSystemDeptList11");
        hashSet.add("/systemDept/toSystemDeptList2111");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("/systemUser/toSystemUserList");
        hashSet2.add("/systemDept/toSystemDeptList");
        hashSet2.add("/systemDept/toSystemDeptList2");

        hashSet.removeAll(hashSet2);

        for (String string : hashSet) {
            System.err.println(string);
        }

    }
}
