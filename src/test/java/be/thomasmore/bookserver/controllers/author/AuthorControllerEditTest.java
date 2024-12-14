package be.thomasmore.bookserver.controllers.author;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.dto.AuthorDetailedDTO;
import be.thomasmore.bookserver.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/authors/create_2_authors.sql")
@Sql(scripts = "/sql/authors/clean_authors.sql", executionPhase = AFTER_TEST_METHOD)
public class AuthorControllerEditTest extends AbstractIntegrationTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @WithMockUser
    public void editAuthor() throws Exception {
        final String AUTHOR_NAME = "edited authorname";
        AuthorDetailedDTO editAuthorDto = AuthorDetailedDTO.builder()
                .id(1)
                .name(AUTHOR_NAME)
                .build();
        mockMvc.perform(getMockRequestPut("/api/authors/1", editAuthorDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(AUTHOR_NAME));
    }

}
