package com.teatro.app.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entradas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nroEntrada;
    private int cantidadEntradas;
    private double precioTotal;
    private int tipoEntrada; // 1: Preferencial 2: General

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "espectaculo_id", nullable = false)
    private Espectaculo espectaculo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

}
