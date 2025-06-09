package com.teatro.app.controller;

import com.teatro.app.model.Espectaculo;
import com.teatro.app.model.User;
import com.teatro.app.service.EntradaService;
import com.teatro.app.service.EspectaculoService;
import com.teatro.app.service.EspacioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EntradaController.class)
public class EntradaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EntradaService entradaService;

    @InjectMocks
    private EspectaculoService espectaculoService;

    @InjectMocks
    private EspacioService espacioService;

    @Test
    @DisplayName("GET /entrada/mis-entradas muestra entradas del usuario")
    @WithMockUser(username = "juan", authorities = "ROLE_USUARIO")
    void verEntradas_devuelveLista() throws Exception {
        Mockito.when(entradaService.findByUsuario(Mockito.any())).thenReturn(List.of());

        mockMvc.perform(get("/entrada/mis-entradas"))
                .andExpect(status().isOk())
                .andExpect(view().name("entradas"))
                .andExpect(model().attributeExists("entradas"))
                .andExpect(model().attributeExists("hoy"));
    }

    @Test
    @DisplayName("GET /entrada/comprar con ID válido muestra formulario")
    @WithMockUser
    void mostrarCompra_muestraVistaConEspectaculo() throws Exception {
        Espectaculo esp = new Espectaculo();
        esp.setId(1L);
        esp.setArtista("Compra Test");

        Mockito.when(espectaculoService.findById(1L)).thenReturn(esp);

        mockMvc.perform(get("/entrada/comprar")
                        .param("espectaculoId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("compra_entrada"))
                .andExpect(model().attributeExists("espectaculo"));
    }

    @Test
    @DisplayName("POST /entrada/comprar con datos válidos procesa compra")
    @WithMockUser
    void comprarEntrada_valida_exito() throws Exception {
        Espectaculo esp = new Espectaculo();
        esp.setId(1L);
        Mockito.when(espectaculoService.findById(1L)).thenReturn(esp);

        mockMvc.perform(post("/entrada/comprar")
                        .param("espectaculoId", "1")
                        .param("cantidad", "2")
                        .param("tipoEntrada", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("compra_entrada"))
                .andExpect(model().attributeExists("exito"));
    }
}
