package top.xmindguoguo.common.utils.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class FileUtil {
    /**
     * 获取一个必定存在的FileOutputStream
     * 
     * @Title getFileOutputStream
     * @author 于国帅
     * @date 2018年3月13日 上午10:33:21
     * @param filePath
     * @return FileOutputStream
     */
    public static FileOutputStream getFileOutputStream(String filePath) {
        createFile(new File(filePath));
        try {
            return new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            log.error("FileOutputStream 出错", e);
            return null;
        }
    }

    public static File createFile(String filePath, String fileName) {
        return createFile(new File(filePath, fileName));
    }

    public static File createFile(String filePath) {
        return createFile(new File(filePath));
    }

    /**
     * @Title createFile
     * @author 于国帅
     * @date 2018年3月13日 上午10:27:31
     * @param fileSaveDir
     * @param fileName
     * @return File
     */
    public static File createFile(File file) {
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("createFile 出错", e);
                return null;
            }
        }
        return file;
    }

    public static String getDefaultSavePath(String fileName) {
        return PathUtil.getSaveDatePath() + File.separator + getDateFileName(fileName);
    }

    /**
     * @Title getFileName
     * @author 于国帅
     * @date 2018年3月9日 上午9:23:55
     * @param fileItem
     * @return String
     */
    public static String getDateFileName(String fileName) {
        return System.currentTimeMillis() + "@" + fileName;
    }
}
