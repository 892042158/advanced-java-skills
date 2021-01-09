package lang.builder;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;

@Slf4j
public class ToStringBuilderTest {
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
        log.info(ToStringBuilder.reflectionToString(bean));
    }
}

