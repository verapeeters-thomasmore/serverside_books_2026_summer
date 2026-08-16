package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.dto.BookDTO;
import org.mapstruct.Mapper;

/**
 * Converter for Book entity to BookDTO record using MapStruct.
 */
@Mapper(componentModel = "spring", uses = {AuthorDTOConverter.class})
public interface BookDTOConverter {

    /**
     * @param book the entity from the db
     * @return BookDTO record to send to the client.
     */
    BookDTO convertToDto(Book book);
}
