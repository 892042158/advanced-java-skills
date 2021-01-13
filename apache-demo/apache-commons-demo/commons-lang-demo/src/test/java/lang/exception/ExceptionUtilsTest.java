package lang.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;

@Slf4j
public class ExceptionUtilsTest {
    @Test
    public void getFullStackTrace() {
        try {
            int i = 1 / 0;
        } catch (Exception ex) {
            log.info(ExceptionUtils.getFullStackTrace(ex));
        }

    }
}
