package top.xmindguoguo.commons.fileupload.fileupload;

import common.utils.date.DateUtil;
import common.utils.file.FileUploadUtil;
import common.utils.file.PathUtil;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PropertiesConfiguration config = null;
        try {
//                AbstractConfiguration.setDefaultListDelimiter(';'); // 默认分隔符
            config = new PropertiesConfiguration("fileupload.properties");
            config.setReloadingStrategy(new FileChangedReloadingStrategy()); // 文件发生改变时重新加载
        } catch (ConfigurationException e) {

        }
        String fileSaveDir = config.getString("file.savePath", request.getContextPath() + File.separator + "savePath");
        fileSaveDir = PathUtil.getProjectDrive() + File.separator + fileSaveDir; // 拼接好盘符
        // 拼接一个根据生成时间的文件夹，文件名生成时间+文件名字
        fileSaveDir += File.separator + DateUtil.getNowDate();
        // 配置上传路径
        try {
            // 0.5 检查是否支持文件上传 ,检查请求头Content-Type : multipart/form-data
            if (!ServletFileUpload.isMultipartContent(request)) {
                System.out.println("不要得瑟，没用");
                return;
            }
            // 2.5 上传文件进度，提供监听器进行监听。
//            servletFileUpload.setProgressListener(new MyProgressListener()); //需要自己实现监听器
            Map<String, String> map = FileUploadUtil.executeUpload(request, fileSaveDir);
            for (Entry<String, String> m : map.entrySet()) {
                System.out.println(m.getKey());
                System.out.println(m.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
