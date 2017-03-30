package ru.eninja.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:app.properties")
@Import({DevelopmentConfig.class, ProductionConfig.class})
@EnableJpaRepositories("ru.eninja.dao")
@ComponentScan("ru.eninja.service")
public class PersistenceConfig {

    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
        env.acceptsProfiles(env.getRequiredProperty("spring.profiles.active"));
    }

    @Autowired
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactory.setPackagesToScan(env.getRequiredProperty("packages_to_scan.entities"));
        entityManagerFactory.setJpaProperties(getHibernateProperties());

        return entityManagerFactory;
    }

    @Autowired
    @Bean("transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory emf, DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(emf);

        transactionManager.setDataSource(dataSource);
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean("exceptionTranslation")
    public PersistenceExceptionTranslationPostProcessor getExceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();

        for (String propertyKey : new String[]{
                "hibernate.dialect",
                "hibernate.hbm2ddl.auto",
                "hibernate.show_sql",
                "hibernate.format_sql"
        }) {
            properties.put(propertyKey, env.getProperty(propertyKey));
        }

        return properties;
    }
}
