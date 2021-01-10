package org.joda.time;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * joda time 学习
 * 
 * @ClassName DateTimeTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年12月28日 下午2:36:12
 *
 */
public class DateTimeTest {
    @Test
    public void test() {
        // jdk
        Calendar calendar = Calendar.getInstance();
        calendar.set(2012, Calendar.NOVEMBER, 15, 18, 23, 55);
        System.err.println(calendar.toString());
        // Joda-time
        DateTime dateTime = new DateTime(2012, 12, 15, 18, 23, 55);
        System.err.println(dateTime.toString("E MM/dd/yyyy HH:mm:ss.SSS"));
    }

    @Test
    public void test2() {
        // 以 JDK 的方式向某一个瞬间加上 90 天并输出结果
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss.SSS");
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        System.out.println(sdf.format(calendar.getTime()));
        // 以 Joda 的方式向某一个瞬间加上 90 天并输出结果
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.plusDays(90).toString("yyyy-mm-dd HH:mm:ss"));
    }

    /**
     * 两者之间的差距拉大了（Joda 用了两行代码，JDK 则是 5 行代码）。 现在假设我希望输出这样一个日期：距离 2000.1.1日 45 天之后的某天在下一个月的当前周的最后一天的日期。 坦白地说，我甚至不想使用 Calendar 处理这个问题。 使用 JDK
     * 实在太痛苦了，即使是简单的日期计算，比如上面这个计算。 正是多年前的这样一个时刻，我第一次领略到 JodaTime的强大。使用 Joda，用于计算的代码所示：
     */
    @Test
    public void test3() {
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
        System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek().withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));
    }

    /**
     * 上边既然是减 那么那样是减呢？ 正负数就可以
     * 
     */
    @Test
    public void test4() {
        DateTime dateTime2 = new DateTime(new Date());
        System.err.println(dateTime2.toString("yyyy-MM-dd HH:mm:ss"));
        System.err.println(dateTime2.toDate());

        DateTime dateTime = new DateTime(2018, 1, 1, 1, 1, 1, 1);
        System.err.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
        // 减去一天
        System.err.println(dateTime.plusDays(-1).toString("yyyy-MM-dd HH:mm:ss"));

        System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek().withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS"));

    }

    /**
     * 计算星期
     */
    @Test
    public void test5() {
        DateTime dateTime = new DateTime(2018, 1, 1, 1, 1, 1, 1);
        System.err.println(dateTime.plusWeeks(1).toString("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 星期天的计算
     */
    @Test
    public void test6() {
        DateTime dt = new DateTime();
        // 星期
        switch (dt.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                System.out.println("星期日");
                break;
            case DateTimeConstants.MONDAY:
                System.out.println("星期一");
                break;
            case DateTimeConstants.TUESDAY:
                System.out.println("星期二");
                break;
            case DateTimeConstants.WEDNESDAY:
                System.out.println("星期三");
                break;
            case DateTimeConstants.THURSDAY:
                System.out.println("星期四");
                break;
            case DateTimeConstants.FRIDAY:
                System.out.println("星期五");
                break;
            case DateTimeConstants.SATURDAY:
                System.out.println("星期六");
                break;
        }
    }

}
