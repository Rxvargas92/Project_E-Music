package com.e_music.project_emusic.config;

import com.e_music.project_emusic.services.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ServiceUser serviceUser;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(serviceUser);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .regexMatchers("/admin/*").hasRole("ADMIN")
                .regexMatchers("/user/*").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/emusic/users/login");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .permitAll();
    }



}
