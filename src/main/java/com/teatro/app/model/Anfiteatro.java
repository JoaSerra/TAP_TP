package com.teatro.app.model;


public class Anfiteatro extends Espacio{

    public Anfiteatro(){
        super();
        setNombre("ANFITEATRO");
        setCapacidad(120);
    }

    @Override
    public double calcularPrecioEntrada(int cantidadEntradas, int tipoEntrada, double precioBase) {
        if(tipoEntrada == 0){ // Entrada Unica
            return cantidadEntradas * precioBase;
        }else
            return 0; // No se permite otro tipo de entrada en Anfiteatro

    }
}
