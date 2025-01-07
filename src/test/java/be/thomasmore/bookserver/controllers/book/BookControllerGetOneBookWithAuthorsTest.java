package be.thomasmore.bookserver.controllers.book;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/books/create_books_with_same_author.sql")
@Sql(scripts = "/sql/books/clean_all_books_and_authors.sql", executionPhase = AFTER_TEST_METHOD)
public class BookControllerGetOneBookWithAuthorsTest extends AbstractIntegrationTest {

    @Test
    public void getOneBook() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.authors.length()").value(1))
                .andExpect(jsonPath("$.authors[0].name").value("Lasse Koskela"))
                .andExpect(jsonPath("$.booksSameAuthor").exists())
                .andExpect(jsonPath("$.booksSameAuthor.length()").value(2))
                .andExpect(jsonPath("$.booksSameAuthor[0].title").value("Test Automation part2"))
                .andExpect(jsonPath("$.booksSameAuthor[1].title").value("Test Automation part3"));
    }


}
