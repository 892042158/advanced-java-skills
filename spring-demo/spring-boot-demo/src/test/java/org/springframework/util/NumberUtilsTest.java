package org.springframework.util;

import org.junit.Test;

public class NumberUtilsTest {
    @Test
    public void convertNumberToTargetClass() {
        String number = "2232424";
        Double doubleNumber = NumberUtils.parseNumber(number, Double.class);
        Integer integerNumber = NumberUtils.convertNumberToTargetClass(doubleNumber, Integer.class);
        System.err.println(integerNumber);

    }

    @Test
    public void parseNumber() {
        String number = "2232424";
        Double doubleNumber = NumberUtils.parseNumber(number, Double.class);
        System.err.println(doubleNumber);
    }

    @Test
    public void toLong() {
        String number = "2232424";
        // 没了
//        System.err.println(NumberUtils.convertNumberToTargetClass(number, Lang.class));

    }
}
