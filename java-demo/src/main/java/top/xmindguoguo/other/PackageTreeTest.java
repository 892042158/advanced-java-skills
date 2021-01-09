package top.xmindguoguo.other;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 把JDK8中包列表及介绍列表.md 中的数据格式化成树显示
 * 
 * @ClassName PackageTreeTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月18日 上午10:30:52
 *
 */
public class PackageTreeTest {
    public static void main(String[] args) throws IOException {
//            首先读取数据，存储在map中 key为包名称    value为 介绍
//            因为包顺序一致，所以就不需要转变成tree再输出只需要保持顺序存入map，然后根据判断输出对应的Tree  级别就可以了
//            默认 两级包 为顶级树  ，其他的子 对应输出
        File dirFile = SystemUtils.getUserDir();
        File jdk8File = new File(dirFile.getPath(), "JDK8中包列表及介绍列表.md");
        List<String> listLines = FileUtils.readLines(jdk8File);
        Map<String, Object> jdk8Map = new LinkedHashMap<>();
        String key = "tempKey";
        String lineT = "";
        for (String line : listLines) {
            lineT = line.trim();
            if (StringUtils.isNotBlank(lineT)) {
                if (isAllLetter(lineT.split("\\."))) {
                    key = lineT;
                    // 预放入，防止下面也是字母key 导致没有存取
                    jdk8Map.put(key, "");
                } else {
                    jdk8Map.put(key, lineT);
                }
            }
        }
//        System.err.println(isAllLetter("org.apache.commons".split("\\.")));
//        System.err.println(isAllLetter("提供了需要创建一个小程序和用来跟其他小程序交流上下文的类。".split("\\.")));
        // 输出数据转变成树

        // 剩下的额外增加"#"
        for (Entry<String, Object> e : jdk8Map.entrySet()) {
            String mdKey = e.getKey();
            String mdValue = String.valueOf(e.getValue());
            String content = getContent(mdKey, mdValue);
            System.err.println(content);
        }

    }

    static int i = 0;

    // 获取转变成的树数据
    private static String getContent(String mdKey, String mdValue) {
        String content = ""; // ```
        String h3 = "#####"; // 顶级主题增加 md 的放大
        int len = mdKey.split("\\.").length;
        if (len == 2) { // 3就是6个# 默认2是5个# 所以直接返回
            if (i != 0) {
                content = "```" + "\n";
            }
            mdKey = content + h3 + " " + (++i) + "." + mdKey + "     " + mdValue;
            content = content + mdKey + "\n" + "```";
        } else if (len > 2) {
            mdKey = join("----", len - 2) + mdKey + "       " + mdValue;
        }
        return mdKey;
    }

    public static String join(String str, int len) {
        String[] s = new String[len];
        Arrays.fill(s, str);
        return StringUtils.join(s);
    }

    /**
     * 判断字符串是否为纯字母
     * 
     * @see https://blog.csdn.net/zhouhaisunny/article/details/80702733
     * @Title isAllLetter
     * @author 于国帅
     * @date 2019年1月18日 上午10:45:36
     * @param str
     * @return boolean
     */
    public static boolean isAllLetter(String str) {
        if (Objects.nonNull(str)) {
            return str.matches("[a-zA-Z]+");
        }
        return false;
    }

    /**
     * 判断字符串是否为纯字母
     * 
     * @see https://blog.csdn.net/zhouhaisunny/article/details/80702733
     * @Title isAllLetter
     * @author 于国帅
     * @date 2019年1月18日 上午10:45:36
     * @param str
     * @return boolean
     */
    public static boolean isAllLetter(String[] strs) {
        for (String str : strs) {
            if (!isAllLetter(str)) { // 如果存在false 则返回
                return false;
            }
        }
        return true;
    }
}
