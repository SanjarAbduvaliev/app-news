package com.example.appnews.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    UserDetailsService userDetailsService;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    public void authConfig(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(encoder());
    }


    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity http) throws Exception {
        DefaultSecurityFilterChain build = http
                .csrf().disable().cors().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/auth/**").permitAll();
                    auth.requestMatchers("/login").permitAll();
                    auth.anyRequest().authenticated();
                }).httpBasic().disable()
                .formLogin(Customizer.withDefaults())
                .build();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        //10-qadam.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return build;

    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
