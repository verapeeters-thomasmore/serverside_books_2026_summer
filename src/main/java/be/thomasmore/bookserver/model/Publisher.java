package be.thomasmore.bookserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String city;
    private Double baseDiscountPercentage; // Bijvoorbeeld: 3.0 voor 3%
    private Integer bulkPurchaseQuantity; // Bijvoorbeeld: 500
    private Double bulkDiscountPercentage; // Bijvoorbeeld: 12.0 voor 12%
}