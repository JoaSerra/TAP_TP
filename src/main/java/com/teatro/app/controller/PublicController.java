package com.teatro.app.controller;

import com.teatro.app.model.User;
import com.teatro.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (!userRepository.existsByUsername(username)) {
            User nuevo = new User();
            nuevo.setUsername(username);
            nuevo.setPassword("{noop}" + password); // NOOP solo para pruebas, reemplazar por BCrypt en producción
            nuevo.setRole("USUARIO");
            userRepository.save(nuevo);
        }
        return "redirect:/login";  // o redirigirLogin()
    }

}
