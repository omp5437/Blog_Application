package com.prakash.blog_app.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder().group("public").pathsToMatch("/**").build();
    }
}
