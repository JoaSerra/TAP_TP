package com.teatro.app.controller;

import com.teatro.app.model.Espectaculo;
import com.teatro.app.service.EspectaculoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EspectaculoController.class)
public class EspectaculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EspectaculoService espectaculoService;

    @Test
    @DisplayName("GET /espectaculo/home muestra lista de espectáculos")
    @WithMockUser
    void mostrarHome_muestraEspectaculos() throws Exception {
        Espectaculo esp = new Espectaculo();
        esp.setId(1L);
        esp.setArtista("Show Test");
        esp.setFecha(LocalDate.now());
        Mockito.when(espectaculoService.findAll()).thenReturn(List.of(esp));

        mockMvc.perform(get("/espectaculo/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("espectaculos"))
                .andExpect(model().attributeExists("hoy"));
    }

    @Test
    @DisplayName("GET /espectaculo/cargar muestra el formulario")
    @WithMockUser(authorities = "ROLE_ADMIN")
    void mostrarCarga_paraAdmin() throws Exception {
        mockMvc.perform(get("/espectaculo/cargar"))
                .andExpect(status().isOk())
                .andExpect(view().name("carga_espectaculo"))
                .andExpect(model().attributeExists("espectaculo"));
    }

    @Test
    @DisplayName("POST /espectaculo/cargar guarda espectáculo si no hay conflicto")
    @WithMockUser(authorities = "ROLE_ADMIN")
    void cargarEspectaculo_exitoso() throws Exception {
        Mockito.when(espectaculoService.puedeGuardar(Mockito.any())).thenReturn(true);

        mockMvc.perform(post("/espectaculo/cargar")
                        .param("artista", "Banda")
                        .param("fecha", "2025-07-01")
                        .param("hora", "21:00")
                        .param("precioBase", "150")
                        .param("duracionMin", "90")
                        .param("espacio", "SALA")
                        .param("tipoShow", "musical"))
                .andExpect(status().isOk())
                .andExpect(view().name("carga_espectaculo"))
                .andExpect(model().attributeExists("exito"));
    }

    @Test
    @DisplayName("GET /espectaculo/{id} muestra detalles si existe")
    @WithMockUser
    void verEspectaculo_existente() throws Exception {
        Espectaculo esp = new Espectaculo();
        esp.setId(1L);
        esp.setArtista("Detalle Test");

        Mockito.when(espectaculoService.findById(1L)).thenReturn(esp);

        mockMvc.perform(get("/espectaculo/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("detalle_espectaculo"))
                .andExpect(model().attributeExists("espectaculo"));
    }

}
