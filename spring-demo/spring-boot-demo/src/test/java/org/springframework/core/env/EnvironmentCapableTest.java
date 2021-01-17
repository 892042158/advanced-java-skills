package org.springframework.core.env;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.xmindguoguo.springboot.ApplicationSuperTest;
import top.xmindguoguo.springboot.ext.ApplicationContextHelper;


/**
 * @ClassName: EnvironmentCapableTest
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/13 16:35
 * @Version: v1.0
 */
public class EnvironmentCapableTest extends ApplicationSuperTest {
    @Autowired
    ApplicationContextHelper applicationContextHelper;

    /**
     * 首先，带有Capable后缀的接口在Spring中带有getXXX的含义，也就是实现了这个接口，就可以通过该接口的实例获取到XXX，这个和Aware接口很类似。
     * <p>
     * 所以，这里的EnvironmentCapable接口就是可以获得一个Environment实例。
     */
    @Test
    public void getEnvironment() {
        Environment environment = applicationContextHelper.getApplicationContext().getEnvironment();
        String value = environment.getProperty("javaagent");
        print(value);
    }
}
