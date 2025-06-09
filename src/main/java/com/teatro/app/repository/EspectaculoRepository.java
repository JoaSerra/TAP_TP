package com.teatro.app.repository;

import com.teatro.app.model.Espectaculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface EspectaculoRepository extends JpaRepository<Espectaculo, Long> {

    List<Espectaculo> findByFechaAfter(LocalDate fecha);
    List<Espectaculo> findByFechaBefore(LocalDate fecha);
    List<Espectaculo> findByFecha(LocalDate fecha);

    List<Espectaculo> findByEspacioAndFecha(String espacio, LocalDate fecha);

    /*Hibernate genera la query automaticamente y devuelve si existe un espectaculo en ese espacio en esa fecha entre esas horas */
    boolean existsByEspacioAndFechaAndHoraBetween(String espacio, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin);


}
