package com.e_music.project_emusic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        User.UserBuilder usuarios = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(usuarios.username("Asiel").password("123").roles("Admin"))
                .withUser(usuarios.username("Alejo").password("456").roles("Admin"))
                .withUser(usuarios.username("Rodrigo").password("789").roles("Admin"));
    }

    //@Override
    //protected void configure(HttpSecurity http) throws Exception{
    //    http.authorizeRequests().anyRequest().authenticated().and().formLogin()
    //            .loginPage("/login")
    //            .loginProcessingUrl("/loginUser")
    //            .permitAll();


    //}
}
