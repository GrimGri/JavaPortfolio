package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()  //
                        .requestMatchers(HttpMethod.GET, "/api/perspectiveMan").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/perspectiveMan/{id}").authenticated() //
                        .requestMatchers(HttpMethod.POST, "/api/perspectiveMan").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/perspectiveMan/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/perspectiveMan/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()); // Включить базовую аутентификацию//
        return http.build();
    }
}
