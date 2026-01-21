package com.pricing.infrastructure.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pricingOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Pricing API")
                        .description("API to found applicable price for a product/brand at a given date")
                        .version("v1"))
                .externalDocs(new ExternalDocumentation()
                        .description("Import this OpenAPI in Postman to get all endpoints"));
    }
}
