package com.teatro.app.controller;

import com.teatro.app.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PublicController.class)
public class PublicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("GET /login muestra login.html")
    void mostrarLogin_devuelveLoginHtml() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(content().string(containsString("Iniciar sesión")));
    }

    @Test
    @DisplayName("GET /registro muestra registro.html")
    void mostrarRegistro_devuelveRegistroHtml() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registro"))
                .andExpect(status().isOk())
                .andExpect(view().name("registro"))
                .andExpect(content().string(containsString("Registrar un nuevo usuario")));
    }

    @Test
    @DisplayName("POST /registro crea usuario y redirige al login")
    void registrarUsuario_redirigeALogin() throws Exception {
        // simulo que no existe ese usuario
        Mockito.when(userRepository.existsByUsername("nuevo")).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/registro")
                        .param("username", "nuevo")
                        .param("password", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @DisplayName("POST /registro con usuario existente no redirige al login")
    void registrarUsuario_existente_noRedirigeALogin() throws Exception {
        // Simulamos que el usuario ya existe
        Mockito.when(userRepository.existsByUsername("existente")).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/registro")
                        .param("username", "existente")
                        .param("password", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("registro"))
                .andExpect(content().string(containsString("ya existe")));
    }
}
