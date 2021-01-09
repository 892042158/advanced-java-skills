package lang.time;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class DateFormatUtilsTest {
    @Test
    public void dateFormatUtilsDemo() {
        System.out.println("格式化日期输出.");
        System.out.println(DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));

        System.out.println("秒表.");
        StopWatch sw = new StopWatch();
        sw.start();

        for (Iterator iterator = DateUtils.iterator(new Date(), DateUtils.RANGE_WEEK_CENTER); iterator.hasNext();) {
            Calendar cal = (Calendar) iterator.next();
            System.out.println(DateFormatUtils.format(cal.getTime(), "yy-MM-dd HH:mm"));
        }

        sw.stop();
        System.out.println("秒表计时:" + sw.getTime());
    }
}
