package com.example.rent_a_car.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "crud Inicial 3",
        version = "1.0.0",
        description = "This is a crud"

))
public class OpenApiConfig {
}
