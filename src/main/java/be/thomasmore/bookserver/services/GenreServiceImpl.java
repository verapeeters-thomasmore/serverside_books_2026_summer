package be.thomasmore.bookserver.services;

import be.thomasmore.bookserver.model.Genre;
import be.thomasmore.bookserver.repositories.GenreRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of GenreService.
 * Uses constructor injection (Spring best practice since Spring 4.3).
 */
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Iterable<Genre> findAll() {
        return genreRepository.findAll();
    }
}
