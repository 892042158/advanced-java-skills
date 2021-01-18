package org.apache.velocity.app;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/18 0:14
 * @Version: v1.0
 */
public class VelocityEngineTest {

    VelocityEngine velocityEngine = new VelocityEngine();

    @Before
    public void setUp(){

        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
    }

    @Test
    public  void test(){
        // 载入（获取）模板对象
        Template t = velocityEngine.getTemplate("helloVelocity.vm");
        VelocityContext ctx = new VelocityContext();
        // 域对象加入参数值
        ctx.put("name", "李智龙");
        ctx.put("date", (new Date()).toString());
        // list集合
        List temp = new ArrayList();
        temp.add("1");
        temp.add("2");
        ctx.put("list", temp);

        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        System.out.println(sw.toString());


    }

}
