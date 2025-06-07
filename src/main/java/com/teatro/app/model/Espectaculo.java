package com.teatro.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    // Lo utilizo para que al eliminar un espectaculo tambien se eliminen todas las entradas asociadas y no haya problemas
    @OneToMany(mappedBy = "espectaculo", cascade = CascadeType.ALL)
    private List<Entrada> entradas;

}
