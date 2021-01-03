package top.xmindguoguo.common.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import top.xmindguoguo.common.utils.date.DateUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 获取路径相关
 *
 * @ClassName PathUtil
 * @date 2016年8月25日 下午3:32:26
 */
@Slf4j
public class PathUtil {
    private static final String defaultSavePath = PathUtil.getProjectDrive() + File.separator + PathUtil.getProjectName();

    /**
     * @return
     * @Title 获取一个项目所在盘符+项目的文件夹路径+时间的文件路径
     * @author 于国帅
     * @date 2018年3月9日 上午8:42:31
     */
    public static String getSaveDatePath() {
        return defaultSavePath + File.separator + DateUtil.getNowDate(); // 拼接好盘符
    }

    /**
     * @return
     * @Title 获取一个项目所在盘符+项目的文件夹路径
     * @author 于国帅
     * @date 2018年3月9日 上午8:42:31
     */
    public static String defaultSavePath() {
        return defaultSavePath; // 拼接好盘符
    }

    /**
     * 获取项目所在的盘符
     *
     * @return String
     * @Title getProject
     * @author 于国帅
     * @date 2018年3月8日 下午3:29:18
     */
    public static String getProjectDrive() {
        return StringUtils.substringBefore(getProjectPath(), File.separator);
    }

    public static String getProjectName() {
        return StringUtils.substringAfterLast(getProjectPath(), File.separator);
    }

    public static String getProjectPath() {
        return getSystemProperty("user.dir");
    }

    private static String getSystemProperty(String property) {
        try {
            return System.getProperty(property);
        } catch (SecurityException ex) {
            // we are not allowed to look at this property
            log.warn("Caught a SecurityException reading the system property '" + property
                    + "'; the SystemUtils property value will default to null.");
            return null;
        }
    }

    /**
     * 获取项目绝对路径
     *
     * @return String
     * @Title getProjectAbsolutePath
     */
    public static String getProjectAbsolutePath() {
        URL url = PathUtil.class.getResource("/");
        if (url == null) {
            log.warn("获取项目绝对路径出错，返回空");
            return "";
        }
        String path = url.getPath();
        try {
            path = URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("#获取项目绝对路径出错：转码失败！", e);
        }
        return path;
    }

    /**
     * 转换url地址为utf-8格式
     *
     * @Title encodeURL
     * @author 吕凯
     * @date 2013-7-29 上午11:37:44
     * @param path
     * @return String
     */
//    public static String encodeURL(String path) {
//        StringBuffer flag = new StringBuffer("");
//        if (path == null || path.isEmpty()) {
//            return flag.toString();
//        }
//        String vali = " +()";
//        char[] valiarr = vali.toCharArray();
//        try {
//            char[] ch = path.toCharArray();
//
//            outer: for (int i = 0; i < ch.length; i++) {
//                if (StringUtil.isSingleByte(ch[i])) {
//                    for (int j = 0; j < valiarr.length; j++) {
//                        if (ch[i] == valiarr[j]) {
//                            if (ch[i] == ' ') {
//                                flag.append("%20");
//                            } else {
//                                flag.append(URLEncoder.encode(String.valueOf(ch[i]), "UTF-8"));
//                            }
//                            continue outer;
//                        }
//                    }
//                    flag.append(ch[i]);
//                } else {
//                    flag.append(URLEncoder.encode(String.valueOf(ch[i]), "UTF-8"));
//                }
//            }
//
//        } catch (UnsupportedEncodingException e) {
//            log.error("不支持的字符集 UTF-8", e);
//        }
//        return flag.toString();
//    }

//    public static String base64EncodeURL(String urls) throws IOException {
//        URL url = new URL(encodeURL(urls));
//        BufferedInputStream bis = new BufferedInputStream(url.openStream());
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        int b = 0;
//        while ((b = bis.read()) != -1) {
//            baos.write(b);
//        }
//        BASE64Encoder encoder = new BASE64Encoder();
//        String outstr = encoder.encode(baos.toByteArray());
//        return outstr;
//    }

    /**
     *
     * 返回长宽数组，下标0为宽度下标1为高度
     *
     * @Title returnWidthAndHight
     * @author 吕凯
     * @date 2014-11-4 下午4:14:47
     * @param urls
     * @return
     * @throws IOException
     *             int[]
     */
//    public static int[] returnWidthAndHight(String urls) throws IOException {
//        int[] data = new int[2];
//        URL url = new URL(encodeURL(urls));
//        BufferedImage bi = ImageIO.read(url);
//        data[0] = bi.getWidth();
//        data[1] = bi.getHeight();
//        return data;
//    }

    /**
     * 根据是否启用二级域名返回url
     *
     * @Title getURIbyHost
     * @author 吕凯
     * @date 2014-11-25 上午11:50:45
     * @param id
     * @param urls
     * @return
     * @throws IOException
     *             String
     */
//    public static String getURIbyHost(String id, String urls) throws IOException {
//        return getURIbyHost("", "", "", id, urls);
//    }

    /**
     *
     * 根据是否启用二级域名返回url(用于跨项目)
     *
     * @Title getURIbyHost
     * @author 吕凯
     * @date 2017年2月17日 下午4:21:26
     * @param hostPath
     *            本地地址
     * @param id
     * @param urls
     * @return
     * @throws IOException
     *             String
     */
//    public static String getURIbyHost(String hostPath, String id, String urls) throws IOException {
//        return getURIbyHost(hostPath, "", "", id, urls);
//    }

    /**
     *
     * 根据是否启用二级域名返回url(用于跨项目)
     *
     * @Title getURIbyHostCrossDomain
     * @author 吕凯
     * @date 2017年2月17日 下午4:21:26
     * @param id
     * @param urls
     * @return
     * @throws IOException
     *             String
     */
//    public static String getURIbyHostCrossDomain(String id, String urls) throws IOException {
//        String webPath = ConfigModel.CONFIG.getProperty("webPath", "");
//        return getURIbyHost(webPath, "", "", id, urls);
//    }

    /**
     * 根据是否启用二级域名返回url
     *
     * @Title getURIbyHost
     * @author 吕凯
     * @date 2014-11-25 上午11:50:45
     * @param hostPath
     *            本地项目地址
     * @param prefix
     *            id的前缀
     * @param suffix
     *            d的后缀
     * @param id
     *            id
     * @param urls
     * @return
     * @throws IOException
     *             String
     */
//    public static String getURIbyHost(String hostPath, String prefix, String suffix, String id, String urls) throws IOException {
//        String userSLD = ConfigModel.CONFIG.getProperty("useSLD", "false");
//        String url = hostPath + urls + "/" + prefix + id + suffix;
//        if (urls == null || urls.length() == 0) {
//            url = hostPath + "/" + prefix + id + suffix;
//        }
//        if (userSLD.equals("true")) {
//            String domain = ConfigModel.CONFIG.getProperty("cookie.domain");
//            if (domain == null) {
//                domain = "11315.com";
//            }
//            url = "http://" + prefix + id + suffix + "." + domain;
//        }
//        return url;
//    }

    /**
     * 获取类的真实路径
     *
     * @param clazz
     * @return String
     * @Title getClassPackageDir
     * @author 于国帅
     * @date 2018年9月12日 下午5:40:30
     */
    public static String getClassPackageDir(Class<?> clazz) {
        if (clazz != null) {
            String classPackageName = clazz.getPackage().toString().replaceFirst("package ", "");
            return StringUtils.replace(classPackageName, ".", File.separator);
        }
        return null;
    }

    public static String getClassPackage(Class<?> clazz) {
        if (clazz != null) {
            return clazz.getPackage().toString().replaceFirst("package ", "");
        }
        return null;
    }

    public static String getPackageDir(String packageName) {
        if (packageName != null) {
            return StringUtils.replace(packageName, ".", File.separator);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getProjectAbsolutePath());
        System.out.println(getProjectDrive());
        System.out.println(getProjectPath());
        System.out.println(defaultSavePath());
        System.out.println(PathUtil.class.getName());
        System.out.println(PathUtil.class.getPackage());

        String classPackageName = PathUtil.class.getPackage().toString().replaceFirst("package ", "");
        System.out.println(classPackageName);
        // .转化为 \
        classPackageName = StringUtils.replace(classPackageName, ".", File.separator);
        System.out.println(classPackageName);

        System.exit(0);
    }
}
