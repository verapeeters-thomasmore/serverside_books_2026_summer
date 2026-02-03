package be.thomasmore.bookserver.controllers.award;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.Award;
import be.thomasmore.bookserver.model.dto.AwardDetailedDTO;
import be.thomasmore.bookserver.repositories.AwardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/awards/create_2_awards.sql")
@Sql(scripts = "/sql/awards/clean_awards.sql", executionPhase = AFTER_TEST_METHOD)
public class AwardControllerEditTest extends AbstractIntegrationTest {

    @Autowired
    private AwardRepository awardRepository;

    @Test
    @WithMockUser
    public void editAwardHappyFlow() throws Exception {
        final String AWARD_NEW_NAME = "award new name";
        AwardDetailedDTO editAwardDto = AwardDetailedDTO.builder()
                .id(1)
                .awardName(AWARD_NEW_NAME)
                .build();

        mockMvc.perform(getMockRequestPut("/api/awards/1", editAwardDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.awardName").value(AWARD_NEW_NAME));


//        Award loadedAward = awardRepository.findByAwardName(AWARD_NEW_NAME).orElseThrow();
//        assertThat(loadedAward.getAwardName()).isEqualTo(AWARD_NEW_NAME);
    }
}
