package be.thomasmore.bookserver.controllers.author;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/authors/create_2_authors.sql")
@Sql(scripts = "/sql/authors/clean_authors.sql", executionPhase = AFTER_TEST_METHOD)
public class AuthorControllerDeleteTest extends AbstractIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @WithMockUser
    public void deleteOneAuthor() throws Exception {
        mockMvc.perform(getMockRequestDelete("/api/authors/{id}", 1))
                .andExpect(status().isNoContent());
    }
}
