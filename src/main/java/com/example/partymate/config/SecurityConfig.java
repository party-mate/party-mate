package com.example.partymate.config;

import com.example.partymate.config.auth.AuthenticationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author JJDabean
 * @since  2023-11-08
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        setLocalMode(http);
        http.formLogin().defaultSuccessUrl("/",true);
        http.authorizeRequests().anyRequest().authenticated();
        return http.build();
    }

    private void setLocalMode(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/api/post/intro/**", "/me", "/login/**", "/api/member/**", "/emails/verification-requests", "/join/**", "/signup/**","/loginForm/**", "/joinForm/**", "/api/user", "/api/user/**", "/signup/**", "/js/**", "/css/**", "/image/**", "/fonts/**", "/favicon.ico").permitAll()
                .antMatchers("/api/**").hasAnyAuthority("USER")
                .and().formLogin().loginPage("/loginForm").loginProcessingUrl("/login").defaultSuccessUrl("/")
                .and().headers().frameOptions().sameOrigin()
                .and().csrf().disable();
    }

}
