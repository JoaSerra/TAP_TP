package com.teatro.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalaTest {
    private final Sala sala = new Sala();

    @Test
    void calcularPrecioEntrada_preferencial() {
        double precio = sala.calcularPrecioEntrada(2, 1, 100);
        assertEquals(400, precio);
    }

    @Test
    void calcularPrecioEntrada_general() {
        double precio = sala.calcularPrecioEntrada(2, 2, 100);
        assertEquals(200, precio);
    }

    @Test
    void calcularPrecioEntrada_invalida() {
        double precio = sala.calcularPrecioEntrada(2, 5, 100);
        assertEquals(0, precio);
    }

}
