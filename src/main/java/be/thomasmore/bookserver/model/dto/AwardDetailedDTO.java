package be.thomasmore.bookserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AwardDetailedDTO {
    private int id;
    private String awardName;
    private String country;
    private Double prizeMoney;
    private String genreFocus;
    private String organization;
}
