package jdk.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @see jdk.util.regex.PatternTest
 * @see https://www.cnblogs.com/gdwkong/articles/7782331.html
 * @ClassName MatcherTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年6月3日 上午12:50:23
 *
 */
public class MatcherTest {
    // matches()对整个字符串进行匹配,只有整个字符串都匹配了才返回true

    @Test
    public void matches() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22bb23");
        m.matches();// 返回false,因为bb不能被\d+匹配,导致整个字符串匹配未成功.
        Matcher m2 = p.matcher("2223");
        m2.matches();// 返回true,因为\d+匹配到了整个字符串
    }

    // lookingAt()对前面的字符串进行匹配,只有匹配到的字符串在最前面才返回true
    @Test
    public void lookingAt() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22bb23");
        m.lookingAt();// 返回true,因为\d+匹配到了前面的22
        Matcher m2 = p.matcher("aa2223");
        m2.lookingAt();// 返回false,因为\d+不能匹配前面的aa
    }

    // find()对字符串进行匹配,匹配到的字符串可以在任何位置.
    @Test
    public void find() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("22bb23");
        m.find();// 返回true
        Matcher m2 = p.matcher("aa2223");
        m2.find();// 返回true
        Matcher m3 = p.matcher("aa2223bb");
        m3.find();// 返回true
        Matcher m4 = p.matcher("aabb");
        m4.find();// 返回false
    }

    @Test
    public void start() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("aaa2223bb");
        System.err.println(m.find());// 返回true
        // start()返回匹配到的子字符串在字符串中的索引位置.
        System.err.println(m.start());// 返回3
        // end()返回匹配到的子字符串的最后一个字符在字符串中的索引位置.
        System.err.println(m.end());// 返回7,返回的是2223后的索引号
        // group()返回匹配到的子字符串
        System.err.println(m.group());// 返回2223

    }

}
