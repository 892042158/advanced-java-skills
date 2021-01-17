package org.springframework.boot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.xmindguoguo.springboot.ApplicationSuperTest;


/**
 * @ClassName: ApplicationArgumentsTest
 * @author: 于国帅
 * @Description:
 * @Date: 2020/12/13 18:04
 * @Version: v1.0
 * @see https://blog.csdn.net/weter_drop/article/details/108311428
 */
public class ApplicationArgumentsTest extends ApplicationSuperTest {

    @Autowired
    private ApplicationArguments arguments;

    @Test
    public void printArgs() {
        System.out.println("# 非选项参数数量: " + arguments.getNonOptionArgs().size());
        System.out.println("# 选项参数数量: " + arguments.getOptionNames().size());
        System.out.println("# 非选项参具参数:");
        arguments.getNonOptionArgs().forEach(System.out::println);

        System.out.println("# 选项参数具体参数:");
        arguments.getOptionNames().forEach(optionName -> {
            System.out.println("--" + optionName + "=" + arguments.getOptionValues(optionName));
        });
    }
}
