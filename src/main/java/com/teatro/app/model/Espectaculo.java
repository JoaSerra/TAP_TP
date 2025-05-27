package com.teatro.app.model;

import jakarta.persistence.Entity;


public class Espectaculo {

    private Espacio espacio;

    public Espectaculo() {
        this.espacio = null;
    }
    public Espectaculo(Espacio espacio) {
        this.espacio = espacio;
    }
    public Espacio getEspacio() {
        return espacio;
    }
    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }



}
