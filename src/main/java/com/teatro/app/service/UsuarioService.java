package com.teatro.app.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final InMemoryUserDetailsManager userDetailsManager;

    public UsuarioService(InMemoryUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public void crearUsuario(String nombre, String contrasena) {
        UserDetails nuevo = User.withUsername(nombre)
                .password("{noop}" + contrasena)
                .roles("USUARIO") // Solo crea usuarios normales
                .build();
        userDetailsManager.createUser(nuevo);
    }

    public void crearAdministrador(String nombre, String contrasena) {
        UserDetails nuevo = User.withUsername(nombre)
                .password("{noop}" + contrasena)
                .roles("ADMIN")
                .build();
        userDetailsManager.createUser(nuevo);
    }
}
