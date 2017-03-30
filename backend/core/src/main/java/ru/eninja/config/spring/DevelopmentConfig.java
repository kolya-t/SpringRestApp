package ru.eninja.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


@Configuration
@Profile("dev")
@PropertySource("classpath:database_dev.properties")
public class DevelopmentConfig {

    @Bean("dataSource")
    public EmbeddedDatabase getDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("test-db/schema.sql")
                .addScript("test-db/test-data.sql")
                .build();
    }
}
