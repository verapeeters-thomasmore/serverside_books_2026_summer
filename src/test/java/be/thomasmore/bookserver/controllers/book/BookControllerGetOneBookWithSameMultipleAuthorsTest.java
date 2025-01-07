package be.thomasmore.bookserver.controllers.book;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/books/create_books_with_same_multiple_authors.sql")
@Sql(scripts = "/sql/books/clean_all_books_and_authors.sql", executionPhase = AFTER_TEST_METHOD)
public class BookControllerGetOneBookWithSameMultipleAuthorsTest extends AbstractIntegrationTest {

    // book     author      books same authors
    // book 1   author 1    book 2, book 3, book 4, book 5
    // book 1   author 2
    // book 2   author 1    book 1, book 3, book 4, book 5
    // book 2   author 2
    // book 3   author 1    book 1, book 2
    // book 4   author 2    book 1, book 2, book 5
    // book 5   author 2    book 1, book 2, book 4

    @Test
    public void getOneBookSameAuthorsForBook1() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authors.length()").value(2))
                .andExpect(jsonPath("$.authors[0].name").value("author 1"))
                .andExpect(jsonPath("$.authors[1].name").value("author 2"))
                .andExpect(jsonPath("$.booksSameAuthor").exists())
                .andExpect(jsonPath("$.booksSameAuthor.length()").value(4))
                .andExpect(jsonPath("$.booksSameAuthor[0].title").value("book 2 with 2 authors"))
                .andExpect(jsonPath("$.booksSameAuthor[1].title").value("book 3 with only author 1"))
                .andExpect(jsonPath("$.booksSameAuthor[2].title").value("book 4 with only author 2"))
                .andExpect(jsonPath("$.booksSameAuthor[3].title").value("book 5 with only author 2"));
    }

    @Test
    public void getOneBookSameAuthorsForBook2() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/books/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authors.length()").value(2))
                .andExpect(jsonPath("$.authors[0].name").value("author 1"))
                .andExpect(jsonPath("$.authors[1].name").value("author 2"))
                .andExpect(jsonPath("$.booksSameAuthor").exists())
                .andExpect(jsonPath("$.booksSameAuthor.length()").value(4))
                .andExpect(jsonPath("$.booksSameAuthor[0].title").value("book 1 with 2 authors"))
                .andExpect(jsonPath("$.booksSameAuthor[1].title").value("book 3 with only author 1"))
                .andExpect(jsonPath("$.booksSameAuthor[2].title").value("book 4 with only author 2"))
                .andExpect(jsonPath("$.booksSameAuthor[3].title").value("book 5 with only author 2"));
    }

    @Test
    public void getOneBookSameAuthorsForBook3() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/books/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authors.length()").value(1))
                .andExpect(jsonPath("$.authors[0].name").value("author 1"))
                .andExpect(jsonPath("$.booksSameAuthor").exists())
                .andExpect(jsonPath("$.booksSameAuthor.length()").value(2))
                .andExpect(jsonPath("$.booksSameAuthor[0].title").value("book 1 with 2 authors"))
                .andExpect(jsonPath("$.booksSameAuthor[1].title").value("book 2 with 2 authors"));
    }


    @Test
    public void getOneBookSameAuthorsForBook4() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/books/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authors.length()").value(1))
                .andExpect(jsonPath("$.authors[0].name").value("author 2"))
                .andExpect(jsonPath("$.booksSameAuthor").exists())
                .andExpect(jsonPath("$.booksSameAuthor.length()").value(3))
                .andExpect(jsonPath("$.booksSameAuthor[0].title").value("book 1 with 2 authors"))
                .andExpect(jsonPath("$.booksSameAuthor[1].title").value("book 2 with 2 authors"))
                .andExpect(jsonPath("$.booksSameAuthor[2].title").value("book 5 with only author 2"));
    }

    @Test
    public void getOneBookSameAuthorsForBook5() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/books/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authors.length()").value(1))
                .andExpect(jsonPath("$.authors[0].name").value("author 2"))
                .andExpect(jsonPath("$.booksSameAuthor").exists())
                .andExpect(jsonPath("$.booksSameAuthor.length()").value(3))
                .andExpect(jsonPath("$.booksSameAuthor[0].title").value("book 1 with 2 authors"))
                .andExpect(jsonPath("$.booksSameAuthor[1].title").value("book 2 with 2 authors"))
                .andExpect(jsonPath("$.booksSameAuthor[2].title").value("book 4 with only author 2"));
    }
}
