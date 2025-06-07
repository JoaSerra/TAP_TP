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
        // Arrange
        User usuario = new User();
        Espectaculo espectaculo = new Espectaculo();
        espectaculo.setId(1L);
        espectaculo.setEspacio("SALA");
        espectaculo.setPrecioBase(100);
        espectaculo.setFecha(LocalDate.of(2025, 6, 15));
        espectaculo.setHora(LocalTime.of(20, 0));
        espectaculo.setDuracionMin(90);

        int cantidad = 2;
        int tipoEntrada = 2; // tipo B (precio base)

        // Simulamos que se vendieron 0 entradas
        Mockito.when(repo.sumarEntradasPorEspectaculo(1L)).thenReturn(0);
        // La sala tiene capacidad para 70
        Mockito.when(espacioService.getCapacidad("SALA")).thenReturn(70);
        // Usamos una instancia real de Sala para calcular el precio
        Mockito.when(espacioService.crearConNombre("SALA")).thenReturn(new Sala());

        // Simulamos que el repo devuelve la entrada que guardó
        Mockito.when(repo.save(Mockito.any(Entrada.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        // Act
        Entrada entrada = service.comprarEntradas(usuario, espectaculo, cantidad, tipoEntrada);

        // Assert
        assertNotNull(entrada);
        assertEquals(usuario, entrada.getUsuario());
        assertEquals(espectaculo, entrada.getEspectaculo());
        assertEquals(cantidad, entrada.getCantidadEntradas());
        assertEquals(200.0, entrada.getPrecioTotal()); // 2 x 100
    }

    @Test
    void comprarEntradas_conCapacidadExcedida_lanzaExcepcion() {
        // Arrange
        Espectaculo espectaculo = new Espectaculo();
        espectaculo.setId(1L);
        espectaculo.setEspacio("SALA");
        espectaculo.setPrecioBase(100);

        int cantidad = 5;

        Mockito.when(repo.sumarEntradasPorEspectaculo(1L)).thenReturn(68); // ya casi lleno
        Mockito.when(espacioService.getCapacidad("SALA")).thenReturn(70);

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                service.comprarEntradas(new User(), espectaculo, cantidad, 2)
        );
        assertEquals("No hay suficientes entradas disponibles.", e.getMessage());
    }

}
