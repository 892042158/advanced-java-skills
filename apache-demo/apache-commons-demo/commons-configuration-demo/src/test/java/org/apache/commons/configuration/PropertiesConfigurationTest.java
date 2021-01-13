package org.apache.commons.configuration;

import java.util.List;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.util.PropertiesConfigurationUtil;
import org.apache.commons.io.Charsets;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesConfigurationTest {
    Configuration config = null;

    @Test
    public void Test() {
        Configuration test = new PropertiesConfiguration();
    }

    @Test
    public void load() throws ConfigurationException {
        AbstractConfiguration.setDefaultListDelimiter(';'); // 默认列表分隔符 会影响到后续创建的对象使用分隔符
        PropertiesConfiguration config = new PropertiesConfiguration("commons/test.properties");
        config.setReloadingStrategy(new FileChangedReloadingStrategy()); // 文件发生改变时重新加载
        config.setAutoSave(true); // 修改属性之后自动保存。
        config.setEncoding(Charsets.UTF_8.toString()); // 默认是ISO-8859-1
        System.out.println("===========基础的==================");
        System.out.println(config.getString("kettle.loglevel"));
        System.out.println("===========读取列表==================");
        List<Object> list = config.getList("kettle.list");
        for (Object object : list) {
            System.out.println(object);
        }
    }

    @Test
    public void testUtil() {
        PropertiesConfiguration config = PropertiesConfigurationUtil.getPropertiesConfiguration("commons/test.properties");
        System.out.println("===========基础的==================");
        System.out.println(config.getString("kettle.loglevel"));
    }

    @Test
    public void testLog() {
        log.info("22");
    }
}
