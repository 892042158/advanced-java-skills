package org.apache.commons.configuration.util;

import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 另一种方式的单例模式(外观模式)
 * 
 * @ClassName ConfigurationKit
 * @author <a href="892042158@qq.com" target="_blank">于国帅</a>
 * @date 2018年3月2日 下午4:00:20
 */
public class ConfigurationKit {
    public static final PropertiesConfiguration properties_Test = PropertiesConfigurationUtil
            .getPropertiesConfiguration("commons/test.properties");

}
