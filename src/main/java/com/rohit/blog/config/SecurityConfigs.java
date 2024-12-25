package com.rohit.blog.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface SecurityConfigs extends WebSecurityConfigurer {

    void configure(AuthenticationManagerBuilder builder) throws Exception;
}
