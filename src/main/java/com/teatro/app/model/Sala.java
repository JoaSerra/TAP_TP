package com.teatro.app.model;

public class Sala extends Espacio{

    public Sala() {
        super();
        setCapacidad(70);
    }

    @Override
    public double calcularPrecioEntrada(int cantidadEntradas, int tipoEntrada, double precioBase) {
        double precioTotal = 0;
        precioTotal = cantidadEntradas * precioBase;
        if (tipoEntrada == 1) //Entrada tipo A
            return precioTotal * 2;
        else if(tipoEntrada == 2) //Entrada tipo B
            return precioTotal;
        else
            return 0; // No se permite otro tipo de entrada en Sala
    }
}
