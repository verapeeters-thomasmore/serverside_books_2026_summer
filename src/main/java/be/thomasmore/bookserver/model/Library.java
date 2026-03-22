package be.thomasmore.bookserver.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(exclude = {"authors"})
@ToString(exclude = {"authors"})
@Entity
public class Library {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String libraryName;
    private String location;
    private Integer establishedYear;
    private String managerName;

    @ManyToMany(mappedBy = "libraries", fetch = FetchType.LAZY)
    private List<Book> books;
}