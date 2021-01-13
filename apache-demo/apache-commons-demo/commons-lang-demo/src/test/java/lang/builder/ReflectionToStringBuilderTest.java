package lang.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.junit.Test;

@Slf4j
public class ReflectionToStringBuilderTest {
    /**
     * 将bean  tostring
     * 一般应该会是用json to string了
     */
    @Test
    public void reflectionToString() {
        // 获取一个对象的所有属性与值
        BeanMode bean = new BeanMode();
        bean.setBeanBoolean(true);
        bean.setBeanByte("2".getBytes()[0]);
        bean.setBeanChar('3');
        bean.setBeanInt(4);
        bean.setBeanFloat(5F);
        bean.setBeanDouble(6D);
        bean.setBeanLong(7L);
        bean.setBeanShort((short) 55);
        bean.setStrs(new String[]{"张三", "李四", "w5"});
        bean.setBeanString("测试");
        log.info(ReflectionToStringBuilder.toString(bean));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BeanMode {
    private boolean beanBoolean;
    private byte beanByte;
    private char beanChar;
    private short beanShort;
    private int beanInt;
    private float beanFloat;
    private double beanDouble;
    private long beanLong;
    private String beanString;
    private BeanMode beanObj;
    private String[] strs;
}