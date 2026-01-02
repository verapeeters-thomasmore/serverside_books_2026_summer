package be.thomasmore.bookserver.controllers.serie;

import be.thomasmore.bookserver.AbstractIntegrationTest;
import be.thomasmore.bookserver.model.Serie;
import be.thomasmore.bookserver.model.dto.SerieDetailedDTO;
import be.thomasmore.bookserver.repositories.SerieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/series/create_2_series.sql")
@Sql(scripts = "/sql/series/clean_series.sql", executionPhase = AFTER_TEST_METHOD)
public class SerieControllerEditTest extends AbstractIntegrationTest {

    @Autowired
    private SerieRepository serieRepository;

    @Test
    @WithMockUser
    public void editSerieHappyFlow() throws Exception {
        final String SERIE_NEW_NAME = "serie new name";
        SerieDetailedDTO editSerieDto = SerieDetailedDTO.builder()
                .id(1)
                .name(SERIE_NEW_NAME)
                .build();

        mockMvc.perform(getMockRequestPut("/api/series/1", editSerieDto))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value(SERIE_NEW_NAME));


        Serie loadedSerie = serieRepository.findByName(SERIE_NEW_NAME).orElseThrow();
        assertThat(loadedSerie.getName()).isEqualTo(SERIE_NEW_NAME);
    }

    @Test
    @WithMockUser
    public void editSerieIdNotTheSameAsInDTO() throws Exception {
        final String SERIE1_ORIGINAL_NAME = "Programming in C";
        final String SERIE2_ORIGINAL_NAME = "Javascript";
        final String SERIE_NEW_NAME = "It is simple to edit a serie";
        SerieDetailedDTO editSerieDto = SerieDetailedDTO.builder()
                .id(2)
                .name(SERIE_NEW_NAME)
                .build();
        mockMvc.perform(getMockRequestPut("/api/series/1", editSerieDto))
                .andExpect(status().isInternalServerError())
                .andReturn();

        Serie loadedSerie1 = serieRepository.findById(1).orElseThrow();
        assertThat(loadedSerie1.getName()).isEqualTo(SERIE1_ORIGINAL_NAME);

        Serie loadedSerie2 = serieRepository.findById(2).orElseThrow();
        assertThat(loadedSerie2.getName()).isEqualTo(SERIE2_ORIGINAL_NAME);
    }


    @Test
    @WithMockUser
    public void editSerieIdNotFound() throws Exception {
        final String SERIE_NAME = "It is simple to edit a serie";
        SerieDetailedDTO editSerieDto = SerieDetailedDTO.builder()
                .id(55) //does not exist
                .name(SERIE_NAME)
                .build();
        mockMvc.perform(getMockRequestPut("/api/series/55", editSerieDto))
                .andExpect(status().isNotFound())
                .andReturn();
    }

}