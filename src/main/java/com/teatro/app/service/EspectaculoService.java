package com.teatro.app.service;

import com.teatro.app.model.Espectaculo;
import com.teatro.app.repository.EspectaculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EspectaculoService {

    private final EspectaculoRepository repo;

    public List<Espectaculo> findAll() {
        return repo.findAll();
    }
    public Espectaculo findById(Long id) {
        //Si el id no existe, devuelve null
        return repo.findById(id).orElse(null);
    }
    public Espectaculo save(Espectaculo espectaculo) {
        return repo.save(espectaculo);
    }


    public boolean puedeGuardar(Espectaculo nuevo){
        LocalTime horaInicio = nuevo.getHora().minusHours(1);
        LocalTime horaFin = nuevo.getHora().plusMinutes(nuevo.getDuracionMin()).plusHours(1);
        //Verifica si hay superposicion de horarios
        return !repo.existsByEspacioAndFechaAndHoraBetween(nuevo.getEspacio(), nuevo.getFecha(), horaInicio, horaFin);
        //Si no hay superposicion devuelve true
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
