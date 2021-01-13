package org.apache.commons.configuration.util;

import java.io.File;
import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesConfigurationUtil {
    /**
     * //注意路径默认指向的是classpath的根目录
     * 
     * @Title getPropertiesConfiguration
     * @author 于国帅
     * @date 2018年3月2日 下午4:37:37
     * @param fileName
     * @return PropertiesConfiguration
     */
    public static PropertiesConfiguration getPropertiesConfiguration(String fileName) {
        PropertiesConfiguration config = null;
        try {
            config = new PropertiesConfiguration(fileName);
        } catch (ConfigurationException e) {
            log.error("getPropertiesConfiguration", e);
        }
        return config;
    }

    public static PropertiesConfiguration getPropertiesConfiguration(URL url) {
        PropertiesConfiguration config = null;
        try {
            config = new PropertiesConfiguration(url);
        } catch (ConfigurationException e) {
            log.error("getPropertiesConfiguration", e);
        }
        return config;
    }

    public static PropertiesConfiguration getPropertiesConfiguration(File file) {
        PropertiesConfiguration config = null;
        try {
            config = new PropertiesConfiguration(file);
        } catch (ConfigurationException e) {
            log.error("getPropertiesConfiguration", e);
        }
        return config;
    }
}
