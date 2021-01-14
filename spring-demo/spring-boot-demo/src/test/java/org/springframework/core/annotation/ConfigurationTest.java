package org.springframework.core.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ConfigurationTest {
    @Bean
    public TestSerivce getTestSerivce() {
        return new TestSerivce();
    }
}
@Component
class TestSerivce {

}
