package org.apache.commons.collection.functors;

import org.apache.commons.collections.functors.ConstantFactory;
import org.junit.Test;

/**
 * 常量工厂测试
 * 
 * @ClassName ConstantFactoryTest
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年12月24日 上午10:39:13
 *
 */
public class ConstantFactoryTest {
    @Test
    public void test() {
        ConstantFactory factroy = (ConstantFactory) ConstantFactory.getInstance("name");
        System.err.println(factroy.getConstant());
    }
}
