package org.springframework.web.client;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.xmindguoguo.web.SpringWebMainTest;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/23 0:43
 * @Version: v1.0
 * @see
 * https://www.cnblogs.com/yongguang1990/p/10223857.html
 * https://blog.csdn.net/qq_26878363/article/details/96161133
 * https://www.cnblogs.com/javazhiyin/p/9851775.html
 * 初步想法
 * 1.将常见的请求的接口的测试都写出来
 * 2.实际映射到是这个项目启动的
 */
@Slf4j
public class RestTemplateTest extends SpringWebMainTest {
    @Autowired
    RestTemplate restTemplate;
    @Test
    public void test(){


    }
}
