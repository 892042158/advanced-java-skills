package lang;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

public class ArrayUtilsTest {
    @Test
    public void testToString() {
        String[] array = { "100", "22", "帆帆", "测试" };
        System.out.println(ArrayUtils.toString(array).replaceAll(",", ""));// {100,22,帆帆,测试}
        System.out.println(ArrayUtils.toString(null, "qq"));
    }

    @Test
    public void testRemove() {
        String[] array = { "100", "22", "帆帆", "测试" };
        System.out.println(ArrayUtils.toString(ArrayUtils.remove(array, 1)));
        System.out.println(ArrayUtils.toString(ArrayUtils.removeElement(array, "100")));
    }
}
