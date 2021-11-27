package org.springframework.web.client;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import top.xmindguoguo.web.SpringWebMainTest;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/23 0:43
 * @Version: v1.0
 * @see
 * https://www.cnblogs.com/yongguang1990/p/10223857.html
 * https://blog.csdn.net/qq_26878363/article/details/96161133
 * https://www.cnblogs.com/javazhiyin/p/9851775.html
 * 初步想法
 * 1.将常见的请求的接口的测试都写出来  以请求csdn为例子
 * 2.实际映射到是这个项目启动的
 *
 * 3.然后写一个对应的http的打印的拦截器，然后可以练习一下
 *
 */
@Slf4j
public class RestTemplateTest extends SpringWebMainTest {
    @Autowired
    RestTemplate restTemplate;
    @Test
    public void test(){

    }
    @Test
    public void get(){
        String url  = "https://blog.csdn.net/mengxiangxingdong/category_9293900.html?spm=1001.2014.3001.5482";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
        log.info("status =  {}",responseEntity.getStatusCode());
        log.info("headers =  {}",responseEntity.getHeaders());
        log.info("body =  {}",responseEntity.getBody());
    }

    /**
     * 现在好奇的是构造方法的request 可以造成几种方式
     * 思路：
     *  1.首先整理一下有多少方式可以传入request
     *
     *      HttpEntity
     *         -- RequestEntity
     *
     *      RequestCallback
     *          --AcceptHeaderRequestCallback
     *              -- HttpEntityRequestCallback
     *
     *  2.然后整理一下怎么才能够传递
     *
     */
    @Test
    public void post(){
        String url  = "https://passport.csdn.net/v1/api/get/mobileAreaCode";
        JSONObject json = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<JSONObject> formEntity = new HttpEntity<>(json, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,formEntity,String.class);
        log.info("status =  {}",responseEntity.getStatusCode());
        log.info("headers =  {}",responseEntity.getHeaders());
        log.info("body =  {}",responseEntity.getBody());
    }

    /***
     *
     */
    @Test
    public void postDemo(){

    }





}
