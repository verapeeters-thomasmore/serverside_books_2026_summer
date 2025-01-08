package be.thomasmore.bookserver.controllers.book;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/books/create_book_with_authors.sql")
@Sql(scripts = "/sql/books/clean_all_books_and_authors.sql", executionPhase = AFTER_TEST_METHOD)
public class BookControllerGetAuthorsForBook extends AbstractIntegrationTest {
    @Test
    public void getAuthorsForBook() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/books/1/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Lasse Koskela"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Lisa Crispin"));
    }

    @Test
    public void getAuthorsForBookNotFound() throws Exception {
        final MvcResult mvcResult =
                mockMvc.perform(getMockRequestGet("/api/books/9999/authors"))
                        .andExpect(status().isNotFound())
                        .andReturn();
        assertThat(mvcResult.getResponse().getErrorMessage()).isEqualTo("Book with id 9999 not found.");
    }
}
