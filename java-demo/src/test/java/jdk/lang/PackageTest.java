package jdk.lang;

import java.util.ArrayList;

import org.junit.Test;

public class PackageTest {
    @Test
    public void getName() {
        System.err.println(ArrayList.class.getPackage()); // package java.util, Java Platform API Specification, version 1.8
        System.err.println(ArrayList.class.getPackage().getName()); // package java.util, Java Platform API Specification, version 1.8

    }
}
