package be.thomasmore.bookserver.model.dto;

import java.util.List;

/**
 * Record for basic book information with authors.
 * JDK 16+ feature - immutable data carrier.
 * Uses List instead of Collection for better API clarity (JDK 21+ sequenced collections).
 */
public record BookDTO(
        int id,
        String title,
        List<AuthorDTO> authors
) {
}
