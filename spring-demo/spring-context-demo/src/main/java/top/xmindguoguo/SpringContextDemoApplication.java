package top.xmindguoguo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @ClassName: SpringBootMain
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/13 16:20
 * @Version: v1.0
 */
@SpringBootApplication()
//@EnableAsync(mode = AdviceMode.ASPECTJ)
@EnableAsync()
//@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
//@EnableAspectJAutoProxy()
public class SpringContextDemoApplication {
    public static void main(String[] args) {
            ApplicationContext ctx = SpringApplication.run(SpringContextDemoApplication.class, args);
//        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}");
    }
}
