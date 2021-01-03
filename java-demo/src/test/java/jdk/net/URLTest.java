package jdk.net;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * URI: 表示一个统一资源标识符 (URI) 引用。英文全称是UniformResource Identifiers。
 * URL:代表一个统一资源定位符，它是指向一个互联网“资源”的指针。互联网是关键，URL指向的资源都是网络资源，所以它需要指定一个具体的协议。
 */
@Slf4j
public class URLTest {

    /**
     * 网络资源
     */
    private static final String HTTP_URL = "http://www.imooc.com/search/course?words=springboot&type=mf#22";
    /**
     * 本地资源
     */
    private static final String LOCALHOST_RELATIVE_PATH = "/file/test.xml";


    /**
     * scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
     * <p>
     * 一个完整的 url 是由上面这 些字段组成的。一般情况下我们访问的网络没有用户名密码验证，所以都没有输user:password
     *
     * @throws Exception void
     * @Title test
     * @author 于国帅
     * @date 2019年4月6日 下午8:10:46
     */
    @Test
    public void httpUrl() throws Exception {
        URL url = new URL(HTTP_URL);
        log.info("请求协议:{}", url.getProtocol()); // http
        log.info("host:{}", url.getHost()); // www.imooc.com
        // 指定port号-1表明URL应使用的默认端口的协议。
        log.info("port:{}", url.getPort()); // -1
        log.info("URL的路径部分:{}", url.getPath()); /// search/course
        log.info("查询参数:{}", url.getQuery()); // words=springboot&type=mf
        // 获取此URL的文件名。返回的文件部分将与getPath()相同，加上getQuery()值的连接(如果有的话)。如果没有查询部分，这个方法和getPath()将返回相同的结果。
        log.info("文件名:{}", url.getFile()); /// search/course?words=springboot&type=mf
        // 这个用户信息是这个 URL一部分，还是 null如果不存在
        log.info("url.getUserInfo:{}:", url.getUserInfo()); // null
        log.info("授权部分 URL:{}", url.getAuthority()); // www.imooc.com
        log.info("getContent:{}", url.getContent()); // sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@22a71081
        log.info("url.getDefaultPort():{}", url.getDefaultPort()); // 80
        // 获得这个 URL的锚（也称为“参考”）。
        log.info(url.getRef()); // 22
        log.info(url.toExternalForm()); // http://www.imooc.com/search/course?words=springboot&type=mf#22
        log.info(url.toString()); // 本身调用的也是toExternalForm http://www.imooc.com/search/course?words=springboot&type=mf#22
        log.info(url.toURI().toString()); // http://www.imooc.com/search/course?words=springboot&type=mf#22
        log.info(url.getPath()); // http://www.imooc.com/search/course?words=springboot&type=mf#22

    }

    @Test
    public void localhostUrl() throws Exception {
        URL url = this.getClass().getResource(LOCALHOST_RELATIVE_PATH);
        log.info("请求协议:{}", url.getProtocol()); // file
        log.info("host:{}", url.getHost()); //
        // 指定port号-1表明URL应使用的默认端口的协议。
        log.info("port:{}", url.getPort()); // -1
        log.info("URL的路径部分:{}", url.getPath()); ///F:/github/advanced-java-skills/java-demo/target/classes/file/test.xml
        log.info("查询参数:{}", url.getQuery()); //
        // 获取此URL的文件名。返回的文件部分将与getPath()相同，加上getQuery()值的连接(如果有的话)。如果没有查询部分，这个方法和getPath()将返回相同的结果。
        log.info("文件名:{}", url.getFile()); /// /F:/github/advanced-java-skills/java-demo/target/classes/file/test.xml
        // 这个用户信息是这个 URL一部分，还是 null如果不存在
        log.info("url.getUserInfo:{}:", url.getUserInfo()); // null
        log.info("授权部分 URL:{}", url.getAuthority()); //
        log.info("getContent:{}", url.getContent()); // java.io.BufferedInputStream@44e81672
        log.info("url.getDefaultPort():{}", url.getDefaultPort());
        log.info(url.getRef()); // 22
        log.info(url.toExternalForm());
        log.info(url.toString());
        log.info(url.toURI().toString());
        log.info(url.getPath());
    }

    // 打开这个请求 ，然后将页面爬取下来
    @Test
    public void openStream() throws Exception {
        URL url = new URL(HTTP_URL);
        InputStream inputStream = url.openStream();
        try {
            log.info(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
        } finally {
            inputStream.close();
        }
    }


}
