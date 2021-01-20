package jdk.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/21 0:36
 * @Version: v1.0
 */
@Slf4j
public class RuntimeTest {

    /**
     * @see https://www.cnblogs.com/xiaoxiong2015/p/12705438.html
     */
    @Test
    public void availableProcessors() {
        log.info("Java虚拟机可用的处理器数:{}", Runtime.getRuntime().availableProcessors());
    }
}
