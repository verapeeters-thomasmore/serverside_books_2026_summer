package be.thomasmore.bookserver.services;

import be.thomasmore.bookserver.model.Library;
import be.thomasmore.bookserver.model.converters.LibraryDTOConverter;
import be.thomasmore.bookserver.model.converters.LibraryDetailedDTOConverter;
import be.thomasmore.bookserver.model.dto.LibraryDTO;
import be.thomasmore.bookserver.model.dto.LibraryDetailedDTO;
import be.thomasmore.bookserver.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private LibraryDTOConverter libraryDTOConverter;
    @Autowired
    private LibraryDetailedDTOConverter libraryDetailedDTOConverter;

    public List<LibraryDTO> findByFilter(String location, String manager) {
        final List<Library> librarys = libraryRepository.findByFilter(location, manager);
        return librarys.stream()
                .map(a -> libraryDTOConverter.convertToDto(a))
                .collect(Collectors.toList());
    }

    public LibraryDetailedDTO edit(int id, LibraryDetailedDTO libraryDto) {
        if (libraryDto.getId() != id)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("id in library (%d) does not match id in url (%d).", libraryDto.getId(), id));

        Optional<Library> libraryFromDb = libraryRepository.findById(id);
        if (libraryFromDb.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Library with id %d not found.", id));

        //overwrite fields present in libraryDto - relations are not touched
        Library librarySaved = libraryRepository.save(libraryDetailedDTOConverter.convertToEntity(libraryDto, libraryFromDb.get()));
        return libraryDetailedDTOConverter.convertToDto(librarySaved);
    }


}
