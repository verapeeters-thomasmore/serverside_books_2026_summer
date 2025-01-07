package be.thomasmore.bookserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@NoArgsConstructor
@Data
@Entity
public class Serie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "serie")
    private Collection<Book> books;

}
