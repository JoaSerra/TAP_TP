package com.teatro.app.service;

import com.teatro.app.model.Anfiteatro;
import com.teatro.app.model.Espacio;
import com.teatro.app.model.Sala;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EspacioServiceTest {

    private final EspacioService service = new EspacioService();


    @Test
    void getCapacidad_sala_70() {
        // Valido que la capacidad de la sala sea 70
        int capacidad = service.getCapacidad("SALA");
        assertEquals(70, capacidad);
    }

    @Test
    void getCapacidad_anfiteatro_120() {
        // Valido que la capacidad del anfiteatro sea 120
        int capacidad = service.getCapacidad("ANFITEATRO");
        assertEquals(120, capacidad);
    }

    @Test
    void crearConNombre_sala_devuelveSalaCapacidad70() {
        // Verifico que al crear un espacio con nombre "SALA" se retorne una instancia de Sala con capacidad 70
        Espacio espacio = service.crearConNombre("SALA");
        assertInstanceOf(Sala.class, espacio);
        assertEquals(70, espacio.getCapacidad());
    }

    @Test
    void crearConNombre_anfiteatro_devuelveAnfiteatroCapacidad120() {
        // Verifico que al crear un espacio con nombre "ANFITEATRO" se retorne una instancia de Anfiteatro con capacidad 120
        Espacio espacio = service.crearConNombre("ANFITEATRO");
        assertInstanceOf(Anfiteatro.class, espacio);
        assertEquals(120, espacio.getCapacidad());
    }

    @Test
    void getCapacidad_espacioDesconocido_lanzaExcepcion() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                service.crearConNombre("OTRO")
        );
        assertTrue(e.getMessage().contains("Espacio incorrecto: OTRO"));
    }

}
