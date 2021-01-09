package beanutils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import top.xmindguoguo.common.utils.model.ModelUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class BeanUtilsTest {
    @Test
    public void test() {
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
        bean.setBeanObj(bean);
        bean.setStrs(new String[] { "张三", "李四", "w5" });
        bean.setBeanString("测试");

        try {
            Map<String, Object> beanMap = BeanUtils.describe(bean);
            // 输出map
            for (Entry<String, Object> e : beanMap.entrySet()) {
                System.out.println(e.getKey() + "==" + e.getValue());
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void setProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        BeanMode bean = new BeanMode();
        BeanUtils.setProperty(bean, "beanInt", 200);
        System.out.println(BeanUtils.getProperty(bean, "beanInt"));
    }

    /**
     * 测试原生的 反射放入值 和工具类反射放入值
     *
     * 反射放入值 String 放入 Long
     *
     * @Title testJavaReflect
     * @author 于国帅
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @date 2018年12月11日 上午10:27:37 void
     */
    @Test
    public void testJavaReflect() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // 测试原生的方法
        // 先获取这个bean 的 String的属性 ，然后获取这个bean 的对应个值
        BeanMode modelBean = new BeanMode();

        Map<String, Field> fieldMap = ModelUtil.getFieldMap(modelBean.getClass());

        fieldMap.forEach((key, field) -> {
            if (field.getModifiers() == 2) {
                field.setAccessible(true);
                try {
                    Object value = field.get(modelBean);
                    boolean onlyShow = false;
//                    if (OnlyShowTag != null && OnlyShowTag.value()) {
//                        onlyShow = true;
//                    }
                    if (!onlyShow) {
                        Class<?> type = field.getType();
                        // 只添加基本类型
                        if (isSupportedType(type)) {
                            if (key.equalsIgnoreCase("beanString")) {
                                field.set(modelBean, 130L);
                            }
                            /*                            createBy createBy = field.getAnnotation(createBy.class);
                            createTime createTime = field.getAnnotation(createTime.class);
                            if ((createBy != null || key.toLowerCase().equals("createby")) && value == null) {
                                field.set(modelBean, SystemSessionInfo.getUserId());
                            } else if ((createTime != null || key.toLowerCase().equals("createtime")) && value == null) {
                                if (type.equals(Date.class)) {
                                    field.set(modelBean, new Date());
                                } else {
                                    field.set(modelBean, DateUtil.getDateStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
                                }
                            }
                                                    } else {
                                                    }*/
                        }
                    }
                } catch (Exception e1) {
                    System.err.println(e1);
                }
            }
        });
    }

    @Test
    public void testJavaReflect2() {
        // 测试工具类
        // 放入的不是同一类型的值时 会报错
        try {
            BeanMode modelBean = new BeanMode();
            BeanUtils.setProperty(modelBean, "beanString", 22222L);
//            System.out.println(BeanUtils.getProperty(modelBean, "beanString"));
        } catch (Exception e) {
            // Auto-generated method stub
            e.printStackTrace();
        }
    }

    private boolean isSupportedType(Class<?> fieldType) {
        return fieldType.isPrimitive() || Number.class.isAssignableFrom(fieldType) || fieldType.equals(String.class)
                || fieldType.equals(Date.class) || fieldType.equals(Timestamp.class) || fieldType.equals(byte[].class);
    }

    @Test
    public void copyProperties() throws IllegalAccessException, InvocationTargetException {
        BeanMode bean = new BeanMode();
        BeanMode bean2 = new BeanMode();
        bean.setBeanString("测试复制数据");
        BeanUtils.copyProperties(bean2, bean);
        System.err.println(bean2.getBeanString());
    }
}
/**
 * 把所有的属性测试全面 先测试常用的
 *
 * @@@8种基本类型 8种包装类型 3种引用类型 数组 类 和 接口 测试下 public
 * @ClassName BeanMode
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年7月27日 下午2:51:58
 *
 */
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
