package be.thomasmore.bookserver.controllers.member;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.dto.MemberDetailedDTO;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = "/sql/members/clean_members.sql", executionPhase = AFTER_TEST_METHOD)
public class MemberControllerCreateTest extends AbstractIntegrationTest {

    @Test
    @WithMockUser
    public void createAuthorHappyFlow() throws Exception {
        final String ADDRESS = "Street 1";
        final String CITY = "Antwerpen";
        final String FIRST_NAME = "FIRSTNAME";
        final String LAST_NAME = "LASTNAME";
        final String MEMBER_NUMBER = "M-ANT24-101-8";


        MemberDetailedDTO newMemberDto = MemberDetailedDTO.builder()
                .address(ADDRESS)
                .city(CITY)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .memberNumber(MEMBER_NUMBER)
                .build();


        mockMvc.perform(getMockRequestPost("/api/members", newMemberDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.address").value(ADDRESS))
                .andExpect(jsonPath("$.city").value(CITY))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME))
                .andExpect(jsonPath("$.memberNumber").value(MEMBER_NUMBER));
    }


}