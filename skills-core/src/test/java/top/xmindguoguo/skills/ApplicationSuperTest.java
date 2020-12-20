package top.xmindguoguo.skills;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: ApplicationSuperTest
 * @author: 于国帅
 * @Description:
 * @Date: 2020/11/28 16:29
 * @Version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationSuperTest {

    public void printJSONString(Object o) {
        log.info("====printObj===========");
        log.info(JSON.toJSONString(o));
        log.info("=====printObj==========");

    }

    public void print(Object o) {
        log.info("====print===========");
        log.info(String.valueOf(o));
        log.info("=====print==========");
    }
}
