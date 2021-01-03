package top.xmindguoguo.jackson.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import top.xmindguoguo.jackson.ext.DateJsonDeserializer;
import top.xmindguoguo.jackson.ext.DateJsonSerializer;

import java.util.Date;

/**
 * @ClassName: Student
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/20 1:35
 * @Version: v1.0
 */
@Data
public class JacksonTestModel {
    private String name;
    private Integer age;

    @JsonIgnore  //作用在字段或方法上，用来完全忽略被注解的字段和方法对应的属性.
    private String testJsonIgnore;

    @JsonProperty("testAlias")
    private String testJsonProperty;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") //    //设置时区为上海时区，时间格式自己据需求定。
    private Date testTime;

    @JsonDeserialize(using = DateJsonDeserializer.class)
    @JsonSerialize(using = DateJsonSerializer.class)
    private Date testJsonSerialize;

    @JsonPropertyOrder(alphabetic = true)
    private String bJsonPropertyOrder;

    @JsonPropertyOrder(alphabetic = true)
    private String cJsonPropertyOrder;

    @JsonUnwrapped  //对象扁平化  也就是 被注解的对象的属性合并到当前model显示
    private JsonAutoDetectTestModel jsonAutoDetectTestModel;

    @JsonUnwrapped  //测试存在属性重名的效果
    private JsonClassAnnotationTestModel jsonClassAnnotationTestModel;



    //声明一般视图接口 只允许这个视图返回用户名属性
    public interface simpleJSONView{};
}

