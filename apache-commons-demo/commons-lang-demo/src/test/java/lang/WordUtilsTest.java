package lang;

import org.apache.commons.lang.WordUtils;
import org.junit.Test;

public class WordUtilsTest {
    @Test
    public void wordUtilsDemo() {
        System.out.println("单词处理功能");
        String str1 = "wOrD";
        String str2 = "ghj\nui\tpo";
        System.out.println(WordUtils.capitalize(str1)); // 首字母大写
        System.out.println(WordUtils.capitalizeFully(str1)); // 首字母大写其它字母小写
        char[] ctrg = { '.' };
        System.out.println(WordUtils.capitalizeFully("i aM.fine", ctrg)); // 在规则地方转换
        System.out.println(WordUtils.initials(str1)); // 获取首字母
        System.out.println(WordUtils.initials("Ben John Lee", null)); // 取每个单词的首字母
        char[] ctr = { ' ', '.' };
        System.out.println(WordUtils.initials("Ben J.Lee", ctr)); // 按指定规则获取首字母
        System.out.println(WordUtils.swapCase(str1)); // 大小写逆转
        System.out.println(WordUtils.wrap(str2, 1)); // 解析\n和\t等字符
    }
}
