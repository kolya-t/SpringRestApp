package ru.eninja.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MvcConfig.class,
        PersistenceConfig.class
})
@ComponentScan("ru.eninja")
public class ApplicationConfig {
}
