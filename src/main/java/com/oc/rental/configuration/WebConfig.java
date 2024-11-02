package com.oc.rental.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
    public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/rentals-images/**")
                    .addResourceLocations("file:/home/solange/IdeaProjects/ocdajap3_backend/rental/src/main/resources/static/rentals-images/");
        }
    }

