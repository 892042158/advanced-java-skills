package org.springframework.util;

import org.junit.Test;

import study.test.code.model.TestModel;

public class ObjectUtilsTest {
    /**
     * Return a hex String form of an object's identity hash code.
     * 
     * @param obj
     *            the object
     * @return the object's identity code in hex notation
     */
    @Test
    public void getIdentityHexString() {
        System.err.println(ObjectUtils.getIdentityHexString(new TestModel()));
        System.err.println(System.identityHashCode(new TestModel()));
        System.err.println(new TestModel());

    }

}
