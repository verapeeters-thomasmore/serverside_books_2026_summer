package be.thomasmore.bookserver.model.dto;

import lombok.Builder;

/**
 * Record for authentication response.
 * JDK 16+ feature - immutable data carrier.
 */
@Builder(toBuilder = true)
public record AuthenticationDTO(
        String username
) {
}
