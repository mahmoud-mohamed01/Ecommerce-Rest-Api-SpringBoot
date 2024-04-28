package com.example.ecommerceRestAPI.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    isAuth isAuth;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf().disable().cors().disable();
        httpSecurity.addFilterBefore(isAuth, AuthorizationFilter.class);

        //change permissions to access the end point
        httpSecurity.authorizeHttpRequests().requestMatchers(HttpMethod.POST,"/products").authenticated().
                requestMatchers("/products/{id},/products","/products/prod","/auth/register","/auth/login").permitAll().
                anyRequest().authenticated();
        return httpSecurity.build();
    }
}
