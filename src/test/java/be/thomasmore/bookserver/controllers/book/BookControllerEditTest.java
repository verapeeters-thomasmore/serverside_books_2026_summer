package be.thomasmore.bookserver.controllers.book;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.dto.BookDetailedDTO;
import be.thomasmore.bookserver.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/books/create_2_books.sql")
@Sql(scripts = "/sql/books/clean_books.sql", executionPhase = AFTER_TEST_METHOD)
public class BookControllerEditTest extends AbstractIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @WithMockUser
    public void editBook() throws Exception {
        final String BOOK_TITLE = "It is simple to edit a book";
        BookDetailedDTO editBookDto = BookDetailedDTO.builder()
                .id(1)
                .title(BOOK_TITLE)
                .build();

        mockMvc.perform(getMockRequestPut("/api/books/1", editBookDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value(BOOK_TITLE));


        Book loadedBook = bookRepository.findByTitle(BOOK_TITLE).orElseThrow();
        assertThat(loadedBook.getTitle()).isEqualTo(BOOK_TITLE);
    }

    @Test
    @WithMockUser
    public void editBookIdNotTheSameAsInDTO() throws Exception {
        final String BOOK_TITLE = "It is simple to edit a book";
        BookDetailedDTO editBookDto = BookDetailedDTO.builder()
                .id(2)
                .title(BOOK_TITLE)
                .build();
        mockMvc.perform(getMockRequestPut("/api/books/1", editBookDto))
                .andExpect(status().isInternalServerError())
                .andReturn();

        Book loadedBook1 = bookRepository.findById(1).orElseThrow();
        assertThat(loadedBook1.getTitle()).isEqualTo("Test Automation"); // original title

        Book loadedBook2 = bookRepository.findById(2).orElseThrow();
        assertThat(loadedBook2.getTitle()).isEqualTo("REST API Automation Testing from Scratch"); // original title
    }


    @Test
    @WithMockUser
    public void editBookIdNotFound() throws Exception {
        final String BOOK_TITLE = "It is simple to edit a book";
        BookDetailedDTO editBookDto = BookDetailedDTO.builder()
                .id(55) //does not exist
                .title(BOOK_TITLE)
                .build();
        mockMvc.perform(getMockRequestPut("/api/books/55", editBookDto))
                .andExpect(status().isNotFound())
                .andReturn();
    }


}