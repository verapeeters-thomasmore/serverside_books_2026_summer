package be.thomasmore.bookserver.model.dto;

/**
 * Record for authentication response.
 * JDK 16+ feature - immutable data carrier.
 */
public record AuthenticationDTO(
        String username
) {
}
