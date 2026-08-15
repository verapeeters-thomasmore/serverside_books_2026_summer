package be.thomasmore.bookserver.model.dto;

/**
 * Record for user data transfer.
 * JDK 16+ feature - immutable data carrier.
 */
public record UserDTO(
        String username,
        String password
) {
}
