package be.thomasmore.bookserver.controllers.author;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.Author;
import be.thomasmore.bookserver.model.dto.AuthorDetailedDTO;
import be.thomasmore.bookserver.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = "/sql/authors/clean_authors.sql", executionPhase = AFTER_TEST_METHOD)
public class AuthorControllerCreateTest extends AbstractIntegrationTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @WithMockUser
    public void createAuthor() throws Exception {
        final String AUTHOR_NAME = "Kent Beck";
        final String AUTHOR_DESCRIPTION = "Kent Beck is an American software engineer and the creator of extreme programming, a software development methodology that eschews rigid formal specification for a collaborative and iterative design process.";
        final String AUTHOR_COUNTRY = "USA";
        AuthorDetailedDTO newAuthorDto = AuthorDetailedDTO.builder()
                .name(AUTHOR_NAME)
                .description(AUTHOR_DESCRIPTION)
                .country(AUTHOR_COUNTRY)
                .build();

        mockMvc.perform(getMockRequestPost("/api/authors", newAuthorDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(AUTHOR_NAME))
                .andExpect(jsonPath("$.description").value(AUTHOR_DESCRIPTION))
                .andExpect(jsonPath("$.country").value(AUTHOR_COUNTRY));

        Author loadedAuthor = authorRepository.findByName(AUTHOR_NAME).orElseThrow();
        assertThat(loadedAuthor.getName()).isEqualTo(AUTHOR_NAME);
    }

    @Test
    @ExceptionHandler
    @WithMockUser
    public void createAuthor_nameCannotBeEmpty() {
        AuthorDetailedDTO newAuthorDto = AuthorDetailedDTO.builder()
                .name(null)
                .build();

        assertThatThrownBy(() -> mockMvc.perform(getMockRequestPost("/api/authors", newAuthorDto)));
        assertThat(authorRepository.count()).isEqualTo(0);
    }

    @Test
    @ExceptionHandler
    @WithMockUser
    public void createAuthor_nameCannotBeBlank() {
        AuthorDetailedDTO newAuthorDto = AuthorDetailedDTO.builder()
                .name("")
                .build();

        assertThatThrownBy(() -> mockMvc.perform(getMockRequestPost("/api/authors", newAuthorDto)));
        assertThat(authorRepository.count()).isEqualTo(0);
    }

    @Test
    @WithMockUser
    public void createAuthor_givenIdIsNotTakenIntoAccount() throws Exception {
        final String AUTHOR_NAME = "Kent Beck";
        AuthorDetailedDTO newAuthorDto = AuthorDetailedDTO.builder()
                .id(66)
                .name(AUTHOR_NAME)
                .build();

        mockMvc.perform(getMockRequestPost("/api/authors", newAuthorDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(AUTHOR_NAME));

        Author loadedAuthor = authorRepository.findByName(AUTHOR_NAME).orElseThrow();
        assertThat(loadedAuthor.getId()).isEqualTo(1); // not 66!!!
        assertThat(loadedAuthor.getName()).isEqualTo(AUTHOR_NAME);
    }

}