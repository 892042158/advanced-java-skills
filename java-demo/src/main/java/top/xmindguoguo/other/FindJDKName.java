package top.xmindguoguo.other;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 找出jdk下的所有的包 及类名称打印来
 * 
 * @方便自己 笔记标记是否学习了
 * 
 * 
 * @ClassName FindJDKName
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年4月25日 下午7:43:52
 *
 */
public class FindJDKName {
    private static final String DIR = "F:\\git码云\\jdk8_source_code_research\\jdk8_source_code_research\\src";
    private static final String TXT = "D:\\jdkName.txt";

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        // 1.查找这个目录下的所有的包
        File dirFile = new File(DIR);
        // 一个key 多个值 MultiValueMap
        MultiValueMap multiValueMap = new MultiValueMap();
        findName(dirFile, multiValueMap);
        File txtFile = new File(TXT);
        List<String> lines = new LinkedList<>();
        // 打印出来所有的类名称
        Set<String> keySet = multiValueMap.keySet();
        for (String key : keySet) {
            lines.add(getName(key));
            List<String> values = (List<String>) multiValueMap.get(key);
            for (String str : values) {
                lines.add("\t" + str);
            }
        }
        try {
            FileUtils.writeLines(txtFile, lines);
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
        System.err.println(1);
    }

    private static void findName(File dirFile, MultiValueMap multiValueMap) {
        List<File> dirList = new LinkedList<>();
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
//                    if (getName(pathname.getAbsolutePath()).contains("java"))
                    if (!getName(pathname.getAbsolutePath()).contains("java"))
                        dirList.add(pathname);
                    return true;
                }
                if (pathname.getName().endsWith(".java")) {
                    multiValueMap.put(pathname.getParentFile().getAbsolutePath(), pathname.getName());
                }
                return false;
            }
        };
        dirFile.listFiles(filter);
        if (!dirList.isEmpty()) {
            for (File file : dirList) {
                findName(file, multiValueMap);
            }
        }
    }

    public static String getName(String str) {
        str = StringUtils.remove(str, DIR + "\\");
        str = StringUtils.replace(str, "\\", ".");
        return str;
    }
}
