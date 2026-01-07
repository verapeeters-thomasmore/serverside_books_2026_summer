package be.thomasmore.bookserver.controllers.member;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.dto.MemberDetailedDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = "/sql/members/clean_members.sql", executionPhase = AFTER_TEST_METHOD)
public class MemberControllerCreateMemberNumberTest extends AbstractIntegrationTest {


    @WithMockUser
    @ParameterizedTest
    @CsvSource({
            "Antwerpen,M-ANT24-101-8",
            "Gent,M-GEN24-367-4",
            "Hasselt,M-HAS25-367-5",

    })
    public void createOneMemberWithMemberNumberOk(String city, String memberNumber) throws Exception {
        //memberNumber is correct but needs to be cleaned
        MemberDetailedDTO newParticipantDetailedDTO = MemberDetailedDTO.builder()
                .city(city)
                .memberNumber(memberNumber)
                .build();

        mockMvc.perform(getMockRequestPost("/api/members/", newParticipantDetailedDTO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.memberNumber").value(memberNumber));
    }

    @ParameterizedTest
    @WithMockUser
    @CsvSource({
            "Antwerpen,ANT25-101-0", //M missing
            "Antwerpen,MANT251010", //dashes
            "Antwerpen,M-ANT-24-101-8", //dashes
            "Antwerpen,M-ANT-24-1012-1",  //4 numbers iso 3
            "Antwerpen,M-ant-24-101-8",  //city code not in uppercase
            "Brussel,M-ANT24-101-8",  //city code not correct (should be BRU)
            "Antwerpen,M-ANT24-101-9",  //checksum not correct
            "Antwerpen,M-ANT25-101-5",  //checksum not correct
    })
    public void createOneMemberWithMemberNumberInvalid(String city, String memberNumber) throws Exception {
        MemberDetailedDTO newParticipantDetailedDTO = MemberDetailedDTO.builder()
                .city(city)
                .memberNumber(memberNumber)
                .build();
        mockMvc.perform(getMockRequestPost("/api/members/", newParticipantDetailedDTO))
                .andExpect(status().isInternalServerError());
    }

}