package com.vzsme.controllerService;

import com.vzsme.controllerService.model.AppProperties.Props;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //@Autowired
    // NOTE: Auto wire does not work here so using default values
    private Props props = new Props();

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.useDefaultResponseMessages(false) // Allows you to document your own response codes for each request via @ApiResponses
                .select()
                .paths(regex("/api/v.*"))
                //.paths(PathSelectors.any())
                //.apis(RequestHandlerSelectors.basePackage("com.vzsme.springframework.controller"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        String version = props.getVersion();
        String name = props.getName();
        String desc = props.getDescription();

        String title = String.format("%s REST API - Version: %s", name, version).trim();
        return new ApiInfoBuilder()
                .title(title)
                .description(desc)
                //.version(version)
                .build();
    }
}