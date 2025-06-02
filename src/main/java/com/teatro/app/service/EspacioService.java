package com.teatro.app.service;

import com.teatro.app.model.Anfiteatro;
import com.teatro.app.model.Espacio;
import com.teatro.app.model.Sala;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Service;

@Service
@EqualsAndHashCode
public class EspacioService {
    Espacio espacio;

    public int getCapacidad(String nombre){
        espacio = crearConNombre(nombre);
        return espacio.getCapacidad();
    }

    public Espacio crearConNombre(String nombre){
        if(nombre.equalsIgnoreCase("SALA")){
            espacio = new Sala();
        }else if(nombre.equalsIgnoreCase("ANFITEATRO")){
            espacio = new Anfiteatro();
        } else {
            throw new IllegalArgumentException("Espacio incorrecto: " + nombre);
        }
        return espacio;
    }
}
