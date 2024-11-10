package com.oc.rental.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import io.swagger.v3.oas.models.tags.Tag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(new Info()
                        .title("Chatop API Documentation")
                        .description("Swagger documentation for the API Chatop app.")
                        .version("1.0.0"))
                .tags(List.of(
                        new Tag().name("Authentication").description("Operations for logging or registering a user"),
                        new Tag().name("Messages").description("Send messages"),
                        new Tag().name("Rentals").description("Operations about rentals"),
                        new Tag().name("Users").description("Operations about users")));
    }

}
//        return new OpenAPI()
//                .info(new Info().title("Your API Title")
//                        .version("1.0")
//                        .description("API Description"))
//                .components(new Components()
//                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("Bearer")
//                                .bearerFormat("JWT")
//                                .in(SecurityScheme.In.HEADER)
//                                .name("Authorization")))
//                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
//    }
//}
