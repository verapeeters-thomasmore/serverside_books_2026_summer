package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.User;
import be.thomasmore.bookserver.model.dto.AuthenticationDTO;
import be.thomasmore.bookserver.model.dto.UserDTO;
import be.thomasmore.bookserver.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

/**
 * REST controller for authentication operations.
 * Uses constructor injection (Spring best practice since Spring 4.3).
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class AuthenticationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/authenticate")
    public AuthenticationDTO authenticate(Principal principal) {
        log.info("##### authenticate");
        String username = principal != null ? principal.getName() : "anonymous";
        return new AuthenticationDTO(username);
    }

    @PostMapping("/signup")
    public AuthenticationDTO signup(@RequestBody UserDTO userDTO) {
        log.info("##### signup {}", userDTO.username());

        if (userRepository.findByUsername(userDTO.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "User with name %s already exists.".formatted(userDTO.username()));
        }

        User newUser = new User();
        newUser.setUsername(userDTO.username());
        newUser.setRole("USER");
        newUser.setPassword(passwordEncoder.encode(userDTO.password()));
        User savedUser = userRepository.save(newUser);

        autologin(userDTO.username(), userDTO.password());

        return new AuthenticationDTO(savedUser.getUsername());
    }

    private void autologin(String userName, String password) {
        var token = new UsernamePasswordAuthenticationToken(userName, password);

        try {
            Authentication auth = authenticationManager.authenticate(token);
            log.info("authentication: {}", auth.isAuthenticated());

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
        } catch (AuthenticationException e) {
            log.error("Auto-login failed for user: {}", userName, e);
        }
    }
}
