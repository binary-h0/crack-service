package com.kt.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("API 문서")
                .version("v1.0.0")
                .description("API 문서 입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
