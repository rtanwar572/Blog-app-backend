package com.rohit.blog.config;

import com.rohit.blog.security.CustomUserDetailService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements SecurityConfigs {

    @Autowired
    private CustomUserDetailService detailService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests)-> requests.
                requestMatchers("/api/posts").permitAll().anyRequest().permitAll());

        return httpSecurity.build();
    }
//    requestMatchers("/api/posts").permitAll()
    //                        .requestMatchers("/api/user").hasRole("USER")
    @Override
    public void init(SecurityBuilder builder) throws Exception {

    }

    @Override
    public void configure(SecurityBuilder builder) throws Exception {

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(this.detailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
