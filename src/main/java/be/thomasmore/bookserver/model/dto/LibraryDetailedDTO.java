package be.thomasmore.bookserver.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LibraryDetailedDTO {
    private int id;
    private String libraryName;
    private String location;
    private Integer establishedYear;
    private String managerName;
}

