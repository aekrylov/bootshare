package com.github.aekrylov.bootshare.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 12:49 AM
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                .csrf().disable();
    }
}
