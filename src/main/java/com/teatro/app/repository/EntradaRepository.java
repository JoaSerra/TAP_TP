package com.teatro.app.repository;

import com.teatro.app.model.Entrada;
import com.teatro.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada,Long> {

    Entrada findById(long id);
    List<Entrada> findByEspectaculoId(long espectaculoId);
    List<Entrada> findByUsuario(User usuario);

    //Devuelve la cantidad de entradas vendidas para un espectaculo
    int countByEspectaculoId(long espectaculoId);

}
