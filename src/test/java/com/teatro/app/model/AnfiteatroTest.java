package com.teatro.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnfiteatroTest {

    private final Anfiteatro anfiteatro = new Anfiteatro();

    @Test
    void calcularPrecioEntrada_general() {
        double precio = anfiteatro.calcularPrecioEntrada(2, 2, 100);
        assertEquals(200, precio);
    }

    @Test
    void calcularPrecioEntrada_invalida() {
        double precio = anfiteatro.calcularPrecioEntrada(2, 5, 100);
        assertEquals(0, precio);
    }
}
