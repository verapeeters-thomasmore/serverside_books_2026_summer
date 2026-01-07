package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Member;
import be.thomasmore.bookserver.model.dto.MemberDetailedDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDetailedDTOConverter {

    @Autowired
    private ModelMapper modelMapper;


    /**
     * @param member the entity from the db
     * @return MemberDTO object to send to the client.
     */
    public MemberDetailedDTO convertToDto(Member member) {
        return modelMapper.map(member, MemberDetailedDTO.class);
    }


    /**
     * @param memberDTO .
     * @return the member entity object - ready to save in the database
     */
    public Member convertToEntity(MemberDetailedDTO memberDTO) {
        return modelMapper.map(memberDTO, Member.class);
    }
}
