package top.xmindguoguo.fastjson.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: FastjsonModel
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/4 0:15
 * @Version: v1.0
 */
@Data
public class FastJsonModel implements Serializable {
    private Integer testInteger;
    private Long testLong;
    private Double testDouble;
    private Date testDate;
    private String testString;
    private List<String> testList;
    private InnerClassModel innerClassModel;

    //=============注解
    @JSONField(name = "testJSONFieldAlias")
    private String testJSONField;


    @Data
    public static class InnerClassModel implements Serializable {
        private String name;
    }
}
