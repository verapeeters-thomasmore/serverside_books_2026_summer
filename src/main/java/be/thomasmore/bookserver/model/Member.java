package be.thomasmore.bookserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@Entity
public class Member {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NotBlank(message = "Username should not be blank")
    private String username;

    private String email;

    public Member(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
