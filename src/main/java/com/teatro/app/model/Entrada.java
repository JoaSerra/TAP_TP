package com.teatro.app.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entradas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder //Me permite instanciar el objeto mas facilmente
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nroEntrada;
    private int cantidadEntradas;
    private double precioTotal;

    @ManyToOne
    @JoinColumn(name = "espectaculo_id")
    private Espectaculo espectaculo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;



}
