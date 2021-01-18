package top.xmindguoguo.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/16 2:05
 * @Version: v1.0
 */
@SpringBootApplication
public class RedisMain {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RedisMain.class, args);
    }
}
