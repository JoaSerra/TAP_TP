package com.teatro.app.service.impl;


import com.teatro.app.model.security.SecurityUser;
import com.teatro.app.model.User;
import com.teatro.app.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new SecurityUser(user);
    }

    @PostConstruct
    public void crearAdminPorDefecto() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("{noop}admin"); // noop para no encriptar la contraseña
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }

    @PostConstruct
    public void crearUserPorDefecto() {
        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword("{noop}user"); // noop para no encriptar la contraseña
            user.setRole("USER");
            userRepository.save(user);
        }

    }

}
