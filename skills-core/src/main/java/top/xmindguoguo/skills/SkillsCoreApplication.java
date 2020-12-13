package top.xmindguoguo.skills;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication()
@MapperScan({"top.xmindguoguo.skills.demo.mybatis"}) //扫描的mapper
public class SkillsCoreApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SkillsCoreApplication.class, args);
//        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}");
    }
}