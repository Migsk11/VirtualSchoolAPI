package com.api.TechLearnAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf -> csrf.disable()) // Desabilitar CSRF se for API REST
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login").permitAll() // Rotas públicas
                .anyRequest().authenticated() // Resto exige autenticação
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .permitAll()
                ); // Tela de login padrão

        return http.build();
    }

}
