package be.thomasmore.bookserver.controllers.author;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.Author;
import be.thomasmore.bookserver.model.dto.AuthorDetailedDTO;
import be.thomasmore.bookserver.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @WithMockUser
    public void editAuthorIdNotTheSameAsInDTO() throws Exception {
        final String AUTHOR_NAME = "edited authorname";
        AuthorDetailedDTO editAuthorDto = AuthorDetailedDTO.builder()
                .id(2)
                .name(AUTHOR_NAME)
                .build();
        mockMvc.perform(getMockRequestPut("/api/authors/1", editAuthorDto))
                .andExpect(status().isInternalServerError())
                .andReturn();

        Author loadedAuthor1 = authorRepository.findById(1).orElseThrow();
        assertThat(loadedAuthor1.getName()).isEqualTo("Thomas Mann"); // original title

        Author loadedAuthor2 = authorRepository.findById(2).orElseThrow();
        assertThat(loadedAuthor2.getName()).isEqualTo("Isaac Asimov"); // original title
    }

    @Test
    @WithMockUser
    public void editAuthorIdNotFound() throws Exception {
        final String AUTHOR_NAME = "edited authorname";
        AuthorDetailedDTO editAuthorDto = AuthorDetailedDTO.builder()
                .id(55)
                .name(AUTHOR_NAME)
                .build();
        mockMvc.perform(getMockRequestPut("/api/authors/55", editAuthorDto))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
