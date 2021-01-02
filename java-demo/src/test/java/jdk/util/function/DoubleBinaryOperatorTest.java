package jdk.util.function;

import java.util.function.DoubleBinaryOperator;

import org.junit.Test;

/**
 * 探索这个这个借口怎么操作
 * 
 * @ClassName DoubleBinaryOperatorTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月21日 上午8:32:33
 *
 */
public class DoubleBinaryOperatorTest {
    @Test
    public void test() {
        DoubleBinaryOperator dbo = ((x, y) -> {
            System.err.println(x + "|" + y);
            return x + y;
        });
        double value = dbo.applyAsDouble(22, 100);
        System.err.println(value);
    }
}
