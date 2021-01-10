package net.sourceforge.tess4j;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @ClassName ITesseractTest
 * @date 2019年5月11日 上午1:35:01
 * @see https://blog.csdn.net/qq_18730505/article/details/81705319
 * <p>
 * 需要下载一个文件
 */
@Slf4j
public class ITesseractTest {
    private static String imgs_path = "imgs"; // 数字

    private static final String zh_img = "/zh_code.png";// 中文
    private static final String en_img = "/encode.jpg"; // 英文图片
    private static final String number_img = "/number_code.jpg"; // 数字

    @Before
    public void setUp() throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(imgs_path);
        imgs_path = url.getFile();
    }

    /**
     * @throws TesseractException
     */
    @Test
    public void enOcr() throws TesseractException {

        File imageFile = new File(imgs_path+en_img);
        ITesseract instance = new Tesseract(); // JNA Interface Mapping
        String result = instance.doOCR(imageFile);
        System.out.println(result);
    }

    @Test
    public void numberOcr() throws TesseractException {
        File imageFile = new File(imgs_path+number_img);
        ITesseract instance = new Tesseract(); // JNA Interface Mapping
        String result = instance.doOCR(imageFile);
        System.out.println(result);
    }

    @Test
    public void zhOcr() throws TesseractException {
        File imageFile = new File(imgs_path+zh_img);
        ITesseract instance = new Tesseract(); // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setLanguage("chi_sim");
        String result = instance.doOCR(imageFile);
        System.out.println(result);
    }

}
