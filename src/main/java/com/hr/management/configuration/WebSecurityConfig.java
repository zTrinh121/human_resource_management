package com.hr.management.configuration;

import com.hr.management.filter.JwtTokenFilter;
import com.hr.management.model.Roles;
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
                    requests
                            .requestMatchers(
//                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/users/login", apiPrefix))
                            .permitAll()
                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/roles/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.USER)
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/roles/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/roles/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/roles/**", apiPrefix)).hasRole(Roles.ADMIN)

                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/employees/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.USER)
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/employees/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/employees/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.USER)
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/employees/**", apiPrefix)).hasRole(Roles.ADMIN)

                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/departments/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.USER)
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/departments/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/departments/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/departments/**", apiPrefix)).hasRole(Roles.ADMIN)

                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/jobs/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.USER)
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/jobs/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/jobs/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/jobs/**", apiPrefix)).hasRole(Roles.ADMIN)

                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/job_history/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.USER)
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/job_history/**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/job_history?**", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/job_history?**", apiPrefix)).hasRole(Roles.ADMIN)

                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/users/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.USER)
                            .requestMatchers(HttpMethod.POST,
                                    String.format("%s/users/register", apiPrefix)).hasRole(Roles.ADMIN)
                            .requestMatchers(HttpMethod.PUT,
                                    String.format("%s/users/**", apiPrefix)).hasAnyRole(Roles.ADMIN, Roles.USER)
                            .requestMatchers(HttpMethod.DELETE,
                                    String.format("%s/users/**", apiPrefix)).hasRole(Roles.ADMIN)

                            .anyRequest().authenticated();
                });
        return http.build();
    }
}