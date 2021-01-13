package org.apache.commons.io;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtilsTest {
    private File srcFile;
    private File destFile;
    private String rootPath = "E:\\1A";
    private String srcPath = "E:\\1A\\src\\";
    private String destPath = "E:\\1A\\dest\\";
    private String dirPath = "E:\\1A\\dir\\";

    /**
     * 需求 1.判断是否存在文件
     */
    @Test
    public void testCopyFile() {
    }

    @Test
    public void testDeleteFile() throws IOException {
        // 先获取 G:\home\\ubuntu\\uploadFile\\yantai 下的所有文件
        String src = "G:\\home\\ubuntu\\uploadFile\\yantai";
        srcFile = new File(src);
        /*
         * 我现在想做清理目录下的文件名称如果不存在数据库，则进行删除 
         * 现在的问题是我现在怎么做
         * 1.查询出来数据库的所有url
         * 2.查询出来指定目录的所有文件 （这个文件是不是完整的全路径名字?） 是，
         * 但是过滤出来的路径需要查询 ，一层查询目录，一层查询文件
         * 
         * 3.如果是 则用过滤器进行删除
         * 4.如果不是则查询出来拼接好全路径的名字，然后list直接移除不存在的url，然后进行便利删除
         */
        FileFilter fileFiter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                System.out.println(file);
                return false;
            }
        };
        File[] files = srcFile.listFiles(fileFiter);
//        for (File file : files) {
//            System.out.println(file);
//
//        }
//        FileUtils.copyDirectory(srcFile, destFile, fileFiter); // 复制后会覆盖原先的数据
    }

    // 递归
    public void deleteFile(File file) {

    }

    @Test
    public void readFileInputStream() {
        // 读取一个文件路径-变成流
        String fileName = "F:\\2AsaveFile\\2018-03-09\\09时40分43秒1520559643896数据共享平台记录.xmind";
        fileName = StringUtils.substringAfterLast(fileName, File.separator);
        System.out.println(fileName);
        // File file = new File(fileName);
//        try {
//            FileUtils.openInputStream(file);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }

    /**
     * 拷贝一个文件夹的内容
     * 
     * @throws IOException
     */
    @Test
    public void copyFileContent() throws IOException {
        srcFile = new File(srcPath + "test.txt");
        isExistFolderAndFile(srcFile);
        destFile = new File(destPath + "test2.txt");
        isExistFolderAndFile(destFile);
        FileUtils.copyFile(srcFile, destFile);
    }

    /**
     * 拷贝一个文件夹下的所有文件夹和文件
     * 
     * @throws IOException
     */
    @Test
    public void copyDirectory() throws IOException {
        srcFile = new File(dirPath);
        isExistFolderAndFile(srcFile);
        destFile = new File(destPath);
        isExistFolderAndFile(destFile);
        FileUtils.copyDirectory(srcFile, destFile);
//        FileUtils.copyDirectory(srcFile, destFile, false);// 不保存文件修改的时间
    }

    /**
     * 拷贝一个文件夹下的所有文件夹和文件 并且过滤指定类型
     * 
     * @throws IOException
     */
    @Test
    public void copyDirectoryFiterType() throws IOException {
        srcFile = new File(dirPath);
        isExistFolderAndFile(srcFile);
        destFile = new File(destPath);
        isExistFolderAndFile(destFile);
        FileFilter fileFiter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory())
                    return true;
                else {
                    String name = file.getName();
                    if (name.endsWith(".zip") || name.endsWith(".txt"))
                        return true;
                    else
                        return false;
                }
            }
        };
        FileUtils.copyDirectory(srcFile, destFile, fileFiter); // 复制后会覆盖原先的数据
    }

    /**
     * 把服务器上文件下载到本地
     * 
     * @throws IOException
     */
    @Test
    public void copyURLToFile() throws IOException {
        String url = "http://imgsrc.baidu.com/baike/pic/item/7aec54e736d12f2ee289bffe4cc2d5628435689b.jpg";
//        String ipUrl = "192.168.1.233//";
        destFile = new File(destPath + "copyURLToFile");
        isExistFolderAndFile(destFile);
        FileUtils.copyURLToFile(new URL(url), destFile);
//        FileUtils.copyURLToFile(new URL(ipUrl), destFile);
    }

    /**
     * 把字符串写入文件
     * 
     * @throws IOException
     */
    @Test
    public void writeStringToFile() throws IOException {
        String content = "http://imgsrc.baidu.com/baike/pic/item/7aec54e736d12f2ee289bffe4cc2d5628435689b.jpg";
        destFile = new File(destPath + "writeStringToFile");
        isExistFolderAndFile(destFile);
        FileUtils.writeStringToFile(destFile, content, Charsets.UTF_8, true);
    }

    /**
     * 把文件读取成字符串
     * 
     * @throws IOException
     */
    @Test
    public void readFileToString() throws IOException {
        String content = "";
        destFile = new File(destPath + "writeStringToFile");
        isExistFolderAndFile(destFile);
        content = FileUtils.readFileToString(destFile, Charsets.UTF_8);
        System.out.println(content);
    }

    /**
     * 把byte[]写入文件
     * 
     * @throws IOException
     */
    @Test
    public void writeByteArrayToFile() throws IOException {
        String content = "http://imgsrc.baidu.com/baike/pic/item/7aec54e736d12f2ee289bffe4cc2d5628435689b.jpg";
        byte[] contentArray = content.getBytes();
        destFile = new File(destPath + "writeByteArrayToFile");
        isExistFolderAndFile(destFile);
        FileUtils.writeByteArrayToFile(destFile, contentArray, true);
    }

    /**
     * 把文件读取成 byte[]
     * 
     * @throws IOException
     */
    @Test
    public void readFileToByteArray() throws IOException {
        String content = "";
        byte[] contentArray = content.getBytes();
        destFile = new File(destPath + "writeByteArrayToFile");
        isExistFolderAndFile(destFile);
        contentArray = FileUtils.readFileToByteArray(destFile);
        System.out.println(ArrayUtils.toString(contentArray));
        System.out.println(Arrays.toString(contentArray));
        System.out.println(new String(contentArray, Charsets.UTF_8));
    }

    /**
     * 把集合写入文件
     * 
     * @throws IOException
     */
    @Test
    public void writeLines() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String mes = "哈哈，下班了";
        String mes2 = "回家，回家";
        list.add(mes); // 第一行数据
        list.add(mes2); // 第二行数据
        destFile = new File(destPath + "writeLines");
        isExistFolderAndFile(destFile);
        FileUtils.writeLines(destFile, list, true);
//        FileUtils.writeLines(destFile, list, "以。结尾", true);
    }

    /**
     * 把文件读取成集合
     * 
     * @throws IOException
     */
    @Test
    public void readLines() throws IOException {
        List<String> list = new ArrayList<>();
        destFile = new File(destPath + "writeLines");
        isExistFolderAndFile(destFile);
        list = FileUtils.readLines(destFile, Charsets.UTF_8);
        System.out.println(Arrays.toString(list.toArray()));
    }

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

    /**
     * 判断文件夹是否存在，不存在就创建
     * 
     * @param file
     * @return
     */
    public boolean isExistFolder(File file) {
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
            return false;
        }
        return true;
    }

    /**
     * 判断文件夹是否存在，不存在就创建
     * 
     * @param file
     * @return
     */
    public boolean isExistFolderAndFile(File file) {
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
            return false;
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return false;
        }
        return true;
    }

    @Test
    public void testDirORFile() throws IOException {
        String path = "E:\1A\file1\\";
        System.out.println(File.separator);
        System.out.println(path.length());
        System.out.println(path.lastIndexOf(File.separator));
        File file1 = new File("E:\\1A\\file1\\txt.s");
        System.out.println("getAbsolutePath=" + file1.getAbsolutePath());
        System.out.println("getCanonicalPath=" + file1.getCanonicalPath());
        System.out.println("file1=" + file1.isDirectory());
        System.out.println("file1=" + file1.isFile());
        File file2 = new File("E:\\1A\\file2\\");
        System.out.println("file2=" + "E:\\1A\\file2\\");
        System.out.println("file2=" + file2.isDirectory());
        System.out.println("file2=" + file2.isFile());
        File file3 = new File("E:\\1A\\file3.a");
        System.out.println("file3=" + file3.isDirectory());
        System.out.println("file3=" + file3.isFile());
    }
}
