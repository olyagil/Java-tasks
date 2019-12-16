package com.epam.task4.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoConfig {

    @Value("localhost")
    private String mongoHost;

    @Value("27017")
    private String mongoPort;

    @Value("AgencyDB")
    private String mongoDB;

    @Bean
    public MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClient(mongoHost + ":" + mongoPort), mongoDB);
    }

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(mongoHost, Integer.parseInt(mongoPort));
    }
}