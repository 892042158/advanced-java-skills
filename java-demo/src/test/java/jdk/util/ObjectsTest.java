package jdk.util;

import lombok.Data;
import org.junit.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @ClassName ObjectsTest2
 * @date 2019年1月15日 下午9:52:38
 * @see https://docs.oracle.com/javase/9/docs/api/java/util/Objects.html
 */
public class ObjectsTest {

    @Test
    public void testCompare11s​() {
        BeanMode bean = new BeanMode();
        bean.setBeanLong(13L);
        BeanMode bean2 = new BeanMode();
        bean2.setBeanLong(13L);
        System.err.println(bean2.equals(bean2));
        int flag = Objects.compare(bean2, bean, new Comparator<BeanMode>() {
            // 实际运用中，可能比较两个model的id是否相等 或者model的某个值，
            @Override
            public int compare(BeanMode o1, BeanMode o2) {
                if (o1 == o2) {
                    return 0;
                }
                if (o1 != null && o1.equals(o2)) {
                    return 0;
                }
                if (o2 != null && o1.getBeanLong() == o2.getBeanLong()) { // 只判断缓存值
                    return 0;
                }
                return -1;
            }
        });
        assertEquals(flag, 0L);
    }

    // 深度比较java 基本类型
    @Test
    public void deepEquals() {
        // 参考 Arrays.deepEquals0();
    }

    @Test
    public void equals() {
        // 比较基本类型
        String str = "";
        String str2 = "";
        System.err.println(Objects.equals(str, str2));
        // 比较对象 肯定不相等，相等需要重写对象的equals
        BeanMode beanModel = new BeanMode();
        BeanMode beanModel2 = new BeanMode();
        System.err.println(Objects.equals(beanModel, beanModel2));
    }

    @Test
    public void testHash​() {
        BeanMode beanModel = new BeanMode();
        // 为一系列输入值生成哈希码。生成哈希代码，好像所有输入值都放在一个数组中，并通过调用对该数组进行哈希处理Arrays.hashCode(Object[])
        System.err.println(Objects.hash(beanModel));
    }

    @Test
    public void testHashCode() {
        // 返回非null参数的哈希码，参数返回0 null
        BeanMode beanModel = new BeanMode();
        System.err.println(Objects.hashCode(beanModel));
        System.err.println(Objects.hashCode(2L));
        System.err.println(Objects.hashCode("2"));

    }

    @Test
    public void isNull() {
        String nullStr = null;
        boolean flag = Objects.isNull(nullStr);
        assertTrue(flag);
    }

    @Test
    public void nonNull​() {
        // 与isNull 相反 判断不等于null
        boolean flag = Objects.nonNull(null);
        assertFalse(flag);
    }

    @Test
    public void requireNonNull() {
        // 判断传入的参数如果是null 则抛出异常
        Objects.requireNonNull(null);
        Objects.requireNonNull(null, "测试信息");
    }

    @Test
    public void testToString2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(22);
    }

    @Test
    public void testToString() {
        System.err.println(Objects.toString(Integer.valueOf(1)));
        System.err.println(Objects.toString(null, "22"));
    }

}

/**
 * 把所有的属性测试全面 先测试常用的
 *
 * @ @ @8种基本类型 8种包装类型 3种引用类型 数组 类 和 接口 测试下 public
 * @ClassName BeanMode
 * @author<a href="892042158@qq.com"target="_blank">于国帅</a>
 * @date 2018年7月27日 下午2:51:58
 */

@Data
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
    private TestI i;
    private String[] strs;

    public BeanMode() {

    }

    public BeanMode(boolean beanBoolean, byte beanByte, char beanChar, short beanShort, int beanInt, float beanFloat, double beanDouble,
                    long beanLong, String beanString, BeanMode beanObj, TestI i) {
        super();
        this.beanBoolean = beanBoolean;
        this.beanByte = beanByte;
        this.beanChar = beanChar;
        this.beanShort = beanShort;
        this.beanInt = beanInt;
        this.beanFloat = beanFloat;
        this.beanDouble = beanDouble;
        this.beanLong = beanLong;
        this.beanString = beanString;
        this.beanObj = beanObj;
        this.i = i;
    }

}

interface TestI {

}
