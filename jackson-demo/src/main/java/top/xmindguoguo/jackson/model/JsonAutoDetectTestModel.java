package top.xmindguoguo.jackson.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @ClassName: JsonAutoDetectTestModel
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/20 18:21
 * @Version: v1.0
 */
//自动检测，（作用在类上）来开启/禁止自动检测。   //fieldVisibility =*自动检测成员字段所需的最低可见性。
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class JsonAutoDetectTestModel {
    /**
     * 1. private 表示私有，只有自己类能访问
     * <p>
     * 2. default表示没有修饰符修饰，只有同一个包的类能访问
     * <p>
     * 3. protected表示可以被同一个包的类以及其他包中的子类访问
     * <p>
     * 4. public表示可以被该项目的所有包中的所有类访问
     */
    private String testPrivate;
    String testDefault;
    protected String testProtected;
    public String testPublic;
}
