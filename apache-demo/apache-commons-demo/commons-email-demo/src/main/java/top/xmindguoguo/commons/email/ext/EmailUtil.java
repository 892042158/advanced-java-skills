package top.xmindguoguo.commons.email.ext;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.*;

/**
 * 发送简单邮件，发送附件邮件，发送html邮件，发送html时带有图片（他得配置一个能够调用出来的接口） 暂时缺少发送多个附件
 * 
 * @ClassName EmailUtil
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年5月27日 下午11:43:46
 *
 */
@Slf4j
public class EmailUtil {
    private final static String HOST_NAME = "smtp.qq.com";
    private final static String send_email = "2462422212@qq.com"; // 发送的邮箱
    private final static String AUTH_CODE = "kbkgdlzohpozdhha"; // 邮箱stmp 的授权码
    private final static String CHARSET = "UTF-8";
    private final static String DEFAULT_SUBJECT = "hello";

    public static boolean sendSimple(String acceptEmail, String msg) {
        return EmailUtil.sendSimple(acceptEmail, DEFAULT_SUBJECT, msg);
    }

    /**
     * 发送 文本消息
     * 
     * @Title sendSimple
     * @author 于国帅
     * @date 2018年5月28日 上午12:02:45
     * @param acceptEmail
     * @param subject
     *            邮件主题
     * @param msg
     *            邮件信息
     * @return boolean
     */
    public static boolean sendSimple(String acceptEmail, String subject, String msg) {
        Email email = getEmail();
        try {
            email.setFrom(send_email);
            email.setSubject(subject); // 邮件主题
            email.setMsg(msg);
            email.addTo(acceptEmail); // 收件人的 邮箱 ，收件人的昵称
            email.send();
        } catch (EmailException e) {
            log.debug("sendSimple", e);
            return false;
        }
        return true;
    }

    public static boolean sendHtml(String acceptEmail, String subject, String html) {
        HtmlEmail email = new HtmlEmail();
        email.setCharset(CHARSET);
        // 设置邮件正文和字符编码
        email.setHostName(HOST_NAME);
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator(send_email, AUTH_CODE));
        email.setSSLOnConnect(true);
        try {
            email.setFrom(send_email);
            email.setSubject(subject); // 邮件主题
            email.setHtmlMsg(html);
            email.addTo(acceptEmail); // 收件人的 邮箱 ，收件人的昵称
            email.send();
        } catch (EmailException e) {
            log.debug("sendHtml", e);
            return false;
        }
        return true;
    }

    public static boolean sendEmailAttachment(String acceptEmail, String msg, String filePath) {
        return sendEmailAttachment(acceptEmail, DEFAULT_SUBJECT, msg, filePath);
    }

    /**
     * 发送带有附件的email
     * 
     * @Title sendEmailAttachment
     * @author 于国帅
     * @date 2018年5月28日 上午12:07:42
     * @param acceptEmail
     * @param subject
     * @param msg
     * @param filePath
     * @return boolean
     */
    public static boolean sendEmailAttachment(String acceptEmail, String subject, String msg, String filePath) {
        Email email = getEmail();
        try {
            email.setFrom(send_email);
            email.setSubject(subject); // 邮件主题
            email.setMsg(msg);
            EmailAttachment attach = new EmailAttachment(); // 附件对象
            attach.setPath(filePath); // 附件文件在系统中的路径
            attach.setDisposition(EmailAttachment.ATTACHMENT);
            email.addTo(acceptEmail); // 收件人的 邮箱 ，收件人的昵称
            email.send();
        } catch (EmailException e) {
            log.debug("sendEmailAttachment", e);
            return false;
        }
        return true;
    }

    /**
     * 获得一个设置好的发送email对象
     * 
     * @Title getEmail
     * @author 于国帅
     * @date 2018年5月28日 上午12:02:24
     * @return Email
     */
    public static Email getEmail() {
        Email email = new SimpleEmail();
        email.setCharset(CHARSET);
        // 设置邮件正文和字符编码
        email.setHostName(HOST_NAME);
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator(send_email, AUTH_CODE));
        email.setSSLOnConnect(true);
        return email;
    }
}
