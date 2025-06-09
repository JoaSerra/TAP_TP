package com.teatro.app.service;

import com.teatro.app.model.Espectaculo;
import com.teatro.app.repository.EspectaculoRepository;
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
public class EspectaculoServiceTest {

    @Mock
    private EspectaculoRepository repo; // Simula el repositorio

    @InjectMocks
    private EspectaculoService service; // Inyecta el repo simulado en el servicio real

    @Test
    void puedeGuardar_sinSuperposicion_retornaTrue() {
        // creo un espectáculo de prueba
        Espectaculo espectaculo = new Espectaculo();
        espectaculo.setEspacio("SALA");
        espectaculo.setFecha(LocalDate.of(2025, 6, 10));
        espectaculo.setHora(LocalTime.of(18, 0));
        espectaculo.setDuracionMin(90);

        // simulo que el repo dice que NO hay superposición
        Mockito.when(repo.existsByEspacioAndFechaAndHoraBetween(
                Mockito.eq("SALA"),
                Mockito.eq(espectaculo.getFecha()),
                Mockito.any(LocalTime.class),
                Mockito.any(LocalTime.class)
        )).thenReturn(false);

        // llamo al metodo para comprobar
        boolean resultado = service.puedeGuardar(espectaculo);
        // verifico que el resultado sea true
        assertTrue(resultado);
    }

    @Test
    void puedeGuardar_conSuperposicion_retornaFalse() {
        Espectaculo espectaculo = new Espectaculo();
        espectaculo.setEspacio("SALA");
        espectaculo.setFecha(LocalDate.of(2025, 6, 10));
        espectaculo.setHora(LocalTime.of(18, 0));
        espectaculo.setDuracionMin(90);

        // simulo que el repo dice que SI hay superposición
        Mockito.when(repo.existsByEspacioAndFechaAndHoraBetween(
                Mockito.eq("SALA"),
                Mockito.eq(espectaculo.getFecha()),
                Mockito.any(LocalTime.class),
                Mockito.any(LocalTime.class)
        )).thenReturn(true);

        boolean resultado = service.puedeGuardar(espectaculo);
        assertFalse(resultado);
    }
}
