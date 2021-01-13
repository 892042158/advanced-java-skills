package beanutils;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

public class PropertyUtilsTest {
    @Test
    public void test() {
        // 获取一个对象的所有属性与值
        BeanMode bean = new BeanMode();
        BeanMode bean2 = new BeanMode();
        bean2.setBeanBoolean(true);
        bean2.setBeanByte("2".getBytes()[0]);
        bean.setBeanBoolean(true);
        bean.setBeanByte("2".getBytes()[0]);
        bean.setBeanChar('3');
        bean.setBeanInt(4);
        bean.setBeanFloat(5F);
        bean.setBeanDouble(6D);
        bean.setBeanLong(7L);
        bean.setBeanShort((short) 55);
        bean.setBeanObj(bean2);
        bean.setStrs(new String[] { "张三", "李四", "w5" });
        bean.setBeanString("测试");

        try {
            Map<String, Object> beanMap = PropertyUtils.describe(bean);
            // 输出map
            for (Entry<String, Object> e : beanMap.entrySet()) {
                System.out.println(e.getKey() + "==" + e.getValue());
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }

    }
}
