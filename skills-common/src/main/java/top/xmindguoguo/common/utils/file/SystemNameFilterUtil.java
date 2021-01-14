package top.xmindguoguo.common.utils.file;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤系统文件命名关键字
 *
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @ClassName SystemNameFilterUtil
 * @date 2018年6月19日 下午3:20:42
 */
public class SystemNameFilterUtil {
    private static List<String> windowsFilterList = new ArrayList<>();
    private static final int MAX_LENGTH = 255; // 文件名称的长度最长为255

    static {
        // windows 下不能生成如下关键字 "\ / : * ? " < > → |"
        windowsFilterList.add("\\");
        windowsFilterList.add("/");
        windowsFilterList.add(":");
        windowsFilterList.add("*");
        windowsFilterList.add("?");
        windowsFilterList.add("\"");
        windowsFilterList.add("<");
        windowsFilterList.add(">");
        windowsFilterList.add("→");
        windowsFilterList.add("|");
    }

    /**
     * 获取过滤windos 关键字的name
     *
     * @return String
     * @Title getFilterName
     * @author 于国帅
     * @date 2018年6月19日 下午3:24:32
     */
    public static String getWindowsFilterName(String fileName) {
        for (String key : windowsFilterList) {
            fileName = StringUtils.replace(fileName, key, ""); // 将这些关键字全部替换掉
        }
        fileName = ObjectUtils.defaultIfNull(fileName, "fileName").toString();
        if (fileName.length() > SystemNameFilterUtil.MAX_LENGTH) {
            return fileName.substring(0, SystemNameFilterUtil.MAX_LENGTH);
        }
        return fileName;
    }


}
