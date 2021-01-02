package jdk.net;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class URLTest {
    String httpUrl = "http://www.imooc.com/search/course?words=springboot&type=mf#22";

    /**
     * scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
     * 
     * 一个完整的 url 是由上面这些字段组成的。一般情况下我们访问的网络没有用户名密码验证，所以都没有输user:password
     * 
     * @Title test
     * @author 于国帅
     * @date 2019年4月6日 下午8:10:46
     * @throws Exception
     *             void
     */
    @Test
    public void test() throws Exception {
        URL url = new URL(httpUrl);
        // 获取请求协议
        System.err.println(url.getProtocol()); // http
        System.err.println(url.getHost()); // www.imooc.com
        // 指定port号-1表明URL应使用的默认端口的协议。
        System.err.println(url.getPort()); // -1
        // 这个 URL的路径部分，或一个空字符串，如果不存在
        System.err.println(url.getPath()); /// search/course
        // 获取此 URL的查询部分
        System.err.println(url.getQuery()); // words=springboot&type=mf
        // 获取此URL的文件名。返回的文件部分将与getPath()相同，加上getQuery()值的连接(如果有的话)。如果没有查询部分，这个方法和getPath()将返回相同的结果。
        System.err.println(url.getFile()); /// search/course?words=springboot&type=mf
        // 这个用户信息是这个 URL一部分，还是 null如果不存在
        System.err.println(url.getUserInfo()); // null
        // 获取此的授权部分 URL 。
        System.err.println(url.getAuthority()); // www.imooc.com
        // 获取此URL的内容。
        System.err.println(url.getContent()); // sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@22a71081
        System.err.println(url.getDefaultPort()); // 80
        // 获得这个 URL的锚（也称为“参考”）。
        System.err.println(url.getRef()); // 22
        System.err.println(url.toExternalForm()); // http://www.imooc.com/search/course?words=springboot&type=mf#22
        System.err.println(url.toString()); // 本身调用的也是toExternalForm http://www.imooc.com/search/course?words=springboot&type=mf#22
        System.err.println(url.toURI()); // http://www.imooc.com/search/course?words=springboot&type=mf#22
    }

    // 打开这个请求 ，然后将页面爬取下来
    @Test
    public void openStream() throws Exception {
        URL url = new URL(httpUrl);
        InputStream input = url.openStream();
        List<String> strList = IOUtils.readLines(input);
        System.err.println(StringUtils.join(strList, "\n\r"));
    }
}
