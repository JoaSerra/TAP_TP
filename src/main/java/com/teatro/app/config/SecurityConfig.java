package com.teatro.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin123") // {noop} = sin encriptar
                .roles("ADMIN")
                .build();

        UserDetails usuario = User.withUsername("usuario")
                .password("{noop}user123")
                .roles("USUARIO")
                .build();

        return new InMemoryUserDetailsManager(admin, usuario);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/registro", "/registro/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/inicio", "/espectaculos/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/inicio", true) // Redirige luego de login
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                )
                //.csrf().disable()
                .csrf(csrf -> csrf.disable())
                .build();
    }
}

