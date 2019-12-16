package com.epam.task2.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.Transient;
import java.util.Properties;

@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@ComponentScan(basePackages = ("com.epam.task2"))
@Slf4j
@EnableAspectJAutoProxy
public class AppConfig {

    private static final String APP_PROPERTIES = "/app.properties";

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em =
                new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan("com.epam.task2.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;

    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect",
                "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.setProperty("hibernate.format_sql", "true");
        return properties;
    }

    @Bean
    public PlatformTransactionManager getPlatformTransactionManager(EntityManagerFactory managerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(managerFactory);
        return transactionManager;
    }

    @Bean
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig(APP_PROPERTIES);
        log.debug("Getting datasource");
        return new HikariDataSource(config);
    }

    @Transient
    @Bean("flyway")
    public Flyway getFlyway() {
        FluentConfiguration fluentConfiguration =
                Flyway.configure().dataSource(getDataSource());
        fluentConfiguration.baselineOnMigrate(true);
        return new Flyway(fluentConfiguration);
    }

}
