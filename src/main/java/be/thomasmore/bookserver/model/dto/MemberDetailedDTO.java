package be.thomasmore.bookserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDetailedDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String memberNumber;
}

