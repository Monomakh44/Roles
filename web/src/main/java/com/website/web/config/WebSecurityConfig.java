package com.website.web.config;

import com.website.web.services.implementation.CustomUsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUsersDetailsService usersDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] staticResources = {
                "/css/**",
                "/js/daypilot/**",
                "/icons/**",
                "/images/**"
        };
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(staticResources).permitAll()
                    .antMatchers("/pay", "/users/**").hasAuthority("ADMIN")
                    .antMatchers("/info", "/contact").permitAll()
                    .antMatchers("/entrance", "/registration").not().fullyAuthenticated()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/entrance")
                    .defaultSuccessUrl("/profile")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/home");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}