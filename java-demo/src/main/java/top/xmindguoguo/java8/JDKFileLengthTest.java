package top.xmindguoguo.java8;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 计算一下jdk8源码的文件数量，估算自己的学习时间
 * 
 * @ClassName JDKFileLengthTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月26日 上午9:03:31
 *
 */
public class JDKFileLengthTest {
    static List<String> fileNameList = new ArrayList<>();

    public static void main(String[] args) {
        // 写一个方法 根据传递的目录来查找
//      String dir = "F:\\xmind2\\jdk8_source_code_research\\jdk8_source_code_research";  //7706个类文件
//        String dir = "F:\\xmind2\\jdk8_source_code_research\\jdk8_source_code_research\\src\\java";  //1495
//        String dir = "F:\\xmind2\\jdk8_source_code_research\\jdk8_source_code_research\\src\\java\\lang"; // 233
//        String dir = "F:\\xmind2\\jdk8_source_code_research\\jdk8_source_code_research\\src\\java\\util"; // 233
        String dir = "F:\\xmind2\\xmind-common"; // 233

        new JDKFileLengthTest().findFileNumber(dir);
        System.err.println(fileNameList.size());

    }

    private void findFileNumber(String dir) {
        File file = new File(dir);
        if (file.isDirectory()) {
            file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.isDirectory()) {
                        // awt //applet 排除掉
                        if (pathname.getAbsolutePath().contains("awt") || pathname.getAbsolutePath().contains("applet"))
                            return true;
                        findFileNumber(pathname.getAbsolutePath());
                        return true;
                    }
                    if (pathname.getName().endsWith(".xmind")) {
                        fileNameList.add(pathname.getName());
                    }
                    return false;
                }
            });
        }
    }
}
