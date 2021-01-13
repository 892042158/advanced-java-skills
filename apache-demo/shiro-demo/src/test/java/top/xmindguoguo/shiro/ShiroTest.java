package top.xmindguoguo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTest {
    @Test
    public void testHelloworld() {
        // 1、获取SecurityManager工厂，此处使用Ini配置文件初始化 SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager); // 接着获取 SecurityManager 并绑定到 SecurityUtils，这是一个全局设置，设置一次即可；

        // 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            // 4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            // 5、身份验证失败
//            DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过多）、IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）等
            e.printStackTrace();
        }
        System.out.println(subject.isAuthenticated());
//        Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录
        // 6、退出
        subject.logout();
    }
}
