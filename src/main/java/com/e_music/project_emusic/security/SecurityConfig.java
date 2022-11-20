package com.e_music.project_emusic.security;

import com.e_music.project_emusic.config.CustomAccessDeniedHandled;
import com.e_music.project_emusic.security.service.ServiceMyUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ServiceMyUserDetailsImpl serviceMyUserDetails;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandled();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(serviceMyUserDetails).passwordEncoder(passwordEncoder());
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/images/**", "/", "/index", "/error", "/forbidden", "/users/login" , "/users/register", "/users/about", "/instruments/list/{id}","/instruments/details/{id}", "/instruments/listAllInstruments**", "/instruments/list/**") //Url permitidas
                .permitAll()
                .antMatchers("/users/register") //Url de post para registro de usuarios
                .permitAll()
                .anyRequest().authenticated() //resto de url se tienen que autenticar
                .and()
                .formLogin()
                .loginProcessingUrl("/users/login") // Url de post
                .loginPage("/users/login").permitAll() //Url del formulario para login de usuarios
                .defaultSuccessUrl( "/", true) //Url de redirect si login ok
                .usernameParameter("username") //nombre del atributo de username del form login
                .passwordParameter("password") //nombre del atributo de password del form login
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler()) //manejo de excepcion
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//logout
                .logoutSuccessUrl("/").permitAll()
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe().tokenValiditySeconds(3600000).key("secret").rememberMeParameter("checkRememberMe");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .permitAll();
    }
}
