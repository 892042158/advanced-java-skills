package top.xmindguoguo.common.utils.date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import top.xmindguoguo.common.utils.number.ArithUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * 日期操作相关
 * 
 * @ClassName DateUtil
 * @author <a href="mailto:donggongai@126.com" target="_blank">kevin</a>
 * @date 2016年8月25日 下午3:02:07
 *
 */
@Slf4j
public class DateUtil {
    /**
     * // 秒
     */
    public static final int SECOND = 1 * 1000;
    /**
     * // 分钟
     */
    public static final int MINUTE = 60 * 1000;
    /**
     * // 小时
     */
    public static final int HOUR = 60 * 60 * 1000;
    private static final SimpleDateFormat IE_GMT_SDF = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.UK);
    private static final SimpleDateFormat GMT_SDF = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z", Locale.UK);
    static {
        IE_GMT_SDF.setTimeZone(new SimpleTimeZone(0, "GMT"));
        GMT_SDF.setTimeZone(new SimpleTimeZone(0, "GMT"));
    }

    /**
     * 
     * 获取格林尼治时间
     * 
     * @Title getNowIeGMT
     * @param date
     *            日期
     * @return String
     */
    public static String getNowIeGMT(Date date) {
        synchronized (IE_GMT_SDF) {
            return IE_GMT_SDF.format(date);
        }
    }

    /**
     * 
     * 获取格林尼治
     * 
     * @Title gmt2Date
     * @param gmtDate
     * @return Date
     */
    public static Date gmt2Date(String gmtDate) {
        try {
            if (gmtDate.matches("\\d+.*")) {
                synchronized (GMT_SDF) {
                    return GMT_SDF.parse(gmtDate);
                }
            } else if (gmtDate.matches("\\w+.*")) {
                synchronized (IE_GMT_SDF) {
                    return IE_GMT_SDF.parse(gmtDate);
                }
            }
        } catch (ParseException e) {
            log.error("dateStr:'" + gmtDate + "'", e);
        }
        return null;
    }

    /**
     * 
     * 获取当前日期yyyy-MM-dd
     * 
     * @Title getNowDate
     * @return String
     */
    public static String getNowDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 
     * 根据参数获取日期yyyy-MM-dd
     * 
     * @Title getDateString
     * @param date
     *            日期
     * @return String
     */
    public static String getDateString(Date date) {
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return dateStr;
    }

    /**
     * 
     * 根据参数格式获取当前日期
     * 
     * @Title getNowDateTime
     * @param formatStr
     *            日期
     * @return String
     */
    public static String getNowDateTime(String formatStr) {
        return new SimpleDateFormat(formatStr).format(new Date());
    }

    /**
     * 
     * 获取当前时间yyyy-MM-dd HH:mm:ss
     * 
     * @Title getNowDateTime
     * @return String
     */
    public static String getNowDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 
     * 根据参数获取yyyy-MM-dd HH:mm:ss格式的时间
     * 
     * @Title getDateTimeString
     * @param date
     *            日期
     * @return String
     */
    public static String getDateTimeString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 
     * 获取某天某个格式的日期
     * 
     * @Title getDateStr
     * @param date
     *            日期
     * @param format
     *            格式化类型
     * @return String
     */
    public static String getDateStr(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 
     * 获取当前月份的第一天
     * 
     * @Title getMonthFirstDate
     * @return String
     */
    public static String getMonthFirstDate() {
        return new SimpleDateFormat("yyyy-MM-01 00:00:00").format(new Date());
    }

    /**
     * 
     * String类型转成Date，类型：yyyy-MM-dd
     * 
     * @Title str2date
     * @param dateStr
     * @return Date
     */
    public static Date str2Date(String dateStr) {
        return str2Date(dateStr, "yyyy-MM-dd");
    }

    /**
     * str转date（如果转换失败返回默认值）
     * 
     * @Title str2Date
     * @author 吕凯
     * @date 2016年10月11日 下午3:33:12
     * @param dateStr
     * @param format
     * @return Date
     */
    public static Date str2Date(String dateStr, String format) {
        return str2Date(dateStr, format, null);
    }

    /**
     * 
     * String 转成dateTime
     * 
     * @Title str2dateTime
     * @param dateStr
     *            日期
     * @return Date
     */
    public static Date str2dateTime(String dateStr) {
        return str2dateTime(dateStr, null);
    }

    /**
     * 
     * String类型转成yyyy-MM-dd HH:mm:ss
     * 
     * @Title str2dateTime
     * @param dateStr
     *            日期
     * @param defaultDate
     *            默认日期
     * @return Date
     */
    public static Date str2dateTime(String dateStr, Date defaultDate) {
        return str2Date(dateStr, "yyyy-MM-dd HH:mm:ss", defaultDate);
    }

    /**
     * 
     * str转date（如果转换失败返回默认值）
     * 
     * @Title str2dateTime
     * @author 吕凯
     * @date 2016年10月11日 下午3:32:23
     * @param dateStr
     * @param format
     * @param defaultDate
     * @return Date
     */
    public static Date str2Date(String dateStr, String format, Date defaultDate) {
        if (StringUtils.isNotBlank(dateStr)) {
            try {
                return new SimpleDateFormat(format).parse(dateStr);
            } catch (Exception e) {
                log.warn("日期转换错误，dateStr‘" + dateStr + "’，" + e.getMessage());
            }
        }
        return defaultDate;
    }

    public static Date getLongStringToDate(String dateStr) throws ParseException {
        Date retDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        return retDate;
    }

    /**
     * 
     * 是否是当前时间的下一天
     * 
     * @Title isNextDate
     * @param dateStr
     *            日期
     * @return
     * @throws ParseException
     *             boolean
     */
    public static boolean isNextDate(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.trim().equals("")) {
            return false;
        }
        Date retDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        return retDate.getTime() > new Date().getTime();
    }

    /**
     * 
     * 返回增加某天后的时间
     * 
     * @Title addDate
     * @param date
     * @param addNum
     *            Calendar.DAY_OF_MONTH 从这个静态类里面调取
     * @param addUnit
     *            加多少时间
     * @return Date
     */
    public static Date addDate(Date date, int addNum, int addUnit) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(addUnit, addNum);
        return c.getTime();
    }

    public static Timestamp getLongStringToTimestamp(String dateStr) {
        Timestamp time = Timestamp.valueOf(dateStr);
        return time;
    }

    /**
     * 
     * 判断2个日期月份是否相同
     * 
     * @Title isSameMonth
     * @param early
     *            前一个月份
     * @param late
     *            后一个月份
     * @return boolean
     */
    public static final boolean isSameMonth(Date early, Date late) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        return calst.get(Calendar.MONTH) == caled.get(Calendar.MONTH);
    }

    /**
     * 
     * 根据id获取后11位文字显示，string：XXX-XXXX-XXXX，如1231142225333=》311-4222-5333
     * 
     * @Title getDh
     * @param id
     *            时间戳
     * @return String
     */
    public static String getDh(long id) {
        String dh = "";
        String dh1 = id + "";
        if (dh1.length() == 13) {
            dh = dh1.substring(2, 5) + "-" + dh1.substring(5, 9) + "-" + dh1.substring(9, 13);
        } else {
            dh = dh1;
        }
        return dh;
    }

    /**
     * 
     * 得到两个日期相差的天数
     * 
     * @Title daysBetween
     * @param early
     *            前一个天数
     * @param late
     *            后一个天数
     * @return int
     */
    public static final int daysBetween(Date early, Date late) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        diffDates(calst, caled, early, late);
        // 得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    /**
     * 
     * 得到两个日期相差的小时数
     * 
     * @Title hoursBetween
     * @param early
     * @param late
     * @return int
     */
    public static final int hoursBetween(Date early, Date late) {
        return minutesBetween(early, late) / 60;
    }

    /**
     * 
     * 得到两个日期相差的分钟数
     * 
     * @Title minutesBetween
     * @param early
     * @param late
     * @return int
     */
    public static final int minutesBetween(Date early, Date late) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        int minutes = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 60;
        return minutes;
    }

    /**
     * 
     * 得到两个日期相差的毫秒数
     * 
     * @Title timeBetween
     * @param early
     * @param late
     * @return long
     */
    public static final long timeBetween(Date early, Date late) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        return caled.getTime().getTime() - calst.getTime().getTime();
    }

    private static void diffDates(Calendar calst, Calendar caled, Date early, Date late) {
        calst.setTime(early);
        caled.setTime(late);
        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
    }

    /**
     * 将毫秒数转换为时间表示1000ms==》1s
     * 
     * @Title formatMill
     * @author 吕凯
     * @date 2013-12-17 下午2:29:03
     * @param time
     * @return String
     */
    public static String millFormat(double time) {
        return millFormat(time, 0);
    }

    /**
     * 将毫秒数转换为时间表示1000ms==》1s
     * 
     * @Title formatMill
     * @author 吕凯
     * @date 2013-12-17 下午2:29:03
     * @param time
     * @return String
     */
    public static String millFormat(double time, int scale) {
        String[] sizearr = { "毫秒", "秒", "分钟", "小时", "天" };
        int[] steps = { 1000, 60, 60, 24, 30 };
        time = ArithUtil.div(time, 1, scale);
        String result = "";
        double sized = (double) time;
        for (int i = 0; i < steps.length; i++) {
            double rem = sized % steps[i];
            double value = sized / steps[i];
            if (rem != 0 || time == 0) {
                result = (int) rem + sizearr[i] + result;
                if (value > 1) {
                    sized /= steps[i];
                } else {
                    break;
                }
            } else {
                if (value >= steps[i + 1]) {
                    sized /= steps[i];
                } else {
                    result = (int) (value) + sizearr[i + 1] + result;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println((getNowRandomTime()));
    }

    /**
     * 
     * 获取当前是第几周
     * 
     * @Title weekOfDate
     * @param d1
     * @return int
     */
    public static int weekOfDate(Date d1) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d1);
        return gc.get(Calendar.DAY_OF_WEEK);
    }

    /**
     *
     * 获取当前天数
     *
     * @Title getDayInYear
     * @param d1
     * @return int
     */
    public static int getDayInYear(Date d1) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d1);
        return gc.get(Calendar.DAY_OF_YEAR);
    }

    /**
     *
     * 获取年
     *
     * @Title getYear
     * @param date
     * @return int
     */
    public static int getYear(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return gc.get(Calendar.YEAR);
    }

    /**
     *
     * 获取当前月份
     *
     * @Title getMonthInYear
     * @param date
     * @return int
     */
    public static int getMonthInYear(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return gc.get(Calendar.MONTH);
    }

    /**
     * @Description:获取当前随机的一个时间点
     * @return
     * @author: 玄承勇
     * @date: 2017年2月10日 上午9:17:11
     */
    public static Date getNowRandomTime() {
        String dateStr = getNowDate();
        Random random = new Random();
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        return str2dateTime(dateStr + " " + hour + ":" + minute + ":" + second);

    }

    /**
     * System.currentTimeMillis();根据一定的规则展示合适的时间 / 1秒内 用 毫秒 1分钟内用秒 1小时内用分钟 2小时外 用小时 System.currentTimeMillis();
     * 
     * @Title getTime
     * @author 于国帅
     * @date 2018年7月11日 下午4:46:12
     * @param start
     * @param end
     * @return String
     */
    public static String getTime(long start, long end) {
        end = end - start;
        if (end < SECOND) {
            return end + "毫秒";
        } else if (end < MINUTE) {
            return end / SECOND + "秒";
        } else if (end < HOUR) { // * 2
            return end / MINUTE + "分" + (end % MINUTE) / SECOND + "秒";
        } else {
            return end / HOUR + "小时" + (end % HOUR) / MINUTE + "分";
        }
    }

    public static String getTime(long start) {
        long end = System.currentTimeMillis();
        return getTime(start, end);
    }
}
