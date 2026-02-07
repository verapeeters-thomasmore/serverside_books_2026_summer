package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Author;
import be.thomasmore.bookserver.model.dto.AuthorDTO;
import org.springframework.stereotype.Component;

/**
 * Converter for Author entity to AuthorDTO record.
 * Uses direct mapping for records - cleaner and more explicit than ModelMapper.
 */
@Component
public class AuthorDTOConverter {

    /**
     * @param author the entity from the db
     * @return AuthorDTO record to send to the client.
     */
    public AuthorDTO convertToDto(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getName()
        );
    }
}
