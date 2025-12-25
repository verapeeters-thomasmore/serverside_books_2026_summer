package be.thomasmore.bookserver.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class PublisherDetailedDTO {
    private int id;
    private String name;
    private String city;
    private Double baseDiscountPercentage;
    private Integer bulkPurchaseQuantity;
    private Double bulkDiscountPercentage;
}
