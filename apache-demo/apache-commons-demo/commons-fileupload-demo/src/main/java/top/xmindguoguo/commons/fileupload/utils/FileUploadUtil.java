package top.xmindguoguo.commons.fileupload.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Encoder;
import top.xmindguoguo.common.utils.file.PathUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FileUploadUtil {
    public static final int MB = 1024 * 1024;
    public static final FileUploadUtil fileUpload = new FileUploadUtil();
    private static DiskFileItemFactory fileItemFactory = null;
    private static ServletFileUpload servletFileUpload = null;
    static {
        fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setSizeThreshold(2 * MB); // 2MB
        // * 临时文件扩展名 *.tmp ，临时文件可以任意删除。
        //todo 实际开发中 这些位置都应该是通过配置文件或者配置中心去处理的
        String tempDir = PathUtil.getProjectAbsolutePath() + "temp";
        fileItemFactory.setRepository(new File(tempDir));
        servletFileUpload = new ServletFileUpload(fileItemFactory);
        servletFileUpload.setFileSizeMax(10 * MB); // 单个文件大小
        servletFileUpload.setSizeMax(100 * MB); // 2.3 整个上传文件总大小
        servletFileUpload.setHeaderEncoding(Charsets.UTF_8.toString()); // * 以上都没有设置，将使用平台默认编码
    }

    private FileUploadUtil() {

    }

    public static FileUploadUtil getFileUploadUtil() {
        return fileUpload;
    }
//
//    public static DiskFileItemFactory getDiskFileItemFactory() {
//        return fileItemFactory;
//    }
//
//    public static ServletFileUpload getServletFileUpload() {
//        return servletFileUpload;
//    }

    public static List<FileItem> getFileItemList(HttpServletRequest request) throws FileUploadException {
        return servletFileUpload.parseRequest(request);
    }

    /**
     * 存储上传的文件，并返回普通表单的数据
     * 
     * @Title getFormFieldList
     * @author 于国帅
     * @date 2018年3月8日 下午4:37:18
     * @param request
     * @return
     * @throws FileUploadException
     *             List<FileItem>
     */
//    public static List<FileItem> getFormFieldList(List<FileItem> fileItemList) throws FileUploadException {
//
//        return null;
//    }
    /**
     * 执行文件存储并且返回存储的文件信息 key="表单name" value="存储路径"
     * 
     * @Title getFilePathMap
     * @author 于国帅
     * @date 2018年3月9日 上午8:42:31
     * @param fileItemList
     * @return
     * @throws Exception
     */
    public static Map<String, String> getFilePathMap(List<FileItem> fileItemList) throws Exception {
        return getFilePathMap(fileItemList, null);
    }

    /**
     * @Title 获取一个项目所在盘符+项目的文件夹路径
     * @author 于国帅
     * @date 2018年3月9日 上午8:42:31
     * @return
     */
    private static String defaltSavePath() {
        return PathUtil.defaultSavePath(); // 拼接好盘符
    }

    /**
     * 执行文件存储并且返回存储的文件信息 key="表单name" value="存储路径" 存储的方式以年月日为分割存储
     * 
     * @Title getFilePathMap
     * @author 于国帅
     * @date 2018年3月9日 上午8:42:31
     * @param fileItemList
     * @param fileSaveDir
     *            配置文件读取的路径
     * @return
     * @throws Exception
     */
    public static Map<String, String> getFilePathMap(List<FileItem> fileItemList, String fileSaveDir) throws Exception {
        Map<String, String> filePathMap = new HashMap<>();
        if (CollectionUtils.isEmpty(fileItemList)) {
            return filePathMap;
        }
        if (StringUtils.isBlank(fileSaveDir)) {
            fileSaveDir = defaltSavePath();
        }
        for (FileItem fileItem : fileItemList) {
            if (!fileItem.isFormField()) { // 处理文件表单
                File file = getExistFile(fileSaveDir + File.separator + getNowDate(), getFileName(fileItem.getName()));
                fileItem.write(file); // 将指定流 写入 到 指定文件中 -- mkdirs() 自动创建目录
                fileItem.delete(); // 7删除临时文件
                filePathMap.put(fileItem.getFieldName(), file.getAbsolutePath());
            }
        }
        return filePathMap;
    }

    /**
     * @Title getFileName
     * @author 于国帅
     * @date 2018年3月9日 上午9:23:55
     * @param fileName
     * @return String
     */
    private static String getFileName(String fileName) {
        // * 兼容浏览器， IE ： C:\Users\liangtong\Desktop\abc.txt ; 其他浏览器 ： abc.txt
        fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
        return System.currentTimeMillis() + "@" + fileName;
    }

    /**
     * @Title 获取一个必定存在的文件
     * @author 于国帅
     * @date 2018年3月9日 上午9:06:19
     * @param fileSaveDir
     * @param fileName
     * @return File
     */
    public static File getExistFile(String fileSaveDir, String fileName) {
        File file = new File(fileSaveDir, fileName);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.error("getExistFile 出错", e);
                return null;
            }
        }
        return file;
    }

    /**
     * 
     * 获取当前时间yyyy-MM-dd HH-mm-ss 开头的名称
     * 
     * @Title getNowDateTime
     * @return String
     */
    private static String getNowDateTime() {
        return new SimpleDateFormat("HH时mm分ss秒").format(new Date());
    }

    /**
     * 
     * 获取当前日期yyyy-MM-dd
     * 
     * @Title getNowDate
     * @return String
     */
    private static String getNowDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 默认执行，存储到项目所在的盘符下+项目名称
     * 
     * @Title execute
     * @author 于国帅
     * @date 2018年3月9日 上午9:27:56
     * @return Map<String,String>
     * @throws Exception
     * @throws FileUploadException
     */
    public static Map<String, String> executeUpload(HttpServletRequest request) throws Exception {
        return getFilePathMap(getFileItemList(request));
    }

    public static Map<String, String> executeUpload(HttpServletRequest request, String fileSaveDir) throws Exception {
        return getFilePathMap(getFileItemList(request), fileSaveDir);
    }

    public static boolean executeDownload(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        return executeDownload(request, response, path, null);
    }

    /**
     * 传入文件存在的路径（包含文件名） 下载
     * 
     * @Title executeDownload
     * @author 于国帅
     * @date 2018年3月9日 上午10:29:10
     * @param request
     * @param response
     * @param path
     *            文件存在的位置，包含文件名
     * @param showFileName
     *            显示的下载名称，不存在默认为文件名
     * @return
     * @throws IOException
     *             boolean
     */
    @SuppressWarnings("restriction")
    public static boolean executeDownload(HttpServletRequest request, HttpServletResponse response, String path, String showFileName)
            throws IOException {
        if (StringUtils.isBlank(path)) {
            throw new RuntimeException("下载路径不能为空");
        }
        if (StringUtils.isBlank(showFileName)) {
            showFileName = StringUtils.substringAfterLast(path, File.separator);
        }
        File file = new File(path);
        String userAgent = request.getHeader("User-Agent");
        // * IE 谷歌 采用 URL编码
        if (userAgent.contains("MSIE") || userAgent.contains("Chrome")) {
            showFileName = URLEncoder.encode(showFileName, "UTF-8");
        }
        // * 火狐 采用 Base64编码
        if (userAgent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String encStr = base64Encoder.encode(showFileName.getBytes("UTF-8"));
            showFileName = "=?UTF-8?B?" + encStr + "?=";
        }
        // 设置响应头，通知浏览器应该进行下载，而不是解析。
        response.setHeader("content-disposition", "attachment;filename=" + showFileName);
        ServletOutputStream out = response.getOutputStream();
        IOUtils.copy(FileUtils.openInputStream(file), out);
        return true;
    }
}
