package com.summer.melisma.config;

import com.summer.melisma.service.UserService;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                    .antMatchers("/users/create").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/").hasRole("USER")
                    .anyRequest().authenticated()      // 나머지 요청은 권한이 있으면 접근 가능
            .and()
                .formLogin()
                    .permitAll()
                    .defaultSuccessUrl("/")
            .and()
                .logout()
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)    // 세션 무효화
            ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
