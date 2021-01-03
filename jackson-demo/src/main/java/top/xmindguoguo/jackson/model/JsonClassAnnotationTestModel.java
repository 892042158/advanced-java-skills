package top.xmindguoguo.jackson.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: JsonIgnorePropertiesTestModel
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/20 19:39
 * @Version: v1.0
 */
@Data
//JsonInclude 序列化的转换策略   Include.NON_NULL 如果bean实例的某一个属性为null，那么序列化时忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"id", "memo"})  //可以注明是想要忽略的属性列表如@JsonIgnoreProperties({"name","age","title"})，
//@JsonIgnoreProperties(ignoreUnknown=true)  //string2obj 字段列表不同会报错  他就不会了
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class JsonClassAnnotationTestModel {
    private Long id;
    private String name;
    private Integer age;
    private String memo;
    private Date createTime;
    private Date updateTime;
    private String testJsonInclude;

}
