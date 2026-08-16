package be.thomasmore.bookserver.model.converters;

import be.thomasmore.bookserver.model.Member;
import be.thomasmore.bookserver.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Converter for converting user registration details to Member entity using MapStruct.
 */
@Mapper(componentModel = "spring")
public interface MemberDTOConverter {

    /**
     * Converts a UserDTO to a Member entity.
     *
     * @param userDTO User data transfer object
     * @return Member entity containing functional fields
     */
    @Mapping(target = "id", ignore = true)
    Member convertToEntity(UserDTO userDTO);
}
