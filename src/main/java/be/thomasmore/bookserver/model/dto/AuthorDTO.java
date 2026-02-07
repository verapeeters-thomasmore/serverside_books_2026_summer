package be.thomasmore.bookserver.model.dto;

/**
 * Record for basic author information.
 * JDK 16+ feature - immutable data carrier with automatic equals(), hashCode(), toString().
 */
public record AuthorDTO(
        int id,
        String name
) {
}
