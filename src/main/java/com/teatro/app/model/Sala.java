package com.teatro.app.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;


public class Sala extends Espacio{

    public Sala() {
        super();
        setNombre("SALA");
        setCapacidad(70);
    }

    @Override
    public double calcularPrecioEntrada(int cantidadEntradas, int tipoEntrada, double precioBase) {
        if (tipoEntrada == 1) //Entrada Preferencial
            return cantidadEntradas * (precioBase * 2);
        else if(tipoEntrada == 2) //Entrada general
            return (cantidadEntradas * precioBase);
        else
            return 0; // No se permite otro tipo de entrada en Sala
    }
}
