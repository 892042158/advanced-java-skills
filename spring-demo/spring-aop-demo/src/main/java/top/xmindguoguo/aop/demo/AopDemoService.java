package top.xmindguoguo.aop.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/21 0:01
 * @Version: v1.0
 */
@Slf4j
@Service
public class AopDemoService {
    @AopDemo
    public String aopMethod() {
        return "aopMethod";
    }

}
