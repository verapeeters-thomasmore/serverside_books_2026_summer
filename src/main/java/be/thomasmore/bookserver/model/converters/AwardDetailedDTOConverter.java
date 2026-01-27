package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Award;
import be.thomasmore.bookserver.model.dto.AwardDetailedDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AwardDetailedDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param award the entity from the db
     * @return AwardDetailedDTO object to send to the client.
     */
    public AwardDetailedDTO convertToDto(Award award) {
        return modelMapper.map(award, AwardDetailedDTO.class);
    }

    /**
     * @param awardDto the data from client that has to be converted
     * @param award:   the original award entity (from db) - this object will be overwritten with the data from serieDto
     * @return the modified award entity object - ready to save in the database
     */
    public Award convertToEntity(AwardDetailedDTO awardDto, Award award) {
        modelMapper.map(awardDto, award);
        return award;
    }
}
