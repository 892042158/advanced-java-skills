package org.springframework.lang;

import org.junit.Test;

import javax.annotation.Nonnull;

/**
 * 参考 NullableTest
 * 
 * @ClassName NonnullTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年3月5日 下午5:29:50
 *
 */
public class NonnullTest {
    @Test
    public void test() {
        syso("222");
        syso(null);

    }

    public void syso(@Nonnull String name) {
        System.err.println(name);
    }
}
