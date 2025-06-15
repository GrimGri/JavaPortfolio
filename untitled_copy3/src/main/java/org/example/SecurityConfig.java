package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                        .requestMatchers("/api/perspectiveMan/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()); // Включить базовую аутентификацию//
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin") // ваш логин
                .password("{noop}1234") // {noop} означает незашифрованный пароль
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}
