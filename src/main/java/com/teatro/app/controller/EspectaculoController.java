package com.teatro.app.controller;

import com.teatro.app.model.Espectaculo;
import com.teatro.app.service.EspectaculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/espectaculo")
@RequiredArgsConstructor
public class EspectaculoController {

    private final EspectaculoService espectaculoService;

    @GetMapping("/home")
    public String mostrarHome(Model model) {
        List<Espectaculo> espectaculos = espectaculoService.findAll();
        model.addAttribute("espectaculos", espectaculos);
        model.addAttribute("hoy", LocalDate.now());
        //El model me sirve para pasar datos al html y los recibo con Thimeleaf

        return "home"; // home.html
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //Solo el admin puede cargar espectaculos
    @GetMapping("/cargar")
    public String mostrarCarga(Model model) {
        model.addAttribute("espectaculo", new Espectaculo());

        return "carga_espectaculo"; // carga_espectaculo.html
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/cargar")
    public String cargarEspectaculo(@ModelAttribute Espectaculo espectaculo, Model model) {
        // Vuelve al formulario con un mensaje de error
        if (espectaculoService.puedeGuardar(espectaculo)) {
            espectaculoService.save(espectaculo);
            model.addAttribute("exito", "Espectáculo cargado correctamente.");
        } else {
            model.addAttribute("error", "El horario del espectáculo se superpone con otro ya existente.");
        }
        return "carga_espectaculo"; // Vuelve al formulario con el mensaje (exito o error)
    }

    @GetMapping("/{id}")
    public String verEspectaculo(@PathVariable Long id, Model model) {
        Espectaculo espectaculo = espectaculoService.findById(id);
        if (espectaculo == null) {
            model.addAttribute("error", "Espectáculo no encontrado.");
            return "redirect:/espectaculo/home"; // Redirige a home si no se encuentra el espectáculo
        }
        model.addAttribute("espectaculo", espectaculo);
        return "detalle_espectaculo"; // detalle_espectaculo.html
    }
}
