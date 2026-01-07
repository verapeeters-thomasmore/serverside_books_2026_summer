package be.thomasmore.bookserver.controllers.member;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/members/create_2_members.sql")
@Sql(scripts = "/sql/members/clean_members.sql", executionPhase = AFTER_TEST_METHOD)
public class MemberControllerGetOneMemberTest extends AbstractIntegrationTest {

    @Test
    public void getOneMember() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/members/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.address").value("Straat 1"))
                .andExpect(jsonPath("$.city").value("City 1"))
                .andExpect(jsonPath("$.firstName").value("Eerste"))
                .andExpect(jsonPath("$.lastName").value("EersteAchternaam"))
                .andExpect(jsonPath("$.memberNumber").value("M-ANT24-101-8"));
    }


}
