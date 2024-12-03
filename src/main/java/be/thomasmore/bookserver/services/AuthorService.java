package be.thomasmore.bookserver.services;

import be.thomasmore.bookserver.model.Author;
import be.thomasmore.bookserver.model.converters.AuthorDTOConverter;
import be.thomasmore.bookserver.model.dto.AuthorDTO;
import be.thomasmore.bookserver.repositories.AuthorRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorDTOConverter authorDTOConverter;

    public List<AuthorDTO> findAll() {
        final List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(a -> authorDTOConverter.convertToDto(a))
                .collect(Collectors.toList());
    }
}
