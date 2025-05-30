package com.teatro.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


public class Espectaculo {

    /*@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)*/
    private Long id;

    private String artista;
    private LocalDate fecha;
    private LocalTime hora;
    private double precio;
    private int duracionMin;
    private String tipoShow;
    private Espacio espacio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Array de espectaculos??

    public Espectaculo() {
        this.espacio = null;
    }

    public Espectaculo(String artista, LocalDate fecha, LocalTime hora, double precio, int duracionMin, String tipoShow, Espacio espacio) {
        this.artista = artista;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.duracionMin = duracionMin;
        this.tipoShow = tipoShow;
        this.espacio = espacio;
    }

    public String getArtista() {
        return artista;
    }
    public void setArtista(String artista) {
        this.artista = artista;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getDuracionMin() {
        return duracionMin;
    }
    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }
    public String getTipoShow() {
        return tipoShow;
    }
    public void setTipoShow(String tipoShow) {
        this.tipoShow = tipoShow;
    }
    public Espacio getEspacio() {
        return espacio;
    }
    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    @Override
    public String toString() {
        return "Espectaculo de '" + artista + "'" +
                "el dia " + fecha + "a las " + hora +
                "\nshow " + tipoShow + "en " + espacio.toString();
    }

    //crearEspectaculo() ????
    //validarHorario() ????????

}
