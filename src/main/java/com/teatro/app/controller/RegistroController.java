package com.teatro.app.controller;

import com.teatro.app.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String mostrarFormulario() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombre, @RequestParam String contrasena) {
        usuarioService.crearUsuario(nombre, contrasena);
        return "redirect:/login";
    }

}
