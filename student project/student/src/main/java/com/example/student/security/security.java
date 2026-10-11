package com.example.student.security;

import com.example.student.util.JwtFilter; // Corrected spelling from JwtFliter to JwtFilter
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;

@Configuration
public class security {

    private final JwtFilter jwtFilter;

    // Manual constructor for dependency injection
    public security(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/api/login", "/api/register").permitAll()
                .anyRequest().authenticated()
            )
            // 1. Add your custom JWT filter before the standard login filter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            // 2. Keep httpBasic for debugging or remove if strictly using JWT
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}