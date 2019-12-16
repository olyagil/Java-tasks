package com.epam.task1.config;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.Transient;
import java.io.IOException;

@Configuration
@ComponentScan(basePackages = ("com.epam.task1"))
public class TestConfig {
    private static final Logger logger =
            LoggerFactory.getLogger(TestConfig.class);

    private static final String APP_PROPERTIES = "/app.properties";

    @Bean
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig(APP_PROPERTIES);
        logger.debug("Getting datasource");
        return new HikariDataSource(config);
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        try {
            EmbeddedPostgres embeddedPostgres =
                    EmbeddedPostgres.builder().start();
            logger.debug("Getting embedded jdbcTemplate");
            return new JdbcTemplate(embeddedPostgres.getPostgresDatabase());
        } catch (IOException e) {
            logger.error("Can't create embedded database", e);
            throw new BeanCreationException(e.getMessage());
        }
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
