package be.thomasmore.bookserver.model.dto;

import lombok.Builder;

/**
 * Record for user data transfer.
 * JDK 16+ feature - immutable data carrier.
 */
@Builder(toBuilder = true)
public record UserDTO(
        String username,
        String password,
        String email
) {
}
