package be.thomasmore.bookserver.controllers.publisher;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/publishers/create_all_publishers.sql")
@Sql(scripts = "/sql/publishers/clean_publishers.sql", executionPhase = AFTER_TEST_METHOD)
public class PublisherControllerInvoicePriceTest extends AbstractIntegrationTest {

    @Test
    public void getInvoicePriceCase1() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/publishers/1/invoiceprice?basePrice=25&quantity=100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(2425));
    }

    @Test
    public void getInvoicePriceCase3() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/publishers/1/invoiceprice?basePrice=25&quantity=600"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(13200));
    }

    @Test
    public void getInvoicePriceCase4() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/publishers/3/invoiceprice?basePrice=18&quantity=599"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(10512.45));
    }

    @Test
    public void getInvoicePriceCase6() throws Exception {
        mockMvc.perform(getMockRequestGet("/api/publishers/4/invoiceprice?basePrice=30&quantity=300"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(7380));
    }
}
