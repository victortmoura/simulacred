package com.br.simulacred.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Loan Simulation API")
                        .description("API for simulating loan calculations")
                        .version("1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/victortmoura/simulacred"));
    }
}
