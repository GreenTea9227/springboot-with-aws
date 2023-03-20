package com.jojoldu.book.springbootwebservice.config;

import com.jojoldu.book.springbootwebservice.config.auth.CustomOAuth2UserService;
import com.jojoldu.book.springbootwebservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/", "/css/**", "/images/**",
                                    "/js/**", "/h2-console/**").permitAll()
                            .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                            .anyRequest().authenticated();
                });
        http
                .logout().logoutSuccessUrl("/")
                .and()
                .oauth2Login(config -> config.userInfoEndpoint(auth -> {
                    auth.userService(customOAuth2UserService);
                }));

        return http.build();
    }
}
