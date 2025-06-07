package com.teatro.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth /*Defino quien tiene acceso a los endpoints*/
                        /*Permite el acceso a usuarios no autenticados a las paginas de login y registro (/ redirige a /login)*/
                        .requestMatchers("/", "/login", "/registro", "/css/**").permitAll()
                        .requestMatchers("/espectaculo/**", "/entrada/**").authenticated() /*Requiere autenticacion para acceder a estos endpoints*/
                        .requestMatchers("/espectaculo/cargar").hasAuthority("ROLE_ADMIN").anyRequest().authenticated() /*Solo los administradores pueden acceder a /cargar*/
                )
                .formLogin(login -> login
                        .loginPage("/login") /*Utiliza el template login.html*/
                        .defaultSuccessUrl("/espectaculo/home", true) /*Redirige a home si hay login exitoso*/
                        .failureUrl("/login?error") /*Redirige a login con error si falla el login*/
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                )
                .build();
    }
}

