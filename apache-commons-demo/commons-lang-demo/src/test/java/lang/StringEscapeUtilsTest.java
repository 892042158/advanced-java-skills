package lang;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Test;

public class StringEscapeUtilsTest {
    @Test
    public void test() {
        String sql = StringEscapeUtils.escapeSql(
                "insert into d01fa9f994f14a42976810b3a6488eaa1522808229702dashuju2 (titleRow,memo,time,state) VALUES('1', '1', '3', '4')");
        System.out.println(sql);
    }

    @Test
    public void testC() {
        String sql = "1' or '1'='1";
        System.out.println("防SQL注入:" + StringEscapeUtils.escapeSql(sql)); // 防SQL注入

        System.out.println("转义HTML,注意汉字:" + StringEscapeUtils.escapeHtml("<font>chen磊  xing</font>")); // 转义HTML,注意汉字
//      System.out.println("反转义HTML:"+StringEscapeUtils.unescapeHtml3("<font>chen磊  xing</font>")); //反转义HTML

        System.out.println("转成Unicode编码：" + StringEscapeUtils.escapeJava("张三")); // 转义成Unicode编码

        System.out.println("转义XML：" + StringEscapeUtils.escapeXml("<name>张三</name>")); // 转义xml
        System.out.println("反转义XML：" + StringEscapeUtils.unescapeXml("<name>张三</name>"));
    }

    @Test
    public void escapeHtml() {
        System.out.println(StringEscapeUtils.escapeHtml("<font>chen磊  xing</font>")); // 转义HTML,注意汉字
    }
}
