package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.dto.AuthenticationDTO;
import be.thomasmore.bookserver.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
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

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(JdbcTemplate jdbcTemplate,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager) {
        this.jdbcTemplate = jdbcTemplate;
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

        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from users where username = ?", Integer.class, userDTO.username());
        if (count != null && count > 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "User with name %s already exists.".formatted(userDTO.username()));
        }

        jdbcTemplate.update("insert into users (username, password, enabled) values (?, ?, ?)",
                userDTO.username(), passwordEncoder.encode(userDTO.password()), true);
        jdbcTemplate.update("insert into authorities (username, authority) values (?, ?)",
                userDTO.username(), "USER");

        autologin(userDTO.username(), userDTO.password());

        return new AuthenticationDTO(userDTO.username());
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
