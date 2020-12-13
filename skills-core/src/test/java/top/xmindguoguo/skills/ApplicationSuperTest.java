package top.xmindguoguo.skills;


import com.alibaba.fastjson.JSON;
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
public class ApplicationSuperTest {

    public void print(Object o) {
        System.err.println("====print===========");
        System.err.println(JSON.toJSONString(o));
        System.err.println("=====print==========");

    }
}
