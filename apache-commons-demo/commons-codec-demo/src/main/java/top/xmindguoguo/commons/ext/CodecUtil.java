package top.xmindguoguo.commons.ext;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 常用的加密解密的整合
 * 
 * @依赖 commons-codes jar
 * 
 * 
 * @ClassName CodecUtil
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年9月5日 下午3:33:29 如基本的单向加密算法： BASE64 严格地说，属于编码格式，而非加密算法
 * 
 *       MD5(Message Digest algorithm 5，信息摘要算法)
 * 
 *       SHA(Secure Hash Algorithm，安全散列算法)
 * 
 *       HMAC(Hash Message Authentication Code，散列消息鉴别码)
 * 
 * 
 *       复杂的对称加密（DES、PBE）、非对称加密算法：
 * 
 *       DES(Data Encryption Standard，数据加密算法)
 * 
 *       PBE(Password-based encryption，基于密码验证)
 * 
 *       RSA(算法的名字以发明者的名字命名：Ron Rivest, AdiShamir 和Leonard Adleman)
 * 
 *       DH(Diffie-Hellman算法，密钥一致协议)
 * 
 *       DSA(Digital Signature Algorithm，数字签名)
 * 
 *       ECC(Elliptic Curves Cryptography，椭圆曲线密码编码学)
 */
public class CodecUtil {
    private static final String DEFAULT_SALT = "codec";

    /**
     * base64 加密
     * 
     * @Title encodeBase64
     * @author 于国帅
     * @date 2018年9月5日 下午4:49:56
     * @param str
     *            要加密的字符串
     * @param salt
     *            盐
     * @return String 加密后的字符串
     */
    public static String encodeBase64(String str, String salt) {
        if (str != null || salt != null) {
            return new String(Base64.encodeBase64(str.getBytes(), true));
        }
        return null;
    }

    public static String encodeBase64(String str) {
        return encodeBase64(str, DEFAULT_SALT);
    }

    public static String encodeShaHex(String str) {
        return encodeShaHex(str, DEFAULT_SALT);
    }

    /**
     * Sha 加密
     * 
     * @Title encodeShaHex
     * @author 于国帅
     * @date 2018年9月5日 下午4:49:56
     * @param str
     *            要加密的字符串
     * @param salt
     *            盐
     * @return String 加密后的字符串
     */
    public static String encodeShaHex(String str, String salt) {
        if (str != null || salt != null) {
            return DigestUtils.shaHex(str + salt);
        }
        return null;
    }

    /**
     * MD5 加密
     * 
     * @Title encodeMd5Hex
     * @author 于国帅
     * @date 2018年9月5日 下午4:49:56
     * @param str
     *            要加密的字符串
     * @param salt
     *            盐
     * @return String 加密后的字符串
     */
    public static String encodeMd5Hex(String str, String salt) {
        if (str != null || salt != null) {
            return DigestUtils.md5Hex(str + salt);
        }
        return null;
    }

    public static String encodeMd5Hex(String str) {
        return encodeMd5Hex(str, DEFAULT_SALT);
    }
}
