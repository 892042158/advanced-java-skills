package org.apache.commons.io.filter;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileFilterTest {
    private File srcFile;
    private File destFile;
    private String rootPath = "E:\\1A";
    private String srcPath = "E:\\1A\\src\\";
    private String destPath = "E:\\1A\\dest\\";
    private String dirPath = "E:\\1A\\dir\\";

    /**
     * @Title 获取文件下的所有列表名称
     * @author 于国帅
     * @date 2018年3月3日 上午10:20:29
     * @throws IOException
     *             void
     */
    @Test
    public void getFileName() throws IOException {
        destFile = new File(destPath);
//         FileUtils.listFiles(directory, fileFilter, dirFilter)
        final List<String> fileNameList = new ArrayList<>();

        FileFilter fiter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return true;
                }
                if (pathname.getName().endsWith(".class")) {
                    fileNameList.add(pathname.getName());
                }
                return false;
            }
        };
        destFile.listFiles(fiter);
        for (String string : fileNameList) {
            System.out.println(string);
        }
    }
}
