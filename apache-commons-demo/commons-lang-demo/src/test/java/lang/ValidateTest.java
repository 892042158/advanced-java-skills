package lang;

import org.apache.commons.lang.Validate;
import org.junit.Test;

public class ValidateTest {

    @Test
    public void validateDemo() {
        String[] strarray = { "a", "b", "c" };
        System.out.println("验证功能");
        Validate.notEmpty(strarray);
    }
}
