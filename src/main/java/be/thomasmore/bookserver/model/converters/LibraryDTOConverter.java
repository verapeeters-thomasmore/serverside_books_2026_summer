package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Library;
import be.thomasmore.bookserver.model.dto.LibraryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param library the entity from the db
     * @return LibraryDTO object to send to the client.
     */
    public LibraryDTO convertToDto(Library library) {
        return modelMapper.map(library, LibraryDTO.class);
    }
}
