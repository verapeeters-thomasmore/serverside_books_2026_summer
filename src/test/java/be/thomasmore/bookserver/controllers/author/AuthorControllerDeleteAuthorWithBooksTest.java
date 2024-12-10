package be.thomasmore.bookserver.controllers.author;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.repositories.AuthorRepository;
import be.thomasmore.bookserver.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/authors/create_author_with_book.sql")
@Sql(scripts = "/sql/authors/clean_author_with_book.sql", executionPhase = AFTER_TEST_METHOD)
public class AuthorControllerDeleteAuthorWithBooksTest extends AbstractIntegrationTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @WithMockUser
    public void deleteOneAuthor() throws Exception {

        final MvcResult mvcResult = mockMvc.perform(getMockRequestDelete("/api/authors/{id}", 1))
                .andExpect(status().isInternalServerError())
                .andReturn();
        assertThat(mvcResult.getResponse().getErrorMessage()).isEqualTo("Author with id 1 still contains books.");
        assertThat(authorRepository.count()).isEqualTo(1);
        assertThat(bookRepository.count()).isEqualTo(1);
    }
}
