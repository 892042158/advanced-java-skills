package org.springframework.core.convert.support;

import org.junit.Test;
import org.springframework.core.convert.converter.Converter;

import java.nio.charset.Charset;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 23:02
 * @Version: v1.0
 */
public class StringToBooleanConverterTest {

    @Test
    public void test() {
        System.out.println("----------------StringToBooleanConverter---------------");
        Converter<String, Boolean> converter = new StringToBooleanConverter();

        // trueValues.add("true");
        // trueValues.add("on");
        // trueValues.add("yes");
        // trueValues.add("1");
        System.out.println(converter.convert("true"));
        System.out.println(converter.convert("1"));

        // falseValues.add("false");
        // falseValues.add("off");
        // falseValues.add("no");
        // falseValues.add("0");
        System.out.println(converter.convert("FalSe"));
        System.out.println(converter.convert("off"));
        // 注意：空串返回的是null
        System.out.println(converter.convert(""));


        System.out.println("----------------StringToCharsetConverter---------------");
        Converter<String, Charset> converter2 = new StringToCharsetConverter();
        // 中间横杠非必须，但强烈建议写上   不区分大小写
        System.out.println(converter2.convert("uTf-8"));
        System.out.println(converter2.convert("utF8"));
    }
}
