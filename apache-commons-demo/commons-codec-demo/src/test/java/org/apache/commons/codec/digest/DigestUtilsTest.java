package org.apache.commons.codec.digest;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class DigestUtilsTest {

    static final String TARGET = "中文123abc,./";

    /*
     * 不可逆算法  MD5
     */
    @Test
    public void Md5() {
        String str = DigestUtils.md5Hex(TARGET);
        System.out.println("md5Hex:     " + str);
    }

    /*
     * 不可逆算法  SHA1
     */
    @Test
    public void Sha1() {
        String str = DigestUtils.shaHex(TARGET);
        print("shaHex:     " + str);
        str = DigestUtils.sha256Hex(TARGET);
        print("sha256Hex:  " + str);
        str = DigestUtils.sha384Hex(TARGET);
        print("sha384Hex:  " + str);
        str = DigestUtils.sha512Hex(TARGET);
        print("sha512Hex:  " + str);
    }

    /*
     * 可逆算法  BASE64
     */

    @Test
    public void Base64() throws UnsupportedEncodingException {
        // 加密
        byte[] b = Base64.encodeBase64(TARGET.getBytes(), true);
        String str = new String(b);
        print("BASE64:     " + str);

        // 解密
        byte[] b1 = Base64.decodeBase64(str);
        print("解密之后内容为：  " + new String(b1));
//        final Base64 base64 = new Base64();
//        final String text = "字串文字";
//        final byte[] textByte = text.getBytes("UTF-8");
//        // 编码
//        final String encodedText = base64.encodeToString(textByte);
//        System.out.println(encodedText);
//        // 解码
//        System.out.println(new String(base64.decode(encodedText), "UTF-8"));
//
//        final Base64 base64 = new Base64();
//        final String text = "字串文字";
//        final byte[] textByte = text.getBytes("UTF-8");
//        // 编码
//        final String encodedText = base64.encodeToString(textByte);
//        System.out.println(encodedText);
//        // 解码
//        System.out.println(new String(base64.decode(encodedText), "UTF-8"));
    }

    public void print(Object obj) {
        System.out.println(obj);
    }
}
