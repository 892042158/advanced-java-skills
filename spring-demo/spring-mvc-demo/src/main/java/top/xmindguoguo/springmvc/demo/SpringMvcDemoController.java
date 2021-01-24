package top.xmindguoguo.springmvc.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/19 0:29
 * @Version: v1.0
 */
@RequestMapping("/")
@Controller
public class SpringMvcDemoController {
    @RequestMapping("")
    ResponseEntity<String> index() {
        //todo  启动的时候显示加载的springmv的url
        return ResponseEntity.ok("hi SpringMvcDemo!");
    }

}
