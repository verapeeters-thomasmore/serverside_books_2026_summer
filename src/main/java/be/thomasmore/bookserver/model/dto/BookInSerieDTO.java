package be.thomasmore.bookserver.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@Data
public class BookInSerieDTO {
    private int id;
    private String title;
    private String numberInSerie;
}

