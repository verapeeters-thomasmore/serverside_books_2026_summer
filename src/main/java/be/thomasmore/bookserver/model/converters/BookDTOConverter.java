package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.dto.AuthorDTO;
import be.thomasmore.bookserver.model.dto.BookDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Converter for Book entity to BookDTO record.
 * Uses direct mapping for records - cleaner and more explicit than ModelMapper.
 */
@Component
public class BookDTOConverter {

    private final AuthorDTOConverter authorDTOConverter;

    public BookDTOConverter(AuthorDTOConverter authorDTOConverter) {
        this.authorDTOConverter = authorDTOConverter;
    }

    /**
     * @param book the entity from the db
     * @return BookDTO record to send to the client.
     * The BookDTO contains a list of AuthorDTO
     * so that the client does not need to do a second request to display this basic info.
     */
    public BookDTO convertToDto(Book book) {
        List<AuthorDTO> authorDtos = book.getAuthors() == null
                ? List.of()
                : book.getAuthors().stream()
                .map(authorDTOConverter::convertToDto)
                .toList();

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                authorDtos
        );
    }
}
