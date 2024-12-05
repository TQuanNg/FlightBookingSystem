package com.example.flightapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**") // Allow all endpoints
                .allowedOrigins("http://localhost:3000") // Allow requests from your frontend's origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // HTTP methods you want to allow
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true);
    }
}
