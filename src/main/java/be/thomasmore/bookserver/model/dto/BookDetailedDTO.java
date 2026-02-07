package be.thomasmore.bookserver.model.dto;

import java.util.List;

/**
 * Detailed book response DTO (JDK 16+ record).
 * Immutable data carrier - all fields set at construction.
 * booksSameAuthor is computed and passed at construction time.
 */
public record BookDetailedDTO(
        int id,
        String title,
        String description,
        List<AuthorDTO> authors,
        List<BookDTO> booksSameAuthor
) {
    /**
     * Compact constructor for validation/normalization.
     */
    public BookDetailedDTO {
        // Ensure lists are never null for consistent API responses
        authors = authors == null ? List.of() : authors;
        booksSameAuthor = booksSameAuthor == null ? List.of() : booksSameAuthor;
    }
}
