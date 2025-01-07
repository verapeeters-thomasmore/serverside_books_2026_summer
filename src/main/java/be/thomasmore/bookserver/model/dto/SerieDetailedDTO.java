package be.thomasmore.bookserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SerieDetailedDTO {
    private int id;
    private String name;
    private Collection<BookInSerieDTO> books;
}
