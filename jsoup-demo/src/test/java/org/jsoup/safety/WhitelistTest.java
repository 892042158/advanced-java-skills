package org.jsoup.safety;

import org.jsoup.Jsoup;
import org.junit.Test;

/**
 * 单元测试 jsoup 的clean 的方法
 * 
 * @ClassName WhitelistTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月16日 下午3:07:47
 *
 */
public class WhitelistTest {
    String strHTML = "<html>" + "<head>" + "<title> Clean HTML By Jsoup Whitelist</title>" + "</head>" + "<body bgcolor=\"000000\">"
            + "<center><img src=\"image.jpg\" align=\"bottom\"> </center>" + "<hr>"
            + "<a href=\"http://blog.csdn.net/dietime1943\">bluetata</a>" + "<h1>heading tags H1</h1>" + "<h2>heading tags H2</h2>"
            + "My email link <a href=\"mailto:dietime1943@gmail.com\">" + "dietime1943@gmail.com</a>." + "<p>Para tag</p>"
            + "<p><b>bold paragraph</b>" + "<br><b><i>bold italics text.</i></b>" + "<hr>Horizontal line" + "</body>" + "</html>";

    @Test
    public void none() {
        // clean HTML using none whitelist (remove all HTML tags)
        String cleanedHTML = Jsoup.clean(strHTML, Whitelist.none());
        System.out.println("None whitelist");
        System.out.println(cleanedHTML);
    }

    @Test
    public void relaxed() {
        // clean HTML using relaxed whitelist
        String cleanedHTML = Jsoup.clean(strHTML, Whitelist.relaxed());
        System.out.println("Relaxed whitelist");
        System.out.println(cleanedHTML);
    }

    @Test
    public void basic() {
        String html = "<p><ahref='http://www.baidu/' onclick='stealCookies()'> 百度一下，你就知道 </a></p>";
        String doc = Jsoup.clean(html, Whitelist.basic());
        System.err.println(doc);
        // 输出：<p><a href="http://www.baidu/"rel="nofollow"> 百度一下，你就知道 </a></p>
        String baseUri = "http://blog.csdn.net";
        html = "<p><a href='http://blog.csdn.net/xyw_eliot' onclick='stealCookies()'> Eliot </a></p>";
        doc = Jsoup.clean(html, baseUri, Whitelist.basic());
        System.out.println(doc);
    }

}
