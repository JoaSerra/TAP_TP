package com.teatro.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String redirigirLogin(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin(Authentication auth){
        if(auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
            return "admin";
        } else if(auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))){
            return "user";
        } else {
            return "loginError";
        }
    }

}
