package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Member;
import be.thomasmore.bookserver.model.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * Converter for converting user registration details to Member entity.
 */
@Component
public class MemberDTOConverter {

    /**
     * Converts a UserDTO to a Member entity.
     *
     * @param userDTO User data transfer object
     * @return Member entity containing functional fields
     */
    public Member convertToEntity(UserDTO userDTO) {
        return new Member(
                userDTO.username(),
                userDTO.email()
        );
    }
}
