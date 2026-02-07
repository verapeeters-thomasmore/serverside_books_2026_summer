package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Author;
import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.dto.AuthorDTO;
import be.thomasmore.bookserver.model.dto.BookDTO;
import be.thomasmore.bookserver.model.dto.BookDetailedDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter for Book entity to/from BookDetailedDTO.
 * Uses constructor injection (Spring best practice since Spring 4.3).
 * Manual mapping for records - no ModelMapper needed.
 */
@Component
public class BookDetailedDTOConverter {

    private final AuthorDTOConverter authorDTOConverter;

    public BookDetailedDTOConverter(AuthorDTOConverter authorDTOConverter) {
        this.authorDTOConverter = authorDTOConverter;
    }

    /**
     * Convert entity to DTO without booksSameAuthor.
     * Use convertToDto with booksSameAuthor parameter for complete DTO.
     *
     * @param book the entity from the db
     * @return BookDetailedDTO record (booksSameAuthor will be empty)
     */
    public BookDetailedDTO convertToDto(Book book) {
        return convertToDto(book, List.of());
    }

    /**
     * Convert entity to complete DTO with booksSameAuthor.
     * Modern approach: all data gathered before DTO creation.
     *
     * @param book            the entity from the db
     * @param booksSameAuthor pre-computed list of books by same authors
     * @return complete BookDetailedDTO record
     */
    public BookDetailedDTO convertToDto(Book book, List<BookDTO> booksSameAuthor) {
        List<AuthorDTO> authorDtos = book.getAuthors() == null
                ? List.of()
                : book.getAuthors().stream()
                .map(authorDTOConverter::convertToDto)
                .toList();

        return new BookDetailedDTO(
                book.getId(),
                book.getTitle(),
                null, // description not in this simpler entity
                authorDtos,
                booksSameAuthor
        );
    }

    /**
     * Convert DTO to new entity for creation.
     *
     * @param bookDto the data from client (can contain author IDs)
     * @return new Book entity ready to save
     */
    public Book convertToEntity(BookDetailedDTO bookDto) {
        Book book = new Book();
        book.setId(bookDto.id());
        book.setTitle(bookDto.title());

        // Handle author references (only IDs matter for relationship)
        if (bookDto.authors() != null && !bookDto.authors().isEmpty()) {
            List<Author> authors = bookDto.authors().stream()
                    .map(a -> new Author(a.id()))
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            book.setAuthors(authors);
        }

        return book;
    }

    /**
     * Update existing entity with DTO data.
     * Note: authors relationship is NOT touched - use PUT /api/books/{id}/authors
     *
     * @param bookDto the data from client
     * @param book    the original book entity (from db)
     * @return the modified book entity ready to save
     */
    public Book convertToEntity(BookDetailedDTO bookDto, Book book) {
        book.setTitle(bookDto.title());
        // Don't touch authors - use PUT /api/books/{id}/authors
        return book;
    }
}
