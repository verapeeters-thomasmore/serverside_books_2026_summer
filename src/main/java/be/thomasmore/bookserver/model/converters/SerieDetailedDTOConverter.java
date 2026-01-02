package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Author;
import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.Serie;
import be.thomasmore.bookserver.model.dto.AuthorDetailedDTO;
import be.thomasmore.bookserver.model.dto.BookDetailedDTO;
import be.thomasmore.bookserver.model.dto.SerieDetailedDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class SerieDetailedDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param serie the entity from the db
     * @return SerieDetailedDTO object to send to the client.
     */
    public SerieDetailedDTO convertToDto(Serie serie) {
        return modelMapper.map(serie, SerieDetailedDTO.class);
    }

    /**
     * @param serieDto
     * @return the serie entity object - ready to save in the database
     */
    public Serie convertToEntity(SerieDetailedDTO serieDto) {
        return modelMapper.map(serieDto, Serie.class);
    }

    /**
     * @param serieDto the data from client that has to be converted
     * @param serie:   the original serie entity (from db) - this object will be overwritten with the data from serieDto
     * @return the modified serie entity object - ready to save in the database
     */
    public Serie convertToEntity(SerieDetailedDTO serieDto, Serie serie) {
        modelMapper.map(serieDto, serie);
        return serie;
    }
}
