package jdk.net;

import java.net.URI;

import org.junit.Test;

public class URITest {
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
        URI uri = new URI(httpUrl);
        // 获取请求协议
        System.err.println(uri.getScheme()); // http url.getProtocol()
//        System.err.println(uri.getHost()); // www.imooc.com
//        // 指定port号-1表明URL应使用的默认端口的协议。
//        System.err.println(uri.getPort()); // -1
//        // 这个 URL的路径部分，或一个空字符串，如果不存在
//        System.err.println(uri.getPath()); /// search/course
//        // 获取此 URL的查询部分
//        System.err.println(uri.getQuery()); // words=springboot&type=mf
//        // 获取此URL的文件名。返回的文件部分将与getPath()相同，加上getQuery()值的连接(如果有的话)。如果没有查询部分，这个方法和getPath()将返回相同的结果。
//        System.err.println(uri.getFile()); /// search/course?words=springboot&type=mf
//        // 这个用户信息是这个 URL一部分，还是 null如果不存在
//        System.err.println(uri.getUserInfo()); // null
//        // 获取此的授权部分 URL 。
//        System.err.println(uri.getAuthority()); // www.imooc.com
//        // 获取此URL的内容。
//        System.err.println(uri.getContent()); // sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@22a71081
//        System.err.println(uri.getDefaultPort()); // 80
//        // 获得这个 URL的锚（也称为“参考”）。
//        System.err.println(uri.getRef()); // 22
//        System.err.println(uri.toExternalForm()); // http://www.imooc.com/search/course?words=springboot&type=mf#22
//        System.err.println(uri.toString()); // 本身调用的也是toExternalForm http://www.imooc.com/search/course?words=springboot&type=mf#22
//        System.err.println(uri.toURI()); // http://www.imooc.com/search/course?words=springboot&type=mf#22
    }
}
