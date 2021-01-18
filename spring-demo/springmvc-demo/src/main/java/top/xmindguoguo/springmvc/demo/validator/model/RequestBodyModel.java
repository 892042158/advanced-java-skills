package top.xmindguoguo.springmvc.demo.validator.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/19 0:07
 * @Version: v1.0
 */
@Data
public class RequestBodyModel {
    @Min(1)
    @Max(10)
    private Integer minMax;

    @Email
    private String email;
}
