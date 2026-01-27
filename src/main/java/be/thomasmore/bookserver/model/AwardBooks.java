package be.thomasmore.bookserver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class AwardBooks {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String awardYear;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    private Award award;
}
