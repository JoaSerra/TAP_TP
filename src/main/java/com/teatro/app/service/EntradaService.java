package com.teatro.app.service;

import com.teatro.app.model.Entrada;
import com.teatro.app.model.Espacio;
import com.teatro.app.model.Espectaculo;
import com.teatro.app.model.User;
import com.teatro.app.repository.EntradaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntradaService {

    private final EntradaRepository repo;
    private final EspacioService espacioService;

    public Entrada findById(long id) {
        return repo.findById(id);
    }

    public List<Entrada> findAll() {
        return repo.findAll();
    }

    public List<Entrada> findByEspectaculoId(long espectaculoId) {
        return repo.findByEspectaculoId(espectaculoId);
    }

    public List<Entrada> findByUsuario(User usuario) {
        return repo.findByUsuario(usuario);
    }

    public int countByEspectaculoId(long espectaculoId) {
        return repo.countByEspectaculoId(espectaculoId);
    }

    public Entrada save(Entrada entrada) {
        return repo.save(entrada);
    }

    public Entrada comprarEntradas(User usuario, Espectaculo espectaculo, int cantidad, int tipoEntrada) {

        //Validar la capacidad
        int vendidas = repo.countByEspectaculoId(espectaculo.getId());
        int capacidadMaxima = espacioService.getCapacidad(espectaculo.getEspacio());

        if (vendidas + cantidad > capacidadMaxima) {
            throw new IllegalArgumentException("No hay suficientes entradas disponibles.");
        }

        //Calculo el precio total de la entrada
        Espacio espacio = espacioService.crearConNombre(espectaculo.getEspacio());
        double precioFinal = espacio.calcularPrecioEntrada(cantidad, tipoEntrada, espectaculo.getPrecioBase());

        //Creo la entrada con el builder
        Entrada entrada = Entrada.builder()
                .usuario(usuario)
                .espectaculo(espectaculo)
                .cantidadEntradas(cantidad)
                .precioTotal(precioFinal)
                .build();

        return repo.save(entrada);
    }


}
