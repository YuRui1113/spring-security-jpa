/*
 * File: src\main\java\com\taylor\springsecurityjpa\config\web\security\SecurityConfig.java
 * Project: spring-security-jpa
 * Created Date: Tuesday, December 26th 2023, 3:35:25 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:39:33 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Tuesday, December 26th 2023	Rui Yu		Initial version
 */

package com.taylor.springsecurityjpa.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String AUTHORITY_ADMIN = "ROLE_ADMIN";
    public static final String AUTHORITY_USER = "ROLE_USER";

    public static final String[] ENDPOINTS_WHITELIST = {
            "/css/**",
            "/js/**",
            "/font-awesome/**",
            "/bootstrap-5.2.3-dist/css/**",
            "/",
            "/index",
            "/login"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorize -> authorize
                        // We are permitting all static resources to be accessed publicly
                        .requestMatchers(ENDPOINTS_WHITELIST)
                        .permitAll()
                        .requestMatchers("/user/**").hasRole(ROLE_USER)
                        .requestMatchers("/admin/**").hasRole(ROLE_ADMIN)
                        .requestMatchers("/anonymous").anonymous()
                        .anyRequest()
                        .authenticated())
                .formLogin(login -> {
                    login.loginPage("/login")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            // 当直接访问login页面时，认证成功会访问index页面
                            // 如果访问其它需要登录的页面时，比如/，认证成功会返回/
                            .defaultSuccessUrl("/index")
                            .failureUrl("/login?error=true")
                            .permitAll();
                })
                .logout(logout -> {
                    logout
                            .logoutSuccessUrl("/login?logout=true")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID");
                })
                .exceptionHandling(exception -> {
                    exception
                            .accessDeniedPage("/accessDenied");
                });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    static GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("ROLE_");
    }
}