package com.teatro.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnfiteatroTest {

    private final Anfiteatro anfiteatro = new Anfiteatro();

    @Test
    void calcularPrecioEntrada_precioUnico() {
        double precio = anfiteatro.calcularPrecioEntrada(4, 0, 150);
        assertEquals(600.0, precio); // 4 x 150
    }

    @Test
    void calcularPrecioEntrada_tipoIgnorado() {
        double precio = anfiteatro.calcularPrecioEntrada(1, 2, 200);
        assertEquals(200.0, precio); // tipo no importa
    }
}
