package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Author;
import be.thomasmore.bookserver.model.dto.AuthorDetailedDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorDetailedDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param author the entity from the db
     * @return AuthorDetailedDTO object to send to the client.
     */
    public AuthorDetailedDTO convertToDto(Author author) {
        return modelMapper.map(author, AuthorDetailedDTO.class);
    }


    /**
     * @param authorDto - authorDto.books should not contain any values.
     *                  The relation between books and authors has to be updated via the book resource.
     * @return the author entity object - ready to save in the database
     */
    public Author convertToEntity(AuthorDetailedDTO authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }
}
