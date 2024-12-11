package be.thomasmore.bookserver.controllers.book;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/books/create_book_and_authors.sql")
@Sql(scripts = "/sql/books/clean_all_books_and_authors.sql", executionPhase = AFTER_TEST_METHOD)
public class BookControllerAssociateBookWithAuthors extends AbstractIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @WithMockUser
    @Transactional
    public void associateBookWithAuthor() throws Exception {
        List<Integer> authorIdList = List.of(1, 2); //author 1 and 2 exist

        mockMvc.perform(getMockRequestPut("/api/books/1/authors", authorIdList))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.authors").exists())
                .andExpect(jsonPath("$.authors", hasSize(2)))
                .andExpect(jsonPath("$.authors[0].id").value(1))
                .andExpect(jsonPath("$.authors[0].name").value("Lasse Koskela"))
                .andExpect(jsonPath("$.authors[1].id").value(2))
                .andExpect(jsonPath("$.authors[1].name").value("Lisa Crispin"));

        //book is created in db:
        Book loadedBook = bookRepository.findById(1).orElseThrow();
        assertThat(loadedBook.getAuthors().size()).isEqualTo(2);
    }

    @Test
    @WithMockUser
    @Transactional
    public void associateBookWithEmptyAuthorList() throws Exception {
        List<Integer> authorIdList = List.of();

        mockMvc.perform(getMockRequestPut("/api/books/1/authors", authorIdList))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.authors").exists())
                .andExpect(jsonPath("$.authors", hasSize(0)));

        //book is created in db:
        Book loadedBook = bookRepository.findById(1).orElseThrow();
        assertThat(loadedBook.getAuthors().size()).isEqualTo(0);
    }
    @Test
    @WithMockUser
    @Transactional
    public void associateBookWithAuthorThatDoesNotExist() throws Exception {
        List<Integer> authorIdList = List.of(55);

        final MvcResult mvcResult = mockMvc.perform(getMockRequestPut("/api/books/1/authors", authorIdList))
                .andExpect(status().isInternalServerError())
                .andReturn();

        assertThat(mvcResult.getResponse().getErrorMessage()).isEqualTo("Not all authors were found.");

        //book is created in db:
        Book loadedBook = bookRepository.findById(1).orElseThrow();
        assertThat(loadedBook.getAuthors().size()).isEqualTo(0);
    }

}
