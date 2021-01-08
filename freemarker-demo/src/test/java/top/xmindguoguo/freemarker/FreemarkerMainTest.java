package top.xmindguoguo.freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FreemarkerMainTest
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/9 2:25
 * @Version: v1.0
 */
public class FreemarkerMainTest {
    @Test
    public void test() {
        Configuration cfg = new Configuration();
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        String templateContent = "欢迎：${name}";
        stringLoader.putTemplate("myTemplate", templateContent);

        cfg.setTemplateLoader(stringLoader);

        try {
            Template template = cfg.getTemplate("myTemplate", "utf-8");
            Map root = new HashMap();
            root.put("name", "javaboy2012");

            StringWriter writer = new StringWriter();
            try {
                template.process(root, writer);
                System.out.println(writer.toString());
            } catch (TemplateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}