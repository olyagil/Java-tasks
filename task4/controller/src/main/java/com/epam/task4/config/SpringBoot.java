package com.epam.task4.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableResourceServer
@SpringBootApplication
@EnableSwagger2
@EnableMongoRepositories(basePackages = "com.epam.task4.repository")
@Configuration
@ComponentScan(basePackages = "com.epam.task4")
public class SpringBoot extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select() //creates a builder, which is used to define which controllers and which of their methods should be included in the generated documentation.
                .apis(RequestHandlerSelectors.basePackage("com.epam.task4.controller")) //defines the classes (controller and model classes) to be included.
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Travel Agency Spring Boot REST API")
                .description("Travel Agency REST API")
                .contact(new Contact("Hil Volha", "www.epam.com", "hil_volha@epam.com"))
                .license("Apache 2.0")
                .version("1.0.0")
                .build();
    }


}
