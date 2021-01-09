package top.xmindguoguo.java8.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @ClassName: FilePathMainTest
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/3 11:50
 * @Version: v1.0
 * <p>
 * 对java 获取各种路径的总结
 */
@Slf4j
public class FilePathMainTest {
    String path = "file/test.xml";

    @Test
    public void getResourceFromClassAsStream() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/file/test.xml");
        log.info(IOUtils.toString(inputStream));
    }

    @Test
    public void getResourceFromContextClassLoader() throws IOException {
        URL url =
                Thread.currentThread().getContextClassLoader().getResource(path);
        log.info(IOUtils.toString(url));
    }

    @Test
    public void getResourceFromContextClassLoaderAsStream() throws IOException {
        InputStream is =
                Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        log.info(IOUtils.toString(is));
    }


}