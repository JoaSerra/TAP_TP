package com.teatro.app.model;

public class Entrada {

    private int nroEntrada;
    private Espectaculo espectaculo;
    private Espacio espacio;
    private User user;
    private double precio;

    public Entrada(){
        //this.nroEntrada = ; springBoot numero automatico

    }
    public Entrada(int nroEntrada, Espectaculo espectaculo, double precio){
        this.nroEntrada = nroEntrada;
        this.espectaculo = espectaculo;
        this.precio = precio;
    }

    public int getNroEntrada() {
        return nroEntrada;
    }
    public void setNroEntrada(int nroEntrada) {
        this.nroEntrada = nroEntrada;
    }
    public Espectaculo getEspectaculo() {
        return espectaculo;
    }
    public void setEspectaculo(Espectaculo espectaculo) {
        this.espectaculo = espectaculo;
    }
    public User getUsuario() {
        return user;
    }
    public void setUsuario(User user) {
        this.user = user;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public Espacio getEspacio() {
        return espacio;
    }
    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    //reporteEntradas

}
