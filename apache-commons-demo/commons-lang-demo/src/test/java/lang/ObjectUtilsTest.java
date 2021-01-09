package lang;

import org.apache.commons.lang.ObjectUtils;
import org.junit.Test;

public class ObjectUtilsTest {
    @Test
    public void ObjectUtils() {
        Object str = ObjectUtils.defaultIfNull(null, "1");
        System.out.println(str);
        str = ObjectUtils.defaultIfNull(new String("2222"), "1");
        System.out.println(str);
    }

    @Test
    public void equals() {
        Object str = ObjectUtils.equals(null, "1");
        System.out.println(str);
    }
}
