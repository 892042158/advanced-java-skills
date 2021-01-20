package top.xmindguoguo.aop.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.xmindguoguo.aop.SpringAopMain;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/21 0:14
 * @Version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringAopMain.class)
@Slf4j
public class AopDemoServiceTest {
    @Autowired
    AopDemoService aopDemoService;

    @Test
    public void aopMethod() {
        aopDemoService.aopMethod();
    }
}