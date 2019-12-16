package com.epam.task1.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = ("com.epam.task1"))
public class AppConfig {
    private static final Logger logger =
            LoggerFactory.getLogger(AppConfig.class);

    private static final String APP_PROPERTIES = "/app.properties";

    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        logger.debug("Getting jdbcTemplate");
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig(APP_PROPERTIES);
        logger.debug("Getting datasource");
        return new HikariDataSource(config);
    }

    @Bean("flyway")
    public Flyway getFlyway() {
        FluentConfiguration fluentConfiguration =
                Flyway.configure().dataSource(getDataSource());
        fluentConfiguration.baselineOnMigrate(true);
        return new Flyway(fluentConfiguration);
    }
}
