package be.thomasmore.bookserver.controllers.serie;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/series/create_1_serie_with_books.sql")
@Sql(scripts = "/sql/series/clean_all_series_and_books.sql", executionPhase = AFTER_TEST_METHOD)
public class SerieControllerGetOneSerieWithBooksTest extends AbstractIntegrationTest {

    @Test
    public void getOneSerieWithBooks() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/series/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Programming in C"))
                .andExpect(jsonPath("$.books.length()").value(2))
                .andExpect(jsonPath("$.books[0].id").value(1))
                .andExpect(jsonPath("$.books[0].title").value("Programming in C the basics"))
                .andExpect(jsonPath("$.books[0].numberInSerie").value(1))
                .andExpect(jsonPath("$.books[1].id").value(2))
                .andExpect(jsonPath("$.books[1].title").value("Programming in C advanced"))
                .andExpect(jsonPath("$.books[1].numberInSerie").value(2));
    }

}
