package top.xmindguoguo.commons.exec.script;

import common.utils.file.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 操作脚本 暂时用的少 ，后期多了再重构分系统
 * 
 * @ClassName ScriptUtil
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年9月12日 下午5:19:03
 *
 */
@Slf4j
public class ScriptUtil {
    // Process p = Runtime.getRuntime().exec(script);
    /**
     * 核心代码 执行cmd 脚本命令
     * 
     * @Title runScript
     * @author 于国帅
     * @date 2018年9月14日 上午9:33:44
     * @param scriptCommand
     * @return boolean
     */
    public static boolean runScript(String scriptCommand) {
        if (StringUtils.isBlank(scriptCommand)) {
            log.error("scriptCommand 非法");
            return false;
        }
        BufferedReader stdInput = null;
        BufferedReader stdError = null;
        try {
            Process p = Runtime.getRuntime().exec(scriptCommand);
            // 防止缓冲区用完
            stdInput = new BufferedReader(new InputStreamReader(p.getInputStream(), "GB2312"));
            stdError = new BufferedReader(new InputStreamReader(p.getErrorStream(), "GB2312"));
            String s = "";
            String cmdLog = "";
            while ((s = stdInput.readLine()) != null) {
                cmdLog += s;
            }
            cmdLog += "\n\r";
            while ((s = stdError.readLine()) != null) {
                cmdLog += s;
            }
            log.error("scriptCommand===" + scriptCommand);
            log.error("scriptCommandLog===" + cmdLog);
            if (p != null && p.waitFor() == 0) {
                return true; // 执行成功
            }
            return false;
        } catch (Exception e) {
            log.error("scriptCommand执行错误" + scriptCommand, e);
            return false;
        } finally {
            try {
                if (stdInput != null) {
                    stdInput.close();
                }
                if (stdError != null) {
                    stdError.close();
                }
            } catch (IOException e) {
                log.error("scriptCommand关闭流失败", e);
            }
        }
    }

    /**
     * * 2、命令提示窗口执行生成命令。
     * 
     * 格式：wsimport -s "src目录" -p “生成类所在包名” -keep “wsdl发布地址”
     * 
     * 示例：
     * 
     * wsimport -s G:\\workspace\\webService\\TheClient\\src -p com.hyan.client -keep http://localhost:9001/Service/ServiceHello?wsdl
     * 
     * 说明：
     * 
     * 1）"src目录"地址不可含空格
     * 
     * 2）“wsdl发布地址”不要漏了“?wsdl”
     * 
     * @Title genWebServiceClient
     * @author 于国帅
     * @date 2018年9月12日 下午5:24:06
     * @param srcDir
     *            src目录
     * @param genPackageName
     *            生成类所在包名
     * @param url
     *            wsdl发布地址 不要漏了“?wsdl”
     * @return boolean
     */
    public static boolean genWebServiceClient(String srcDir, String genPackageName, String url) {
        String script = "wsimport -s " + srcDir + " -p " + genPackageName + " -keep " + url;
        boolean flag = runScript(script);
        if (flag) {
            log.error("生成成功路径在" + srcDir + File.separator + PathUtil.getPackageDir(genPackageName));
        }
        return flag;
    }

    /**
     * 生成到maven 路径下
     */
    public static boolean genWebServiceClient(Class<?> clazz, String url) {
        return genWebServiceClient(clazz, url, true);
    }

    /**
     * 默认生成webservice 客户端代码到本地目录
     * 
     * @Title genWebServiceClient
     * @author 于国帅
     * @date 2018年9月14日 上午9:38:29
     * @param clazz
     * @param url
     * @param isMaven
     * @return boolean
     */
    public static boolean genWebServiceClient(Class<?> clazz, String url, boolean isMaven) {
        String srcDir = PathUtil.getProjectPath() + (isMaven ? "\\src\\main\\java" : "\\src");
        String genPackageName = PathUtil.getClassPackage(clazz);
        return genWebServiceClient(srcDir, genPackageName, url);
    }

    public static void main(String[] args) {
        String url = "http://localhost:9000/webService/demoWebService?wsdl";
        boolean flag = genWebServiceClient(ScriptUtil.class, url);
        System.out.println(flag);
    }
}
