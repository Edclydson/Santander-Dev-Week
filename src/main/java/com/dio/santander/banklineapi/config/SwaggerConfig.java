package com.dio.santander.banklineapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Bankline Api").contact(new Contact().name("Edclydson").url("https://linkedin.com/in/edclydson"))
                        .description("Api desenvolvida durante a Santander Dev Week - Projeto disponível em: https://github.com/Edclydson/Santander-Dev-Week")
                        .version("1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/edclydson"));
    }
}
