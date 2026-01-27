package be.thomasmore.bookserver.controllers.book;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql("/sql/books/create_award_with_2_books.sql")
@Sql(scripts = "/sql/books/clean_awards_with_books.sql", executionPhase = AFTER_TEST_METHOD)
public class BookControllerGetOneBookPrizeMoneyTest extends AbstractIntegrationTest {
    @Test
    public void getOneBookWithAwards() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Oryx and Crake"))
                .andExpect(jsonPath("$.description").value("MaddAddam is a serie of 3 dystopian science-fiction novels that deals with extreme genetic engineering."))
                .andExpect(jsonPath("$.totalPrizeMoney").value(1000000.0));
    }

    @Test
    public void getOneBookWithNoAwards() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/books/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.title").value("Pattern Hatching: Design Patterns Applied"))
                .andExpect(jsonPath("$.description").value("Description Random"))
                .andExpect(jsonPath("$.totalPrizeMoney").isEmpty());
    }
}
