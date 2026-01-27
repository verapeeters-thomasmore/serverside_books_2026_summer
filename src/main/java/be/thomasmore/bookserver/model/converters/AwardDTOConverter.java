package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Award;
import be.thomasmore.bookserver.model.dto.AwardDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AwardDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param award the entity from the db
     * @return AwardDTO object to send to the client.
     */
    public AwardDTO convertToDto(Award award) {
        return modelMapper.map(award, AwardDTO.class);
    }
}
