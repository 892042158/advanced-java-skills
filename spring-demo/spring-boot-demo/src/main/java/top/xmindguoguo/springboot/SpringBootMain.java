package top.xmindguoguo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName: SpringBootMain
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/13 16:20
 * @Version: v1.0
 */
@SpringBootApplication()
public class SpringBootMain {
    public static void main(String[] args) {
            ApplicationContext ctx = SpringApplication.run(SpringBootMain.class, args);
//        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}");
    }
}
