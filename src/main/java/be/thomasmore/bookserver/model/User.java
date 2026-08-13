package be.thomasmore.bookserver.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
// Table name is set to BOOKSUSER because USER is a reserved keyword in H2 database.
@Table(name = "BOOKSUSER")
@Data
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    String username;
    String password;
    String role;
}
