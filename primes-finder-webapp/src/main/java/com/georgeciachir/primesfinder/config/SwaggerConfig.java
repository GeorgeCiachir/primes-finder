package com.georgeciachir.primesfinder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static java.util.Collections.emptyList;

@Configuration
public class SwaggerConfig {

    @Value("${api.contact.name}")
    private String name;
    @Value("${api.contact.url}")
    private String url;
    @Value("${api.contact.email}")
    private String email;

    @Value("${api.title}")
    private String title;
    @Value("${api.description}")
    private String description;
    @Value("${api.version}")
    private String version;
    @Value("${api.termsOfServiceUrl}")
    private String termsOfServiceUrl;
    @Value("${api.license}")
    private String license;
    @Value("${api.inlicenseUrlfo}")
    private String inlicenseUrlfo;

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.georgeciachir.primesfinder.resource"))
                .paths(PathSelectors.any())
                .build();

        return docket
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        Contact contact = apiContact();
        return new ApiInfo(
                title, description, version, termsOfServiceUrl,
                contact, license, inlicenseUrlfo, emptyList());
    }

    private Contact apiContact() {
        return new Contact(name, url, email);
    }
}