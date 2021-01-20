package jdk.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/18 0:38
 * @Version: v1.0
 */
@Slf4j
public class DateTest {
    /**
     * @see https://blog.csdn.net/qq_34068082/article/details/80983707
     */
    @Test
    public  void after(){
        Date today = new Date();
        Date tomorrow = DateUtils.addDays(today,1);
        Assert.assertTrue(tomorrow.after(today));
    }
    @Test
    public  void before(){
        Date today = new Date();
        Date yesterday = DateUtils.addDays(today,-1);
        Assert.assertTrue(yesterday.before(today));
    }
}
