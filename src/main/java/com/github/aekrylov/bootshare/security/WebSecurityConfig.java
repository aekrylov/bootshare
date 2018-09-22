package com.github.aekrylov.bootshare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 12:49 AM
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OtpAuthenticationProvider otpAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/f/**", "/login", "/auth/**").permitAll()
                    .antMatchers("/static/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .csrf().disable() //todo
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/auth/login")
                    .usernameParameter("phone")
                    .passwordParameter("code");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(otpAuthenticationProvider);
    }
}
