package org.prezydium.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder authManager) throws Exception {
        authManager
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin1"))
                .roles("ADMIN", "USER")
                .and()
                .withUser("kristoff")
                .password(passwordEncoder().encode("abc"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin-secure").hasRole("ADMIN")
                .antMatchers("/user-secure").hasRole("USER")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .successForwardUrl("/secure");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
