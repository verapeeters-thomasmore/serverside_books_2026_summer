package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.dto.AuthorDTO;
import be.thomasmore.bookserver.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Operation(summary = "find all the authors that are stored in the database ")
    @GetMapping("")
    public Iterable<AuthorDTO> findAll() {
        log.info("##### findAll authors");
        return authorService.findAll();
    }
}
