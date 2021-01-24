package top.xmindguoguo.springmvc.demo;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import top.xmindguoguo.springmvc.demo.model.PersonModel;

import java.util.Date;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/25 0:16
 * @Version: v1.0
 */
public class RestfulControllerTest {
    @Test
    public void test() {
        PersonModel personModel = new PersonModel();

        personModel.setId(System.currentTimeMillis());
        personModel.setName("任帆帆");
        personModel.setAge(18);
        personModel.setDate(new Date());
        System.err.println(JSON.toJSONString(personModel));

    }
}