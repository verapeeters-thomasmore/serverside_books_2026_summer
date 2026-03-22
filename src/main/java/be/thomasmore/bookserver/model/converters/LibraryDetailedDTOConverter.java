package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Library;
import be.thomasmore.bookserver.model.dto.LibraryDetailedDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryDetailedDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param library the entity from the db
     * @return LibraryDetailedDTO object to send to the client.
     */
    public LibraryDetailedDTO convertToDto(Library library) {
        return modelMapper.map(library, LibraryDetailedDTO.class);
    }

    /**
     * @param libraryDto: the data from client that has to be converted
     * @param library:    the original library entity (from db) - this object will be overwritten with the data from libraryDto
     * @return the modified library entity object - ready to save in the database
     * Do not overwrite the authors-array.
     * Use the PUT request api/librarys/{id}/authors to update the authors for a library.
     */
    public Library convertToEntity(LibraryDetailedDTO libraryDto, Library library) {
        modelMapper.map(libraryDto, library);
        return library;
    }
}
