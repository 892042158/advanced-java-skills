package top.xmindguoguo.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.xmindguoguo.fastjson.model.FastJsonModel;

import java.util.ArrayList;
import java.util.Date;

/**
 * @ClassName: FastjsonMainTest
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/4 0:12
 * @Version: v1.0
 */
@Slf4j
public class FastJsonMainTest {
    private static final SerializeConfig config;

    static {
        config = new SerializeConfig(); // 使用默认的时间处理
//        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
//        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };


    @Test
    public void obj2String() {
        FastJsonModel fastJsonModel = new FastJsonModel();
        fastJsonModel.setTestDate(new Date());
        fastJsonModel.setTestInteger(new Integer(1));
        fastJsonModel.setTestDouble(new Double(2.22));
        fastJsonModel.setTestLong(173L);
        fastJsonModel.setTestString("obj2String s");
        fastJsonModel.setTestList(new ArrayList<>());


        FastJsonModel.InnerClassModel innerClassModel = new FastJsonModel.InnerClassModel();
        innerClassModel.setName("my name is innerClassModel");
        fastJsonModel.setInnerClassModel(innerClassModel);

        String value = JSON.toJSONString(fastJsonModel);
        log.info("obj2String:{}", value);
    }


    @Test
    public void string2Obj() {
        String jsonStr = "{\"innerClassModel\":{\"name\":\"my name is innerClassModel\"},\"testDate\":1609691150827,\"testDouble\":2.22,\"testInteger\":1,\"testList\":[],\"testLong\":173,\"testString\":\"obj2String s\"}";
        FastJsonModel model = JSON.parseObject(jsonStr, FastJsonModel.class);
        log.info("lombok to String:{}", model.toString());

    }

    @Test

    public void toJSONString() {
        FastJsonModel fastJsonModel = new FastJsonModel();
        fastJsonModel.setTestDate(new Date());
        fastJsonModel.setTestInteger(new Integer(1));
        fastJsonModel.setTestDouble(new Double(2.22));
        fastJsonModel.setTestLong(173L);
        fastJsonModel.setTestString("obj2String s");
        fastJsonModel.setTestList(new ArrayList<>());


        FastJsonModel.InnerClassModel innerClassModel = new FastJsonModel.InnerClassModel();
        innerClassModel.setName("my name is innerClassModel");
        fastJsonModel.setInnerClassModel(innerClassModel);

        String value = JSON.toJSONString(fastJsonModel, config, features);
        log.info("toJSONString:{}", value);

    }

    @Test

    public void toJSONNoFeatures() {
        FastJsonModel fastJsonModel = new FastJsonModel();
        fastJsonModel.setTestDate(new Date());
        fastJsonModel.setTestInteger(new Integer(1));
        fastJsonModel.setTestDouble(new Double(2.22));
        fastJsonModel.setTestLong(173L);
        fastJsonModel.setTestString("obj2String s");
        fastJsonModel.setTestList(new ArrayList<>());


        FastJsonModel.InnerClassModel innerClassModel = new FastJsonModel.InnerClassModel();
        innerClassModel.setName("my name is innerClassModel");
        fastJsonModel.setInnerClassModel(innerClassModel);
        String value = JSON.toJSONString(fastJsonModel, config);
        log.info("toJSONNoFeatures:{}", value);

    }

    /**
     * 字段别名
     */
    @Test
    public void testJSONField() {
        FastJsonModel fastJsonModel = new FastJsonModel();
        fastJsonModel.setTestJSONField("field name is change");
        log.info("toString:{}", JSON.toJSONString(fastJsonModel));// toString:{"testJSONFieldAlias":"field name is change"}
    }

    /**
     * public @interface JSONField {
     *     // 配置序列化和反序列化的顺序，1.1.42版本之后才支持
     *     int ordinal() default 0;
     *
     *      // 指定字段的名称
     *     String name() default "";
     *
     *     // 指定字段的格式，对日期格式有用
     *     String format() default "";
     *
     *     // 是否序列化
     *     boolean serialize() default true;
     *
     *     // 是否反序列化
     *     boolean deserialize() default true;
     * }
     */

}