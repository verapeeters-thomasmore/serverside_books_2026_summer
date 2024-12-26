package be.thomasmore.bookserver.controllers.book;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.Book;
import be.thomasmore.bookserver.model.dto.BookDetailedDTO;
import be.thomasmore.bookserver.repositories.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void editBookHappyFlow() throws Exception {
        final String BOOK_NEW_TITLE = "It is simple to edit a book";
        BookDetailedDTO editBookDto = BookDetailedDTO.builder()
                .id(1)
                .title(BOOK_NEW_TITLE)
                .build();

        mockMvc.perform(getMockRequestPut("/api/books/1", editBookDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value(BOOK_NEW_TITLE));


        Book loadedBook = bookRepository.findByTitle(BOOK_NEW_TITLE).orElseThrow();
        assertThat(loadedBook.getTitle()).isEqualTo(BOOK_NEW_TITLE);
    }

    @Test
    @WithMockUser
    public void editBookIdNotTheSameAsInDTO() throws Exception {
        final String BOOK1_ORIGINAL_TITLE = "Test Automation";
        final String BOOK2_ORIGINAL_TITLE = "REST API Automation Testing from Scratch";
        final String BOOK_NEW_TITLE = "It is simple to edit a book";
        BookDetailedDTO editBookDto = BookDetailedDTO.builder()
                .id(2)
                .title(BOOK_NEW_TITLE)
                .build();
        mockMvc.perform(getMockRequestPut("/api/books/1", editBookDto))
                .andExpect(status().isInternalServerError())
                .andReturn();

        Book loadedBook1 = bookRepository.findById(1).orElseThrow();
        assertThat(loadedBook1.getTitle()).isEqualTo(BOOK1_ORIGINAL_TITLE);

        Book loadedBook2 = bookRepository.findById(2).orElseThrow();
        assertThat(loadedBook2.getTitle()).isEqualTo(BOOK2_ORIGINAL_TITLE);
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

    @Test
    @WithMockUser
    public void editBookWithBlankTitle() throws Exception {
        final String BOOK_ORIGINAL_TITLE = "Test Automation";
        BookDetailedDTO editBookDto = BookDetailedDTO.builder()
                .id(1)
                .title("")
                .build();
        Assertions.assertThatThrownBy(() -> mockMvc.perform(getMockRequestPut("/api/books/1", editBookDto)));

        Book loadedBook1 = bookRepository.findById(1).orElseThrow();
        assertThat(loadedBook1.getTitle()).isEqualTo(BOOK_ORIGINAL_TITLE); // original title
    }
}