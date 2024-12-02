package io.kan.opteammer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/assets").permitAll()
                        .requestMatchers("/api/assets/**").permitAll()
                        .requestMatchers("/api/inventories").permitAll()
                        .requestMatchers("/api/inventories/**").permitAll()
                        .requestMatchers("/api/preoperative-assessment").permitAll()
                        .requestMatchers("/api/preoperative-assessment/**").permitAll()
                        .anyRequest().authenticated()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }
}
