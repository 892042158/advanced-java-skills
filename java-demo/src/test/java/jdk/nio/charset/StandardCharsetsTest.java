package jdk.nio.charset;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class StandardCharsetsTest {
    /**
     * jdk5 以后自带的字符编码
     * 
     * @Title testUTF8
     * @author 于国帅
     * @date 2019年3月31日 下午7:40:53 void
     */
    @Test
    public void testUTF8() {
        System.err.println(StandardCharsets.UTF_8.name());
    }

    /**
     * Apache commons io 包带的UTF-8
     * 
     * @Title testUTF2
     * @author 于国帅
     * @date 2019年3月31日 下午7:41:14 void
     */
//    @Test
//    public void testUTF2() {
//        System.err.println(FileUtils);
//    }
}
