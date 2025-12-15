package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Publisher;
import be.thomasmore.bookserver.model.dto.PublisherDetailedDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublisherDetailedDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param publisher the entity from the db
     * @return a PublisherDetailedDTO object to send to the client.
     */
    public PublisherDetailedDTO convertToDto(Publisher publisher) {
        return modelMapper.map(publisher, PublisherDetailedDTO.class);
    }

}
