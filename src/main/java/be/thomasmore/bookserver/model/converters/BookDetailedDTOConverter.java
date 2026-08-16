package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.dto.BookDetailedDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Converter for Book entity to/from BookDetailedDTO using MapStruct.
 */
@Mapper(componentModel = "spring", uses = {AuthorDTOConverter.class})
public interface BookDetailedDTOConverter {

    /**
     * Convert entity to DTO.
     *
     * @param book the entity from the db
     * @return BookDetailedDTO record
     */
    BookDetailedDTO convertToDto(Book book);

    /**
     * Convert DTO to new entity for creation.
     *
     * @param bookDto the data from client
     * @return new Book entity ready to save
     */
    Book convertToEntity(BookDetailedDTO bookDto);

    /**
     * Update existing entity with DTO data.
     * Note: authors relationship is NOT touched.
     *
     * @param bookDto the data from client
     * @param book    the original book entity (from db)
     */
    @Mapping(target = "authors", ignore = true)
    void updateEntityFromDto(BookDetailedDTO bookDto, @MappingTarget Book book);

    /**
     * Update existing entity with DTO data and return it.
     * Preserves original method signature for compatibility.
     *
     * @param bookDto the data from client
     * @param book    the original book entity (from db)
     * @return the modified book entity ready to save
     */
    default Book convertToEntity(BookDetailedDTO bookDto, Book book) {
        updateEntityFromDto(bookDto, book);
        return book;
    }
}
