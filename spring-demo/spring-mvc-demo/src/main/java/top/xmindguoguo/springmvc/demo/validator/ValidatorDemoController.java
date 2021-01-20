package top.xmindguoguo.springmvc.demo.validator;

import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.xmindguoguo.springmvc.demo.validator.model.RequestBodyModel;

import javax.validation.Valid;
import javax.validation.constraints.Email;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/19 0:05
 * @Version: v1.0
 * <p>
 * springmvc 验证相关的例子
 * @see https://www.cnblogs.com/mr-yang-localhost/p/7812038.html
 * @see https://blog.csdn.net/liyanqiang19/article/details/107318650/
 */
@RestController
@RequestMapping("/validatorDemo")
@Validated  //注意：在类级别上标注@Validated注解告诉Spring需要校验方法参数上的约束
public class ValidatorDemoController {
    /**
     * MethodArgumentNotValidException
     * 這@Valid 和@Validated两个注解可以混用
     *
     * @param model
     * @return
     */
    @RequestMapping("/vaildRequestBodyModel")
    ResponseEntity<String> vaildRequestBodyModel(@Valid @RequestBody RequestBodyModel model) {
        return ResponseEntity.ok("@Valid");
    }

    @RequestMapping("/validatedRequestBodyModel")
    ResponseEntity<String> validatedRequestBodyModel(@Validated @RequestBody RequestBodyModel model) {
        return ResponseEntity.ok("@Validated");
    }

    @RequestMapping("/validatedRequestParam")
    ResponseEntity<String> validatedRequestParam(@Email @RequestParam("email") String email,
                                                 @Range(min = 10, max = 50, message = "pageSize 的数量取值范围为[10-50]") @RequestParam("pageSize") String pageSize

    ) {
        return ResponseEntity.ok("validatedRequestParam");
    }
}
