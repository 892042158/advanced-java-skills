package jdk.lang;

import org.junit.Test;

/**
 * @see https://www.jianshu.com/p/52cdc402fb5d 所谓的函数式接口，当然首先是一个接口，然后就是在这个接口里面只能有一个抽象方法。 如果不符合就报错
 * @ClassName FunctionalInterfaceTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2019年1月15日 下午9:46:44 函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。 函数式接口可以被隐式转换为 lambda 表达式。
 */
@FunctionalInterface
interface FunctionalInterfaceTest2 {
    public void showMsg(String message); // 注释掉这个 就会报错误

}

public class FunctionalInterfaceTest {
    @Test
    public void testJDK8Before() {
        FunctionalInterfaceTest2 test = new FunctionalInterfaceTest2() {
            @Override
            public void showMsg(String message) {
                System.err.println("Hello " + message);

            }
        };
        test.showMsg("jdk7");
    }

    @Test
    public void testJDK8After() {
        FunctionalInterfaceTest2 test = message -> System.out.println("Hello " + message);
        test.showMsg("jdk8");

    }
}
