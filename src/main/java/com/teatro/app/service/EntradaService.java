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
        int vendidas = repo.sumarEntradasPorEspectaculo(espectaculo.getId());
        int capacidadMaxima = espacioService.getCapacidad(espectaculo.getEspacio());

        if (vendidas + cantidad > capacidadMaxima) {
            throw new IllegalArgumentException("No hay suficientes entradas disponibles.");
        }

        int siguienteNroEntrada = vendidas + 1;

        //Calculo el precio total de la entrada
        Espacio espacio = espacioService.crearConNombre(espectaculo.getEspacio());
        double precioFinal = espacio.calcularPrecioEntrada(cantidad, tipoEntrada, espectaculo.getPrecioBase());

        Entrada entrada = new Entrada();
        entrada.setUsuario(usuario);
        entrada.setEspectaculo(espectaculo);
        entrada.setCantidadEntradas(cantidad);
        entrada.setPrecioTotal(precioFinal);
        entrada.setNroEntrada(siguienteNroEntrada);
        entrada.setTipoEntrada(tipoEntrada);

        return repo.save(entrada);
    }


}
