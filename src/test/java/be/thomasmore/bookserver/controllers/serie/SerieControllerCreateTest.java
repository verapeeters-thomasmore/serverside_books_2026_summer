package be.thomasmore.bookserver.controllers.serie;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.Serie;
import be.thomasmore.bookserver.model.dto.SerieDetailedDTO;
import be.thomasmore.bookserver.repositories.SerieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = "/sql/series/clean_series.sql", executionPhase = AFTER_TEST_METHOD)
public class SerieControllerCreateTest extends AbstractIntegrationTest {

    @Autowired
    private SerieRepository serieRepository;

    @Test
    @WithMockUser
    public void createSerie() throws Exception {
        final String SERIE_NAME = "nieuwe serie";
        SerieDetailedDTO newSerieDto = SerieDetailedDTO.builder()
                .name(SERIE_NAME)
                .build();

        mockMvc.perform(getMockRequestPost("/api/series/", newSerieDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(SERIE_NAME));

        Serie loadedSerie = serieRepository.findByName(SERIE_NAME).orElseThrow();
        assertThat(loadedSerie.getName()).isEqualTo(SERIE_NAME);
    }

    @Test
    @WithMockUser
    public void createSerie_givenIdIsNotTakenIntoAccount() throws Exception {
        final String SERIE_NAME = "Serie with random given id";
        SerieDetailedDTO newSerieDto = SerieDetailedDTO.builder().id(57)
                .name(SERIE_NAME)
                .build();

        mockMvc.perform(getMockRequestPost("/api/series/", newSerieDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1)) // not 57!
                .andExpect(jsonPath("$.name").value(SERIE_NAME));

        Serie loadedSerie = serieRepository.findByName(SERIE_NAME).orElseThrow();
        assertThat(loadedSerie.getId()).isEqualTo(1); // not 57!
        assertThat(loadedSerie.getName()).isEqualTo(SERIE_NAME);
    }

}