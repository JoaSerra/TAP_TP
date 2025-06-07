package com.teatro.app.repository;

import com.teatro.app.model.Entrada;
import com.teatro.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada,Long> {

    Entrada findById(long id);
    List<Entrada> findByEspectaculoId(long espectaculoId);
    List<Entrada> findByUsuario(User usuario);

    //Devuelve la cantidad de entradas vendidas para un espectaculo
    int countByEspectaculoId(long espectaculoId);

    // COALENCE se usa para evitar que la suma sea null si no hay entradas vendidas
    //Devuelve la suma de entradas vendidas para un espectaculo, o 0 si no hay entradas
    @Query("SELECT COALESCE(SUM(e.cantidadEntradas), 0) FROM Entrada e WHERE e.espectaculo.id = :espectaculoId")
    int sumarEntradasPorEspectaculo(long espectaculoId);
}
