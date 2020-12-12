package top.xmindguoguo.skills;

import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @ClassName: SkillsCoreApplicationTest
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/13 0:31
 * @Version: v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillsCoreApplicationTest {
    public void print(Object o) {
        System.err.println("====print===========");
        System.err.println(JSON.toJSONString(o));
        System.err.println("=====print==========");

    }
}