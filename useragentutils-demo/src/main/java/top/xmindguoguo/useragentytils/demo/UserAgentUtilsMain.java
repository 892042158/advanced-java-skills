package top.xmindguoguo.useragentytils.demo;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @ClassName: UserAgentUtilsMain
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/9 2:02
 * @Version: v1.0
 */
@Slf4j
public class UserAgentUtilsMain {
    String chrome_user_agent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36";

    @Test
    public void test() {
        // 解析agent字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(chrome_user_agent);
        // 获取浏览器对象
        Browser browser = userAgent.getBrowser();
        // 获取操作系统对象
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();

        log.info("浏览器名:" + browser.getName());
        log.info("浏览器类型:" + browser.getBrowserType());
        log.info("浏览器家族:" + browser.getGroup());
        log.info("浏览器生产厂商:" + browser.getManufacturer());
        log.info("浏览器使用的渲染引擎:" + browser.getRenderingEngine());
        log.info("浏览器版本:" + userAgent.getBrowserVersion());

        log.info("操作系统名:" + operatingSystem.getName());
        log.info("访问设备类型:" + operatingSystem.getDeviceType());
        log.info("操作系统家族:" + operatingSystem.getGroup());
        log.info("操作系统生产厂商:" + operatingSystem.getManufacturer());
    }
}
