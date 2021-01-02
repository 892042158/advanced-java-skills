package jdk.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import org.junit.Test;

public class FileTest {
    String str1 = "G:\\AA";
    String str2 = "G:\\AA";
    String strPath = "G:\\a\\aa\\aaa.txt";

    @Test
    public void Demo7() {
        Random r = new Random();
        int n3 = r.nextInt(11);
        int n4 = Math.abs(r.nextInt() % 11);
        System.out.println("n3:" + n3);
        System.out.println("n4:" + n4);
    }

    @Test
    public void testTest() {
    }

    private void ss(String name, Object obj) {
        System.out.println(name + "===" + obj);
    }

    @Test
    public void testFileApi() {
        String strPath = "G:\\a\\aa\\aaa.txt";
        File file = new File(strPath);
        ss("getAbsoluteFile() ", file.getAbsoluteFile());
    }

    // 创建文件夹
    @Test
    public void createFolder() {
        File myFolderPath = new File(str1 + "createFolder");
        try {
            if (!myFolderPath.exists()) {
                myFolderPath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }

    @Test
    public void createFile() {
        // 2.创建文件
        // import java.io.*;
        File myFilePath = new File(str1 + "createFile");
        try {
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            myFile.println(str2);
            resultFile.close();
        } catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }
    }

    @Test
    public void createFileFolder() {
        // Java如何在不存在文件夹的目录下创建文件
        String strPath = "G:\\a\\aa\\aaa.txt";
        File file = new File(strPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Test
    public void createFileFolder2() {
        /*
         * 1. Java如何在不存在文件夹的目录下创建文件
         * 2. 有一层存在，且存在文件呢（但是下层的其他不存在）
         */
        String strPath = "G:\\a\\aa\\aaa.txt";
        File file = new File(strPath);
        System.out.println(file.getParent());
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * 1.删除文件 ，删除文件夹时的文件和文件夹怎么办   
     * 2.如何全部删除和删除指定文件
     * 
     */
    @Test
    public void deletFile() {
        // 3.删除文件
        // import java.io.*;
        File myDelFile = new File(strPath);
        try {
            myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }

    @Test
    public void deletFolder() {
        File delFolderPath = new File("G:\\a\\bb");
        try {
            delFolderPath.delete(); // 删除空文件夹
        } catch (Exception e) {
            System.out.println("删除文件夹操作出错");
            e.printStackTrace();
        }
    }

    @Test
    public void deletAllFolder() {
        File delfile = new File("G:\\a");
        File[] files = delfile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                files[i].delete();
            }
        }
    }

    @Test
    public void cleanFolder() {
        File delfilefolder = new File("G:\\a");
        try {
            if (delfilefolder.exists()) {
                delfilefolder.delete();
            }
            delfilefolder.mkdir();
        } catch (Exception e) {
            System.out.println("清空目录操作出错");
            e.printStackTrace();
        }
    }

    @Test
    public void cleanAllFolderFile() {
        File delfilefolder = new File("G:\\a");
        try {
            if (delfilefolder.exists()) {
                delfilefolder.delete();
            }
            delfilefolder.mkdir();
        } catch (Exception e) {
            System.out.println("清空目录操作出错");
            e.printStackTrace();
        }
    }
}
