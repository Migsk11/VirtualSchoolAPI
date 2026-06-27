package com.api.TechLearnAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Libera todos os endpoints
            .allowedOrigins("http://localhost:5173", "https://cuddly-bassoon-97w4596vqjpwhp9rg-5173.app.github.dev/", "http://127.0.0.1:5175") // O domínio do seu frontend
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD") // Métodos permitidos
            .allowedHeaders("*") // Permite todos os headers
            .allowCredentials(true); // Permite envio de cookies/auth se necessário
    }
}
