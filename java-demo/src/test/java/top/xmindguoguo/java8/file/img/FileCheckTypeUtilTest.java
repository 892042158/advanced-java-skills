package top.xmindguoguo.java8.file.img;

import org.junit.Test;
import top.xmindguoguo.common.utils.file.PathUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/10 16:38
 * @Version: v1.0
 */
public class FileCheckTypeUtilTest {
    String filePath = PathUtil.getProjectPath() + File.separator + "file" + File.separator;

    @Test
    public void test() throws IOException {
        System.err.println(new SimpleImageInfo(filePath + "jpg.jpg"));
        System.err.println(new SimpleImageInfo(filePath + "png.png"));
        System.err.println(new SimpleImageInfo(filePath + "gif.gif"));
        System.err.println(new SimpleImageInfo(filePath + "webp.webp"));
        System.err.println(new SimpleImageInfo(filePath + "ico.ico"));
        System.err.println(new SimpleImageInfo(filePath + "bmp.bmp")); // null
        System.err.println(new SimpleImageInfo(filePath + "tga.tga")); // null
        System.err.println(new SimpleImageInfo(filePath + "tif.tif"));
    }

    @Test
    public void testGetType() throws Exception {
        System.err.println(FileCheckTypeUtil.getType(filePath + "jpg.jpg"));
        System.err.println(FileCheckTypeUtil.getType(filePath + "png.png"));
        System.err.println(FileCheckTypeUtil.getType(filePath + "gif.gif"));
        System.err.println(FileCheckTypeUtil.getType(filePath + "webp.webp"));
        System.err.println(FileCheckTypeUtil.getType(filePath + "ico.ico"));
        System.err.println(FileCheckTypeUtil.getType(filePath + "bmp.bmp")); // null
        System.err.println(FileCheckTypeUtil.getType(filePath + "tga.tga")); // null
        System.err.println(FileCheckTypeUtil.getType(filePath + "tif.tif"));

    }

    @Test
    public void test2() throws IOException {
        BufferedImage sourceImg = ImageIO.read(new FileInputStream(filePath + "tga.tga"));
        sourceImg.getWidth();
        sourceImg.getHeight();
    }
}