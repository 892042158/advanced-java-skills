package top.xmindguoguo.common.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/14 23:51
 * @Version: v1.0
 */
@Slf4j
public class SystemNameFilterUtilTest {

    @Test
    public void getWindowsFilterName() {
        //ssdsa frefsa fa
        log.info(SystemNameFilterUtil.getWindowsFilterName("ssdsa fref\\*sa fa |||"));
    }
}