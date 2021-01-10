package top.xmindguoguo.java8.file.img;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 校验文件类型
 *
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @ClassName FileCheckTypeUtil
 * @date 2019年4月6日 下午5:13:48
 * @see https://blog.csdn.net/KeepStrong/article/details/78416318
 * @see https://www.cnblogs.com/w3live/p/8391599.html
 */
@Slf4j
public class FileCheckTypeUtil {
    static Map<String, String> map = new HashMap<>();

    static {
        map.put("FFD8FF", "jpg");
        map.put("89504E", "png");
        map.put("474946", "gif");
        map.put("524946", "webp");
        map.put("000001", "ico");
        map.put("424D36", "bmp");
        map.put("00000A", "tga");
        map.put("49492A", "tif");
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static String checkType(String code) {
        return map.get(code);
    }

    public static String getType(String path) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        return getType(fileInputStream);
    }

    /**
     * 获得图片后缀
     *
     * @param path
     * @return
     */
    public static String getType(InputStream is) {
        String type = "";
        try {
            byte[] b = new byte[3];
            is.read(b, 0, b.length);
            String code = bytesToHexString(b);
            code = code.toUpperCase();
            type = checkType(code);

        } catch (Exception e) {
            log.error("获取文件格式异常", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("获取文件格式异常", e);
                }
            }
        }
        return type;
    }

}
