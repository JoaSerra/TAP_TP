package com.teatro.app.controller;

import com.teatro.app.model.Entrada;
import com.teatro.app.model.Espectaculo;
import com.teatro.app.model.SecurityUser;
import com.teatro.app.model.User;
import com.teatro.app.service.EntradaService;
import com.teatro.app.service.EspacioService;
import com.teatro.app.service.EspectaculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/entrada")
@RequiredArgsConstructor
public class EntradaController {
    private final EntradaService entradaService;
    private final EspectaculoService espectaculoService;
    private final EspacioService espacioService;

    @GetMapping("/mis-entradas")
    public String verEntradas(@AuthenticationPrincipal SecurityUser userDetails, Model model) {
        User usuario = userDetails.getUser(); // Obtiene el usuario autenticado
        List<Entrada> entradas = entradaService.findByUsuario(usuario);
        model.addAttribute("entradas", entradas);
        model.addAttribute("hoy", LocalDate.now());

        return "entradas"; // entradas.html
    }

    @GetMapping("/comprar")
    public String mostrarCompra(@RequestParam("espectaculoId") Long espectaculoId,
                                Model model) {
        Espectaculo espectaculo = espectaculoService.findById(espectaculoId);
        if(espectaculo == null) {
            model.addAttribute("error", "Espectáculo no encontrado.");
            return "redirect:/espectaculo/home"; // Redirige a home si no se encuentra el espectáculo
        }
        model.addAttribute("espectaculo", espectaculo);
        return "compra_entrada"; // compra_entrada.html
    }

    @PostMapping ("/comprar") // POST desde "compra_entrada.html"
    public String comprarEntrada(@RequestParam("espectaculoId") Long espectaculoId,
                                 @RequestParam("cantidad") int cantidad,
                                 @RequestParam("tipoEntrada") int tipoEntrada,
                                 @AuthenticationPrincipal SecurityUser userDetails,
                                 Model model) {
        User usuario = userDetails.getUser(); // Obtiene el usuario autenticado
        Espectaculo espectaculo = espectaculoService.findById(espectaculoId);
        if (espectaculo == null) {
            model.addAttribute("error", "Espectáculo no encontrado.");
            return "redirect:/espectaculo/home"; // Redirige a home si no se encuentra el espectáculo aunque directamente no lo mostraría
        }
        try {
            Entrada entrada = entradaService.comprarEntradas(usuario, espectaculo, cantidad, tipoEntrada);
            model.addAttribute("exito", "Compra realizada con éxito.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("espectaculo", espectaculo);
        return "compra_entrada"; // Vuelve a la página de compra con mensaje de éxito o error
    }
}
