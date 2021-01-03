package top.xmindguoguo.common.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * https://www.cnblogs.com/jyiqing/p/6858224.html
 * 
 * @ClassName DateFormatConstant
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年12月28日 下午2:52:25
 */
public interface DateFormatConstant {
    // 开局设定几个通用 常用的
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";

    // SimpleDateFormat 使用标准教程教学
    public interface Format {

        /**
         * 星期六 12/15/2012 18:23:55.000
         */
        public static final String E = "E MM/dd/yyyy HH:mm:ss.SSS";
        /**
         * 其中的分隔符"-"可以替换成其他非字母的任意字符(也可以是汉字),例如:
         */
        public static final String SEPARATOR = "-";

        /**
         * 年 不区分大小写 "y" , "yyy" , "yyyy" 匹配的都是4位完整的年 如 : "2017"
         * 
         * "yy" 匹配的是年分的后两位 如 : "15"
         * 
         * 超过4位,会在年份前面加"0"补位 如 "YYYYY"对应"02017"
         */
        public static final String YEAR = "yyyy";
        /**
         * 代表月(只能使用大写) 假设月份为 9
         * 
         * "M" 对应 "9"
         * 
         * "MM" 对应 "09"
         * 
         * "MMM" 对应 "Sep"
         * 
         * "MMMM" 对应 "Sep"
         * 
         * 超出3位,仍然对应 "September"
         */
        public static final String MONTH = "MM";
        /**
         * dd : 代表日(只能使用小写) 假设为13号
         * 
         * "d" , "dd" 都对应 "13"
         * 
         * 超出2位,会在数字前面加"0"补位. 例如 "dddd" 对应 "0013"
         */
        public static final String DAY = "dd";
        /**
         * hh : 代表时(区分大小写,大写为24进制计时,小写为12进制计时) 假设为15时
         * 
         * "H" , "HH" 都对应 "15" , 超出2位,会在数字前面加"0"补位. 例如 "HHHH" 对应 "0015"
         * 
         * "h" 对应 "3"
         * 
         * "hh" 对应 "03" , 超出2位,会在数字前面加"0"补位. 例如 "hhhh" 对应 "0003"
         */
        public static final String HOUR = "HH";
        /**
         * mm : 代表分(只能使用小写) 假设为32分
         * 
         * "m" , "mm" 都对应 "32" , 超出2位,会在数字前面加"0"补位. 例如 "mmmm" 对应 "0032"
         */
        public static final String MINUTE = "mm";
        /**
         * ss : 代表秒(只能使用小写) 假设为15秒
         * 
         * "s" , "ss" 都对应 "15" , 超出2位,会在数字前面加"0"补位. 例如 "ssss" 对应 "0015"
         */
        public static final String SECOND = "ss";
        public static final String MILLISECOND = ".SSS";
        /**
         * 星期
         */
        public static final String WEEK = "E";
        /**
         * a : 代表上午还是下午,如果是上午就对应 "AM" , 如果是下午就对应 "PM"
         */
        public static final String A = "a";
    }

    public static void main(String args[]) {
        Date newTime = new Date();
        // 设置时间格式
        SimpleDateFormat sdf1 = new SimpleDateFormat("y-M-d h:m:s a E");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yy-MM-dd hh:mm:ss a E");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MMM-ddd hhh:mmm:sss a E");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyyy-MMMM-dddd hhhh:mmmm:ssss a E");

        // 获取的时间，是本机的时间
        String formatDate1 = sdf1.format(newTime);
        String formatDate2 = sdf2.format(newTime);
        String formatDate3 = sdf3.format(newTime);
        String formatDate4 = sdf4.format(newTime);

        System.out.println(formatDate1);
        System.out.println(formatDate2);
        System.out.println(formatDate3);
        System.out.println(formatDate4);
    }
}
