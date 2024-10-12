package com.ads.dacapicar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Desabilita a proteção CSRF (não recomendado para produção)
                .authorizeRequests()
                .anyRequest().permitAll()  // Permite acesso a todos os endpoints sem autenticação
                .and()
                .httpBasic().disable();  // Desabilita o login básico HTTP

        return http.build();
    }
}
