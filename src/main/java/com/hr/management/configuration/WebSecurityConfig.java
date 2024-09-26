package com.hr.management.configuration;

import com.hr.management.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
     @Value("${apiPrefix}")
    private String apiPrefix;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests.requestMatchers("**")
                            .permitAll()
                            .requestMatchers(HttpMethod.PUT,
                            String.format("%s/roles", apiPrefix)).hasRole("admin")
                            .requestMatchers(HttpMethod.POST,
                            String.format("%s/roles", apiPrefix)).hasRole("admin")
                            .requestMatchers(HttpMethod.POST,
                            String.format("%s/employees", apiPrefix)).hasRole("admin")
                            .anyRequest().authenticated();
                });
        return http.build();
    }
}
