package com.teatro.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "espectaculos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Espectaculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String artista;
    private LocalDate fecha;
    private LocalTime hora;
    private double precioBase;
    private int duracionMin;
    private String tipoShow;
    private String espacio;


    @Override
    public String toString() {
        return "Espectaculo de '" + artista + "'" +
                "el dia " + fecha + "a las " + hora +
                "\nshow " + tipoShow + "en " + espacio;
    }

}
