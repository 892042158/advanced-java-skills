package top.xmindguoguo.common.utils.number;

import java.math.BigDecimal;

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 * 
 * @ClassName Arith
 * @author <a href="mailto:donggongai@126.com" target="_blank">kevin Lv</a>
 * @date 2013-8-19 上午11:25:47
 * 
 */
public class ArithUtil {

    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 提供精确的加法运算。
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 除法运算保留两位小数
     * 
     * @Title div
     * @author 吕凯
     * @date 2013-8-19 下午3:43:05
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return Double
     */
    public static Double div(Number v1, Number v2) {
        return div(v1, v2, 2);
    }

    /**
     * 除法运算
     * 
     * @Title div
     * @author 吕凯
     * @date 2013-8-19 下午3:43:39
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            保留位数
     * @return Double
     */
    public static Double div(Number v1, Number v2, int scale) {
        if (Double.parseDouble(v2 + "") == 0) {
            return 0d;
        }
        if (v1 == null || v2 == null) {
            return null;
        }
        double result = div(Double.parseDouble(v1 + ""), Double.parseDouble(v2 + ""), scale);
        return result;
    }

    /**
     * 除法运算保留两位小数
     * 
     * @Title div
     * @author 吕凯
     * @date 2013-8-19 下午3:43:05
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return Double
     */
    public static Double div2(Double v1, Double v2) {
        return div(v1, v2, 2);
    }

    public static void main(String[] args) {
        // double[] a = { 0.84, 0.42, 0.14, 0.36, 0.36 };
        // double result = 0;
        // double result1 = 0;
        // for (int i = 0; i < a.length; i++) {
        // if (i == 0) {
        // result = a[0];
        // result1 = a[0];
        // } else {
        // result += a[i];
        // result1 = Arith.add(result1, a[i]);
        // }
        // }
        String dt = "500";

    }

    /**
     * 提供精确的乘法运算。
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * 
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s == null) {
            return null;
        }
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }

    // 这个类不能实例化
    private ArithUtil() {
    }
};
