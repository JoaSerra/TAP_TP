package com.teatro.app.controller;

import com.teatro.app.model.User;
import com.teatro.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class PublicController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String redirigirLogin(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin(){
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    @GetMapping("/error")
    public String mostrarError(Model model) {
        model.addAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo más tarde.");
        return "error"; // Retorna la vista de error
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        if (!userRepository.existsByUsername(username)) {
            User nuevo = new User();
            nuevo.setUsername(username);
            nuevo.setPassword("{noop}" + password); // noop para no encriptar la contraseña
            nuevo.setRole("USUARIO");
            userRepository.save(nuevo);

            redirectAttributes.addFlashAttribute("exito", "Usuario registrado correctamente!!!");
            return "redirect:/login"; // Redirige al login después del registro exitoso
        }else{
            redirectAttributes.addFlashAttribute("error", "El nombre de usuario ya existe. Intente nuevamente");
            return "redirect:/registro";
        }
    }



}
