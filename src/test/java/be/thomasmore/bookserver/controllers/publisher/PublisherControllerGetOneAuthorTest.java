package be.thomasmore.bookserver.controllers.publisher;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/publishers/create_all_publishers.sql")
@Sql(scripts = "/sql/publishers/clean_publishers.sql", executionPhase = AFTER_TEST_METHOD)
public class PublisherControllerGetOneAuthorTest extends AbstractIntegrationTest {

    @Test
    public void getOnePublisher() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/publishers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("De Gouden Druk"))
                .andExpect(jsonPath("$.city").value("Antwerpen"))
                .andExpect(jsonPath("$.baseDiscountPercentage").value(3))
                .andExpect(jsonPath("$.bulkPurchaseQuantity").value(500))
                .andExpect(jsonPath("$.bulkDiscountPercentage").value(12))
        ;
    }
}
