package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.dto.AuthorDTO;
import be.thomasmore.bookserver.model.dto.AuthorDetailedDTO;
import be.thomasmore.bookserver.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "get 1 author from the database.",
            description = "Author with id is fetched from database. " +
                    "</br>" +
                    "The books Collection contains only id, title and authors-array. </br>" +
                    "Use GET api/books/{id} to fetch more info about the books. ")
    @GetMapping("{id}")
    public AuthorDetailedDTO findOne(@PathVariable int id) {
        log.info(String.format("##### findOne author %d", id));
        return authorService.findOne(id);
    }

    @Operation(summary = "create a new author in the database.",
            description = "Returns new author (containing id from database). </br>" +
                    "The name of the new author must be unique (case insensitive). </br>" +
                    "The name of the author should never be empty or blank. </br>")
    @PostMapping("")
    public AuthorDetailedDTO create(@Valid @RequestBody AuthorDetailedDTO authorDTO) {
        log.info("##### create author");
        return authorService.create(authorDTO);
    }
}
