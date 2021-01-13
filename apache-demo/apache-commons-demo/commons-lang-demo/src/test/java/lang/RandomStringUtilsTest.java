package lang;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

public class RandomStringUtilsTest {
    @Test
    public void random() {
        System.out.println("**RandomStringUtilsDemo**");
        System.out.println("生成指定长度的随机字符串,好像没什么用.");
        System.out.println(RandomStringUtils.random(6));

        System.out.println("在指定字符串中生成长度为n的随机字符串.");
        System.out.println(RandomStringUtils.random(6, "1234567890"));

        System.out.println("指定从字符或数字中生成随机字符串.");
        System.out.println(RandomStringUtils.random(6, true, false));
        System.out.println(RandomStringUtils.random(6, false, true));
        System.out.println(RandomStringUtils.random(6, true, true));
        System.out.println(RandomStringUtils.random(6, false, false));
    }
}
