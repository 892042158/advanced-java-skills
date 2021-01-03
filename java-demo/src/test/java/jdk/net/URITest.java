package jdk.net;

import org.junit.Test;

import java.net.URI;

public class URITest {
    String httpUrl = "http://www.imooc.com/search/course?words=springboot&type=mf#22";

    /**
     * scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
     * <p>
     * 一个完整的 url 是由上面这些字段组成的。一般情况下我们访问的网络没有用户名密码验证，所以都没有输user:password
     *
     * @throws Exception void
     * @Title test
     * @author 于国帅
     * @date 2019年4月6日 下午8:10:46
     */
    @Test
    public void test() throws Exception {
        URI uri = new URI(httpUrl);
        // 获取请求协议
        System.err.println(uri.getScheme()); // http url.getProtocol()

    }
}
