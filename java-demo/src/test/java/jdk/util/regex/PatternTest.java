package jdk.util.regex;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.junit.Test;

public class PatternTest {
    /**
     * Pattern.matches(String regex,CharSequence input)是一个静态方法,用于快速匹配字符串,该方法适合用于只匹配一次,且匹配全部字符串.
     * 
     * @Title matches
     * @author 于国帅
     * @date 2019年6月3日 上午12:56:19 void
     */
    @Test
    public void matches() {
        Pattern p = Pattern.compile("\\w+");
//        p.pattern();// 返回 \w+
        System.err.println(p.pattern());
        Pattern.matches("\\d+", "2223");// 返回true
        Pattern.matches("\\d+", "2223aa");// 返回false,需要匹配到所有字符串才能返回true,这里aa不能匹配到
        Pattern.matches("\\d+", "22bb23");// 返回false,需要匹配到所有字符串才能返回true,这里bb不能匹配到
    }

    /**
     * Pattern有一个split(CharSequence input)方法,用于分隔字符串,并返回一个String[],我猜String.split(String regex)就是通过Pattern.split(CharSequence input)来实现的.
     * 
     * @Title split
     * @author 于国帅
     * @date 2019年6月3日 上午12:56:29 void
     */
    @Test
    public void split() {
        Pattern p = Pattern.compile("\\d+");
//        结果:str[0]="我的QQ是:" str[1]="我的电话是:" str[2]="我的邮箱是:aaa@aaa.com" 
        String[] str = p.split("我的QQ是:456456我的电话是:0532214我的邮箱是:aaa@aaa.com");
        System.err.println(Arrays.toString(str));
    }

}
