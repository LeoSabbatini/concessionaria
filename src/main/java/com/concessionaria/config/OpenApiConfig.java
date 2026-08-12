package com.concessionaria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("Concessionária API")
                .description("API que gerencia o estoque de carros e o cadastro de clientes.")
                .version("1.0.0"));

    }
}