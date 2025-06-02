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
    private int tipoEntrada; // 0- tipo Unico, 1- tipo A, 2- tipo B

    public abstract double calcularPrecioEntrada(int cantidadEntradas, int tipoEntrada, double precioBase);
}
