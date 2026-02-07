package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.Genre;
import be.thomasmore.bookserver.services.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genres")
@Slf4j
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("")
    public Iterable<Genre> findAll() {
        log.info("##### findAll Genres");
        return genreService.findAll();
    }
}
