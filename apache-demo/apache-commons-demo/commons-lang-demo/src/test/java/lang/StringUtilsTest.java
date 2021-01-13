package lang;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.Arrays;

public class StringUtilsTest {
    @Test
    public void testEmpty() {
        String str = StringUtils.EMPTY;
        System.out.println(str);
    }

    @Test
    public void split() {
        String[] str = StringUtils.split("1234  56ssss");
        System.out.println(Arrays.toString(str));
        str = StringUtils.split("123456ssss");// aacc222
        System.out.println(Arrays.toString(str));
        str = StringUtils.split("123456ssss", "s");// aacc222
        System.out.println(Arrays.toString(str));
        str = StringUtils.split("123456ssss");// aacc222
        System.out.println(Arrays.toString(str));
    }

    @Test
    public void join() {
        String str = StringUtils.join(new String[] { "aa", "cc", "222" });// aacc222
        System.out.println(str);
        str = StringUtils.join(new String[] { "aa", "cc", "222" }, ",");// aa,cc,222
        System.out.println(str);
        str = StringUtils.join(new String[] { "aa", "cc", "222" }, ",", 1, 2);// cc
        System.out.println(str);
    }

    @Test
    public void repeat() {
        String str = StringUtils.repeat("repeat重复", 3);
        System.out.println(str);
    }

    @Test
    public void abbreviate() {
        String str = StringUtils.abbreviate("标题锁进--伟大的标题", 7);// 标题锁进...
        System.out.println(str);
    }

    @Test
    public void testStrip() {
        String str = StringUtils.EMPTY;
        System.out.println(StringUtils.strip("aaabbcccc", "avc"));// bb
//        assertEquals(StringUtils.strip("aaabbcccc", "avc"), "bb");
        System.out.println(StringUtils.strip(null, null));// "null"
        System.out.println(StringUtils.strip("   aa   abbcc   cc", " "));// bb

    }

    @Test
    public void stripAll() {
        String[] strs = { "aaabnbcccc null", "avc,null" };
        System.out.println(Arrays.toString(StringUtils.stripAll(strs)));// [aaabbcccc, avc]
        System.out.println(Arrays.toString(StringUtils.stripAll(strs, ",null")));// [aaabbcccc, avc]
    }

    @Test
    public void deleteWhitespace() {
        String s = "aaa  ddd   ss ";
        System.out.println(StringUtils.deleteWhitespace(s));
        s = "aaa, ddd,   ss ";
        System.out.println(StringUtils.remove(s, " "));
    }

}
