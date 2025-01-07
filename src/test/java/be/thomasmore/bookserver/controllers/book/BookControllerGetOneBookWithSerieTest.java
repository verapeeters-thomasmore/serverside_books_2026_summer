package be.thomasmore.bookserver.controllers.book;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/books/create_2_books_with_serie.sql")
@Sql(scripts = "/sql/books/clean_all_books_and_series.sql", executionPhase = AFTER_TEST_METHOD)
public class BookControllerGetOneBookWithSerieTest extends AbstractIntegrationTest {

    @Test
    public void getOneBook() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Programming in C the basics"))
                .andExpect(jsonPath("$.serie").exists())
                .andExpect(jsonPath("$.serie.id").value(1))
                .andExpect(jsonPath("$.serie.name").value("Programming in C"))
                .andExpect(jsonPath("$.numberInSerie").value(1))
        ;
    }
}
