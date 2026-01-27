package be.thomasmore.bookserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@Data
@Entity
public class Award {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String awardName;
    private String country;
    private Double prizeMoney;
    private String genreFocus;
    private String organization;

    @OneToMany(mappedBy = "award")
    private Collection<AwardBooks> awardBooks;
}