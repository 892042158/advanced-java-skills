package top.xmindguoguo.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;

/**
 * @ClassName: SpringBootMain
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/13 16:20
 * @Version: v1.0
 */
@SpringBootApplication
public class SpringAopMain {
    @Async
    public static void main(String[] args) {
            ApplicationContext ctx = SpringApplication.run(SpringAopMain.class, args);
//        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}");
    }
}
