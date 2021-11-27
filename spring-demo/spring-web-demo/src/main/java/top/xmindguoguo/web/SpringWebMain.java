package top.xmindguoguo.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import top.xmindguoguo.interceptor.LoggingClientHttpRequestInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SpringBootMain
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/13 16:20
 * @Version: v1.0
 */
@SpringBootApplication
public class SpringWebMain {
    public static void main(String[] args) {
            ApplicationContext ctx = SpringApplication.run(SpringWebMain.class, args);
//        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}");
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate =  new RestTemplate();
        // 添加自定义拦截器
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList();
        interceptors.add(new LoggingClientHttpRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        //提供对传出/传入流的缓冲,可以让响应body多次读取(如果不配置,拦截器读取了Response流,再响应数据时会返回body=null)
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
        return  restTemplate;
    }
}
