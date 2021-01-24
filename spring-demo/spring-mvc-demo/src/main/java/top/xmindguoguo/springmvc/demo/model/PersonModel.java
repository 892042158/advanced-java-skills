package top.xmindguoguo.springmvc.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/24 23:38
 * @Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonModel implements Serializable {
    private Long Id;
    private String name;
    private Integer age;
    //日期格式化
    private Date date;
}
