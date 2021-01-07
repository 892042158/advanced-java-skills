package top.xmindguoguo.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.serializer.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import top.xmindguoguo.fastjson.model.FastJsonModel;

import java.io.StringReader;
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
    private  SerializeConfig config;
    private  FastJsonModel fastJsonModel;

    @Before
    public void setUp() {
        initSerializeConfig();
        initFastJsonModel();
    }

    private void initFastJsonModel() {
        fastJsonModel = new FastJsonModel();
        fastJsonModel.setTestDate(new Date());
        fastJsonModel.setTestInteger(new Integer(1));
        fastJsonModel.setTestDouble(new Double(2.22));
        fastJsonModel.setTestLong(173L);
        fastJsonModel.setTestList(new ArrayList<>());
        FastJsonModel.InnerClassModel innerClassModel = new FastJsonModel.InnerClassModel();
        innerClassModel.setName("my name is innerClassModel");
        fastJsonModel.setInnerClassModel(innerClassModel);
    }

    private void initSerializeConfig() {
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
        fastJsonModel.setTestString("obj2String s");
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
        fastJsonModel.setTestString("obj2String config features");
        String value = JSON.toJSONString(fastJsonModel, config, features);
        log.info("toJSONString:{}", value);

    }

    @Test

    public void toJSONNoFeatures() {
        fastJsonModel.setTestString("obj2String config");
        String value = JSON.toJSONString(fastJsonModel, config);
        log.info("toJSONNoFeatures:{}", value);
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
    @Test
    public void testJSONField() {
        FastJsonModel fastJsonModel = new FastJsonModel();
        fastJsonModel.setTestJSONField("field name is change");
        log.info("toString:{}", JSON.toJSONString(fastJsonModel));// toString:{"testJSONFieldAlias":"field name is change"}
    }

    /**
     * 在某些场景下，对Value做过滤，需要获得所属JavaBean的信息，包括类型、字段、方法等。
     * 在fastjson-1.2.9中，提供了ContextValueFilter，类似于之前版本提供的ValueFilter，只是多了BeanContext参数可用。
     */
    @Test
    public void givenContextFilter_whenJavaObject_thanJsonCorrect() {

        fastJsonModel.setTestString("ContextValueFilter");
        ContextValueFilter valueFilter = new ContextValueFilter () {
            public Object process(
                    BeanContext context, Object object, String name, Object value) {

                if (value!=null&&value.equals("ContextValueFilter")) {
                    return ((String) value).toUpperCase();
                } else {
                    return null;
                }
            }
        };
        String jsonOutput = JSON.toJSONString(fastJsonModel, valueFilter);
        log.info(jsonOutput);
    }

    /**
     * NameFilter: 序列化时修改 Key。
     *
     * SerializeConfig：内部是个map容器主要功能是配置并记录每种Java类型对应的序列化类。
     */

    @Test
    public void givenSerializeConfig_whenJavaObject_thanJsonCorrect() {
        NameFilter formatName = new NameFilter() {
            public String process(Object object, String name, Object value) {
                return name.toLowerCase().replace(" ", "_");
            }
        };
        fastJsonModel.setTestDate(new Date());
        SerializeConfig.getGlobalInstance().addFilter(FastJsonModel.class,  formatName);
        String jsonOutput =
                JSON.toJSONStringWithDateFormat(fastJsonModel, "yyyy-MM-dd");
        log.info(jsonOutput);

    }


    /**
     * 读取大文件
     */
    @Test
    public  void readBigJson() {
        String json = "{" + "\"array\": [1,2,3]," + "\"arraylist\": [" + "{\"a\": \"b\"," + "\"c\": \"d\"," + "\"e\": \"f\"},"
                + "{\"a\": \"b\"," + "\"c\": \"d\"," + "\"e\": \"f\"}," + "{\"a\": \"b\"," + "\"c\": \"d\"," + "\"e\": \"f\"}  " + "],"
                + "\"object\": {" + "\"a\": \"b\"," + "\"c\": \"d\"," + "\"e\": \"f\"}," + "\"string\": \"Hello World\"" + "}";
        // 如果json数据以形式保存在文件中，用FileReader进行流读取，path为json数据文件路径。
        // JSONReader reader = new JSONReader(new FileReader(path));
        // 为了直观，方便运行，就用StringReader做示例！
        JSONReader reader = new JSONReader(new StringReader(json));
        reader.startObject();
        System.out.print("start read json with fastjson");
        while (reader.hasNext()) {
            String key = reader.readString();
            System.out.println("key " + key);
            if (key.equals("array")) {
                reader.startArray();
                System.out.println("start " + key);
                while (reader.hasNext()) {
                    String item = reader.readString();
                    System.out.println(item);
                }
                reader.endArray();
                System.out.println("end " + key);
            } else if (key.equals("arraylist")) {
                reader.startArray();
                System.out.println("start " + key);
                while (reader.hasNext()) {
                    reader.startObject();
                    System.out.println("start arraylist item");
                    while (reader.hasNext()) {
                        String arrayListItemKey = reader.readString();
                        String arrayListItemValue = reader.readObject().toString();
                        System.out.print("key " + arrayListItemKey);
                        System.out.println(":value " + arrayListItemValue);
                    }
                    reader.endObject();
                    System.out.println("end arraylist item");
                }
                reader.endArray();
                System.out.println("end " + key);
            } else if (key.equals("object")) {
                reader.startObject();
                System.out.println("start object item");
                while (reader.hasNext()) {
                    String objectKey = reader.readString();
                    String objectValue = reader.readObject().toString();
                    System.out.print("key " + objectKey);
                    System.out.println(":value " + objectValue);
                }
                reader.endObject();
                System.out.println("end object item");
            } else if (key.equals("string")) {
                System.out.println("start string");
                String value = reader.readObject().toString();
                System.out.println("value " + value);
                System.out.println("end string");
            }
        }
        reader.endObject();
        System.out.println("start fastjson");
    }

}