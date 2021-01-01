package top.xmindguoguo.skills.demo.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.xmindguoguo.skills.demo.jackson.model.JacksonTestModel;
import top.xmindguoguo.skills.demo.jackson.model.JsonAutoDetectTestModel;
import top.xmindguoguo.skills.demo.jackson.model.JsonClassAnnotationTestModel;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName: JacksonMainTest
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/20 1:39
 * @Version: v1.0
 */
@Slf4j
public class JacksonMainTest {
    private static ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void obj2String() throws JsonProcessingException {
        JacksonTestModel model = new JacksonTestModel();
        model.setName("test jackson");
        model.setAge(22);
        String jsonStr = objectMapper.writeValueAsString(model);
        log.info(jsonStr);
    }

    @Test
    public void obj2StringPretty() throws JsonProcessingException {
        JacksonTestModel model = new JacksonTestModel();
        model.setName("test jackson");
        model.setAge(22);
        String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
        log.info(jsonStr);
    }

    @Test
    public void string2Obj() throws IOException {
        String str = "{\"name\":\"name\",\"age\":10}";
        JacksonTestModel model = objectMapper.readValue(str, JacksonTestModel.class);
        log.info(String.valueOf(model));
    }
//


    @Test
    public void testJsonAutoDetectAnnotation() throws JsonProcessingException {
        /**
         * 思路 1.先打印一下默认的策略  关闭注解类上的所有注解，新建四个作用域的字段
         *      private String testPrivate;
         *     String testDefault;
         *     protected String testProtected;
         *     public String testPublic;
         *
         *     2.然后设置不同的策略测试一下  只测试 fieldVisibility:字段的可见级别
         *     jackson默认的字段属性发现规则如下：
         *   所有被public修饰的字段->所有被public修饰的getter->所有被public修饰的setter
         */
        JsonAutoDetectTestModel model = new JsonAutoDetectTestModel();
        log.info(objectMapper.writeValueAsString(model)); //{"testPublic":null}
        // Visibility.DEFAULT 默认的策略  {"testPublic":null}
        // Visibility.ANY 任何级别的字段都可以自动识别  {"testPrivate":null,"testDefault":null,"testProtected":null,"testPublic":null}
        // Visibility.NONE 所有字段都不可以自动识别 {}
        // Visibility.NON_PRIVATE 非private修饰的字段可以自动识别  {"testDefault":null,"testProtected":null,"testPublic":null}
        // Visibility.PROTECTED_AND_PUBLIC 被protected和public修饰的字段可以被自动识别 {"testProtected":null,"testPublic":null}
        // Visibility.PUBLIC_ONLY 只有被public修饰的字段才可以被自动识别 {"testPublic":null}
    }

    @Test
    public void testJsonIgnoreAnnotation() throws JsonProcessingException {
        JacksonTestModel model = new JacksonTestModel();
        model.setName("xmind-果果");
        model.setAge(25);
        model.setTestJsonIgnore("aaaaa");
        log.info(objectMapper.writeValueAsString(model)); //{"name":"xmind-果果","age":25}
    }

    /**
     * @JsonProperty 设置别名
     */
    @Test
    public void testAnJsonPropertyAnnotation() throws IOException {
        //obj2string
        JacksonTestModel model = new JacksonTestModel();
        model.setName("xmind-果果");
        model.setAge(25);
        model.setTestJsonProperty("aaaaa");
        log.info(objectMapper.writeValueAsString(model)); //{"name":"xmind-果果","age":25,"testJsonInclude":null,"testTime":null,"testAlias":"aaaaa"}

        //string2obj
        String str = "{\"name\":\"name\",\"age\":10,\"testAlias\":\"testAlias\"}";
        JacksonTestModel string2obj = objectMapper.readValue(str, JacksonTestModel.class);
        log.info(string2obj.getTestJsonProperty()); //testAlias

    }

    /**
     * JsonFormat 日期格式化
     *
     * @throws IOException
     */
    @Test
    public void testJsonFormatAnnotation() throws IOException {
        JacksonTestModel model = new JacksonTestModel();
        model.setTestTime(new Date());
        String value = objectMapper.writeValueAsString(model);
        log.info(value); //{"name":null,"age":null,"testJsonInclude":null,"testTime":"2020-12-20","testAlias":null}
    }


    //

    /**
     * 作用在类上，用来说明有些属性在序列化/反序列化时需要忽略掉，可以将它看做是@ JsonIgnore 的批量操作，但它的功能比@JsonIgnore要强，比如一个类是代理类，我们无法将将@JsonIgnore标记在属性或方法上，此时便可用@JsonIgnoreProperties标注在类声明上，它还有一个重要的功能是作用在反序列化时解析字段时过滤一些未知的属性，否则通常情况下解析到我们定义的类不认识的属性便会抛出异常。
     * <p>
     * 可以注明是想要忽略的属性列表如@JsonIgnoreProperties({"name","age","title"})，
     * <p>
     * 也可以注明过滤掉未知的属性如@JsonIgnoreProperties(ignoreUnknown=true)
     */
    @Test
    public void testJsonIgnorePropertiesAnnotation() throws JsonProcessingException {
        //test1   普通的使用
        JsonClassAnnotationTestModel model = new JsonClassAnnotationTestModel();
        model.setId(1L);
        model.setName("test");
        model.setAge(111);
        model.setMemo("假装我是很长的备注");
        log.info(objectMapper.writeValueAsString(model));  //{"name":"test","age":111,"createTime":null,"updateTime":null}
        //todo 待补充代理 test 测试代理类

    }

    @Test
    public void testJsonIncludeAnnotation() throws IOException {
        JsonClassAnnotationTestModel model = new JsonClassAnnotationTestModel();
        model.setTestJsonInclude("testJsonInclude");
        String value = objectMapper.writeValueAsString(model);
        log.info(value);  //{"test_json_include":"testJsonInclude"}
        //==set null
        model.setTestJsonInclude(null);
        log.info(objectMapper.writeValueAsString(model)); //{}
    }

    /**
     * 统一的转换命名策略
     */
    @Test
    public void testJsonNamingAnnotation() throws JsonProcessingException {
        JsonClassAnnotationTestModel model = new JsonClassAnnotationTestModel();
        model.setCreateTime(new Date());
        log.info(objectMapper.writeValueAsString(model)); //{"create_time":1608465161519}
    }

    @Test
    public void testJsonUnwrappedAnnotation() throws JsonProcessingException {
        JacksonTestModel model = new JacksonTestModel();
        JsonAutoDetectTestModel autoDetectModel = new JsonAutoDetectTestModel();
        model.setJsonAutoDetectTestModel(autoDetectModel);
        log.info(objectMapper.writeValueAsString(model));//{"name":null,"age":null,"testTime":null,"testPrivate":null,"testDefault":null,"testProtected":null,"testPublic":null,"testAlias":null}
    }

    /**
     * //test  属性重名
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testJsonUnwrappedRepeatAttribute() throws JsonProcessingException {
        JacksonTestModel model = new JacksonTestModel();
        JsonClassAnnotationTestModel jsonCAModel = new JsonClassAnnotationTestModel();
        jsonCAModel.setName("我是子类的name");
        jsonCAModel.setCreateTime(new Date());
        model.setName("我是主类的name");
        model.setJsonClassAnnotationTestModel(jsonCAModel);
        // {"name":"我是主类的name","age":null,"testTime":null,"name":"我是子类的model","create_time":1608466122554,"testAlias":null}
        log.info(objectMapper.writeValueAsString(model));
    }

    /**
     * 2.0+版本新注解，作用于类或属性上，被用来在序列化/反序列化时为该对象或字段添加一个对象识别码，通常是用来解决循环嵌套的问题
     */
    @Test
    public void testJsonIdentityInfo() {

    }

    /**
     * 作用于类/接口，被用来开启多态类型处理，对基类/接口和子类/实现类都有效
     */
    @Test
    public void testJsonTypeInfo() {

    }

    /**
     * 作用于类/接口，用来列出给定类的子类，只有当子类类型无法被检测到时才会使用它，一般是配合@JsonTypeInfo在基类上使用
     */
    @Test
    public void testJsonSubTypes() {


    }

    /**
     * 作用于子类，用来为多态子类指定类型标识符的值
     */
    @Test
    public void testJsonTypeName() {


    }

    /**
     * 11、@JsonTypeResolver和@JsonTypeIdResoler
     * <p>
     * <p>
     * <p>
     * 作用于类，可以自定义多态的类型标识符，这个平时很少用到，主要是现有的一般就已经满足绝大多数的需求了，如果你需要比较特别的类型标识符，建议使用这2个注解，自己定制基于TypeResolverBuilder和TypeIdResolver的类即可
     */

    @Test
    public void testJsonTypeResolver() {


    }

    /**
     * 作用于方法和字段上，通过 using(JsonSerializer)和using(JsonDeserializer)来指定序列化和反序列化的实现，通常我们在需要自定义序列化和反序列化时会用到，比如下面的例子中的日期转换
     */
    @Test
    public void testJsonSerialize() throws JsonProcessingException {
        Date date = new Date();
        log.info(date.toString());
        JacksonTestModel model = new JacksonTestModel();
        model.setTestJsonSerialize(date);
        log.info(objectMapper.writeValueAsString(model)); //"testJsonSerialize":"2020-12-20 20:41:35"


    }

    @Test
    public void testJsonDeserialize() throws IOException {
        String json = "{\"name\":null,\"age\":null,\"testTime\":null,\"testJsonSerialize\":\"2020-12-20 20:41:35\",\"testAlias\":null}";
        JacksonTestModel model = objectMapper.readValue(json, JacksonTestModel.class);
        log.info(model.toString()); //JacksonTestModel
    }

    /**
     * 作用在类上，被用来指明当序列化时需要对属性做排序，它有2个属性
     * <p>
     * <p>
     * 一个是alphabetic：布尔类型，表示是否采用字母拼音顺序排序，默认是为false，即不排序
     */
    @Test
    public void testJsonPropertyOrder() {

    }

    /**
     * https://my.oschina.net/u/3637243/blog/1789932/
     * C参考这个  感觉过于复杂
     */
    @Test
    public void testJsonView() {

    }

    /**
     * Json属性过滤器，作用于类，作用同上面的@JsonView，都是过滤掉不想要的属性，
     * 输出自己想要的属性。和@FilterView不同的是@JsonFilter可以动态的过滤属性，比如我不想输出以system开头的所有属性
     */
    @Test
    public void testJsonFilter() {

    }

    @Test
    public void testJsonIgnoreType() {
    }

    @Test
    public void testJsonAnySetter() {
    }

    @Test
    public void testJsonCreator() {
    }

    @Test
    public void testJacksonInject() {
    }

    @Test
    public void testJsonPOJOBuilder() {
        String insertSql = "insert into demo_sc (sno,cno,score) values ";
        String values = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                values += "(" + i + ",100" + j + "," + getRandomScore() + "), \r\n";
            }
        }
        System.err.println(insertSql + values);
//        log.info(insertSql + values);
    }

    Random random = new Random();

    public int getRandomScore() {
        return random.nextInt(40) + 60;
    }


}