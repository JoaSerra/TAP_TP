package com.teatro.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SalaTest {
    private final Sala sala = new Sala();

    @Test
    void calcularPrecioEntrada_tipoA_doblePrecio() {
        double precio = sala.calcularPrecioEntrada(2, 1, 100);
        assertEquals(400.0, precio); // 2 x 100 x 2
    }

    @Test
    void calcularPrecioEntrada_tipoB_precioNormal() {
        double precio = sala.calcularPrecioEntrada(3, 2, 100);
        assertEquals(300.0, precio); // 3 x 100
    }

    @Test
    void calcularPrecioEntrada_tipoInvalido_retorna0() {
        double precio = sala.calcularPrecioEntrada(2, 99, 100);
        assertEquals(0.0, precio);
    }

}
