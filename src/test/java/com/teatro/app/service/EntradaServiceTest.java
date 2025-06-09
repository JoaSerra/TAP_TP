package com.teatro.app.service;

import com.teatro.app.model.Entrada;
import com.teatro.app.model.Espectaculo;
import com.teatro.app.model.Sala;
import com.teatro.app.model.User;
import com.teatro.app.repository.EntradaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EntradaServiceTest {
    @Mock
    private EntradaRepository repo;

    @Mock
    private EspacioService espacioService;

    @InjectMocks
    private EntradaService service;

    @Test
    void comprarEntradas_conEspacioDisponible_retornaEntrada() {
        // creo entrada de prueba
        User usuario = new User();
        Espectaculo espectaculo = new Espectaculo();
        espectaculo.setId(1L);
        espectaculo.setEspacio("SALA");
        espectaculo.setPrecioBase(100);
        espectaculo.setFecha(LocalDate.of(2025, 6, 15));
        espectaculo.setHora(LocalTime.of(20, 0));
        espectaculo.setDuracionMin(90);

        int cantidad = 2;
        int tipoEntrada = 2; // entrada general

        // simulo que se vendieron 0 entradas
        Mockito.when(repo.sumarEntradasPorEspectaculo(1L)).thenReturn(0);
        // la sala tiene capacidad para 70
        Mockito.when(espacioService.getCapacidad("SALA")).thenReturn(70);
        // obtengo una instancia de Sala
        Mockito.when(espacioService.crearConNombre("SALA")).thenReturn(new Sala());
        // simulo que el repo devuelve la entrada que guardó
        Mockito.when(repo.save(Mockito.any(Entrada.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        // pruebo la compra de entradas
        Entrada entrada = service.comprarEntradas(usuario, espectaculo, cantidad, tipoEntrada);

        // los resultados esperados
        assertNotNull(entrada);
        assertEquals(usuario, entrada.getUsuario());
        assertEquals(espectaculo, entrada.getEspectaculo());
        assertEquals(cantidad, entrada.getCantidadEntradas());
        assertEquals(200.0, entrada.getPrecioTotal());
    }

    @Test
    void comprarEntradas_conCapacidadExcedida_lanzaExcepcion() {
        Espectaculo espectaculo = new Espectaculo();
        espectaculo.setId(1L);
        espectaculo.setEspacio("SALA");
        espectaculo.setPrecioBase(100);

        int cantidad = 5;

        Mockito.when(repo.sumarEntradasPorEspectaculo(1L)).thenReturn(68); // ya casi lleno
        Mockito.when(espacioService.getCapacidad("SALA")).thenReturn(70);

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                service.comprarEntradas(new User(), espectaculo, cantidad, 2)
        );
        assertEquals("No hay suficientes entradas disponibles.", e.getMessage());
    }

}
