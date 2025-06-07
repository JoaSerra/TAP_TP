package com.teatro.app.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Espacio {

    private String nombre;
    private int capacidad;
    private int tipoEntrada; // 1: Preferencial, 2: General

    public abstract double calcularPrecioEntrada(int cantidadEntradas, int tipoEntrada, double precioBase);
}
