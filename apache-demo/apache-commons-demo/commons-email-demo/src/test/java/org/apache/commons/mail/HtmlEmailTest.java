package org.apache.commons.mail;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class HtmlEmailTest {
    private static String imgs_path = "imgs"; // 数字

    private static final String zh_img = "/zh_code.png";// 中文
    private static final String en_img = "/encode.png"; // 英文图片
    private static final String number_img = "/number_code.png"; // 数字

    @Before
    public void setUp() throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(imgs_path);
        imgs_path = url.getFile();
    }

    @Test
    public void sendEmailAttachment() throws EmailException {
        HtmlEmail mail = new HtmlEmail();
        mail.setHostName("smtp.qq.com");
        mail.setSmtpPort(25);
        mail.setAuthenticator(new DefaultAuthenticator("2462422212@qq.com", "kbkgdlzohpozdhha"));
        mail.setSSLOnConnect(true);
        mail.setFrom("2462422212@qq.com");
        mail.addTo("1009573717@qq.com"); // 收件人的 邮箱 ，收件人的昵称
        EmailAttachment attach = new EmailAttachment(); // 附件对象
//        String content = "http://imgsrc.baidu.com/baike/pic/item/7aec54e736d12f2ee289bffe4cc2d5628435689b.jpg";
//        attach.setPath("E:\\1A\\fanfan.txt"); // 附件文件在系统中的路径
//        attach.setPath(imgs_path + en_img); // 附件文件在系统中的路径
//        attach.setPath(imgs_path + en_img); //"/F:/github/advanced-java-skills/apache-commons-demo/commons-email-demo/target/classes/imgs/encode.png"
        attach.setPath("F:/github/advanced-java-skills/apache-commons-demo/commons-email-demo/target/classes/imgs/encode.png"); // 附件文件在系统中的路径

        attach.setDisposition(EmailAttachment.ATTACHMENT);

        mail.setSubject("帆帆");
        mail.setHtmlMsg("<html>The apache logo - <img src=\"cid:\"></html>");

//        mail.attach(attach); // 添加附件
        mail.send();
        System.out.println("发送成功");
//        send163();
    }

    @Test
    public void sendQQ() throws EmailException {
        Email email = new SimpleEmail();
        // 设置邮件正文和字符编码
        email.setHostName("smtp.qq.com");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator("2462422212@qq.com", "kbkgdlzohpozdhha"));
        email.setSSLOnConnect(true);
        email.setFrom("2462422212@qq.com");
        email.setSubject("TestMail"); // 邮件主题
        email.setMsg("This is a test mail ... :-)");
        email.addTo("892042158@qq.com"); // 收件人的 邮箱 ，收件人的昵称
        email.send();
        System.out.println("发送成功");
    }

    @Test
    public void send163() throws EmailException {
        Email email = new SimpleEmail();
        // 设置邮件正文和字符编码
        email.setHostName("smtp.163.com");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator("fanfan2462422212@163.com", "fanfan110"));
        email.setSSLOnConnect(true);
        email.setFrom("fanfan2462422212@163.com");
        email.setSubject("TestMail"); // 邮件主题
        email.setMsg("This is a test mail ... :-)");
        email.addTo("892042158@qq.com"); // 收件人的 邮箱 ，收件人的昵称
        email.send();
        System.out.println("发送成功");
    }
}
