package jdk.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * @ClassName: SecureRandom
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/2 17:12
 * @Version: v1.0
 */
@Slf4j
public class SecureRandomTest {
    /**
     * 总结：因安全扫描结果显示使用：Math.Random() 方法导致熵不足问题，以上作为修改
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void test() throws NoSuchAlgorithmException, NoSuchProviderException {
        //指定算法名称
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        Integer randNum = random.nextInt();//生成10位数的随机数

        Integer randNum2 = random.nextInt(100);//生成0~99的随机数
        log.info(String.valueOf(randNum2));
        //系统将确定环境中是否有所请求的算法实现，是否有多个，是否有首选实现。
//        SecureRandom random2 = SecureRandom.getInstance("SHA1PRNG", "RUN");

    }
}
