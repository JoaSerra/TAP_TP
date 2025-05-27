package com.teatro.app.model;

public abstract class Espacio {

    private String nombre;
    private int capacidad;
    private int tipoEntrada; // 0- tipo Unico, 1- tipo A, 2- tipo B

    public Espacio() {}
    public Espacio(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public abstract double calcularPrecioEntrada(int cantidadEntradas, int tipoEntrada, double precioBase);
}
