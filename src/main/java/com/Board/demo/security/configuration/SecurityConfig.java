package com.Board.demo.security.configuration;

import com.Board.demo.handler.LoginFailManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/","/login", "/signup", "/error", "/articles", "/article/**")
                        .permitAll()
                        .anyRequest().authenticated()
                );

        httpSecurity
                .formLogin((auth)->auth.loginPage("/login")
                                .failureHandler(loginFailManager())
                                .permitAll()
                );

        httpSecurity
                .logout((auth)->auth.logoutUrl("/logout")
                        .addLogoutHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession(false);
                            if(session != null){
                                session.invalidate();
                            }
                        })
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.sendRedirect("/");
                        })
                        .deleteCookies("JSESSIONID"));

        httpSecurity
                .csrf((auth)->auth.disable()
                );

        httpSecurity
                .sessionManagement((auth)->auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                );

        httpSecurity
                .sessionManagement((auth)->auth
                        .sessionFixation().changeSessionId()
                );

        return httpSecurity.build();
    }


    @Bean
    public LoginFailManager loginFailManager(){
        return new LoginFailManager();
    }
}