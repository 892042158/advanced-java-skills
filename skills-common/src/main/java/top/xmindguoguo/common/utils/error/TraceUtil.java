package top.xmindguoguo.common.utils.error;

import org.apache.commons.lang.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 对捕捉到的所有异常进行处理，转义 暂时不处理大小写
 * 
 * @ClassName TraceUtils
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年4月11日 上午9:35:42
 *
 */
public class TraceUtil {
    // key 反馈的错误提示 value 错误信息
    private static Map<String, String> exceptionMap = null;
    private static final String MESSAGE = "文件未知的错误";
    static {
        if (exceptionMap == null) {
            exceptionMap = new HashMap<>();
        }
        initialize();
    }

    private static void initialize() {
        // key 反馈的错误提示 value 错误信息
        put("不能带有关键字", "You have an error in your SQL syntax;");
        put("长度超过限制（目前最大为1000）", "Data truncation: Data too long for column");
        put("主键id存在重复", "for key 'PRIMARY'"); // Duplicate entry '18' for key 'PRIMARY'
        put("数据长度超过限制（目前最大为1000）", "Row size too large"); // Row size too large. The maximum row size for the used table type, not counting
                                                          // BLOBs, is
        // 65535. This includes storage overhead, check the manual. You have to change some columns
        // to TEXT or BLOBs
        put("当前用户无删除权限", "DELETE command denied to user");
        // 字段长度不能太长 Identifier name '2016NIANXIABANNIANHUNANSHENGCHANPINZHILIANGJIANDUCHOUCHAHEGEQIYEGONGGAO' is too long
    }

    private static void put(String key, String value) {
        exceptionMap.put(key, StringUtils.deleteWhitespace(value));
    }

    /**
     * 根据异常获取异常对应的用户消息
     * 
     * @Title getMessage
     * @author 于国帅
     * @date 2018年4月11日 上午9:59:43
     * @param e
     * @return String
     */
    public static String getException(Exception e) {
        String trace = getStackTrace(e);
        return getException(trace);
    }

    /**
     * 
     * @Title getException
     * @author 于国帅
     * @date 2018年4月11日 下午12:03:56
     * @param trace
     * @return String
     */
    public static String getException(String trace) {
        for (Map.Entry<String, String> entry : exceptionMap.entrySet()) {
            if (trace.contains(entry.getValue())) {
                return entry.getKey();
            }
        }
        return MESSAGE;
    }

    /**
     * 异常转化为String
     * 
     * @Title getStackTrace
     * @author 于国帅
     * @date 2018年4月11日 上午10:02:08
     * @param e
     * @return String
     */
    public static String getStackTrace(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stace = sw.toString();
//        String stace = ArrayUtils.toString(e.getStackTrace()); // 转换异常为String
            stace = StringUtils.deleteWhitespace(stace); // 移除所有的空格
            stace = StringUtils.remove(stace, ","); // 移除转换带有的所有,
            return stace;
        } catch (Exception e1) {
            return MESSAGE;
        }
    }

    public static void main(String[] args) {
        String stace = "java.sql.SQLException: Data truncation: Data too long for column 'time' at row 1 Query: insert into 0129c1d0066848aa8dfdc05ab21bd301(titleRow,memo,time,state) values(?,?,?,?) Parameters: [[, 0, ";
        stace = StringUtils.deleteWhitespace(stace); // 移除所有的空格
        stace = StringUtils.remove(stace, ","); // 移除转换带有的所有,
        stace = getException(stace);
        System.out.println(stace);
    }
}
