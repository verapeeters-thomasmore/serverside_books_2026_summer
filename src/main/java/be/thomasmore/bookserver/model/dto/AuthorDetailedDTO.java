package be.thomasmore.bookserver.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@NoArgsConstructor
@Data
public class AuthorDetailedDTO {
    private int id;
    private String name;
    private String description;
    private String country;
    private Collection<BookDTO> books;
}
