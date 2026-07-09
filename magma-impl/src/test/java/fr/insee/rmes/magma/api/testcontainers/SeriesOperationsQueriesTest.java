package fr.insee.rmes.magma.api.testcontainers;

import fr.insee.rmes.magma.api.SeriesOperationsEndpoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "--spring.profiles.active=security.disabled")
@AutoConfigureMockMvc
@Tag("integration")
class SeriesOperationsQueriesTest extends TestcontainerTestGestion {

    static final String SERIE_ID = "idSeriePrincipaleTest";
    static final String OPERATION_ID = "idOperationTest";

    @Autowired
    SeriesOperationsEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    // =========================================================
    //    /operations/serie/{id}
    // =========================================================

    @Test
    @DisplayName("When getSerieById (/operations/serie/{id} end-point), returns full serie")
    void should_return_serieById_idSeriePrincipaleTest_when_getSerieById_idSeriePrincipaleTest() throws Exception {
        var response = endpoints.getSerieById(SERIE_ID);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/serie-idSeriePrincipaleTest-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, false);
    }

    @Test
    @DisplayName("When getSerieById (/operations/serie/{id} end-point) with unknown id, returns 404")
    void should_return_404_when_getSerieById_unknown_id() throws Exception {
        mockMvc.perform(get("/operations/serie/serieInconnue"))
                .andExpect(status().isNotFound());
    }

    // =========================================================
    //   /operations/operation/{id}
    // =========================================================

    @Test
    @DisplayName("When getOperationByCode (/operations/operation/{id} end-point), returns full operation")
    void should_return_operationById_idOperationTest_when_getOperationByCode_idOperationTest() throws Exception {
        var response = endpoints.getOperationByCode(OPERATION_ID);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/operation-idOperationTest-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, false);
    }

    @Test
    @DisplayName("When getOperationByCode (/operations/serie/{id} end-point) with unknown id, returns 404")
    void should_return_404_when_getOperationById_unknown_id() throws Exception {
        mockMvc.perform(get("/operations/operation/serieInconnue"))
                .andExpect(status().isNotFound());
    }

    // =========================================================
    //    /operations/series
    // =========================================================

    @Test
    @DisplayName("When getAllSeries (/operations/series end-point) without date filter, returns all series")
    void should_return_all_series_when_getAllSeries_without_dateFilter() throws Exception {
        var response = endpoints.getAllSeries(null);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/serie-list-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getAllSeries (/operations/series end-point) with date 2025-01-01, returns 1 filtered serie")
    void should_return_filtered_series_when_getAllSeries_with_date_2025_01_01() {
        var response = endpoints.getAllSeries("2025-01-01");
        var result = response.getBody();

        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("idSerieTest", result.getFirst().getSeriesId()),
                () -> assertEquals("http://bauhaus/operations/serie/idSerieTest", result.getFirst().getUri())
        );
    }

    @Test
    @DisplayName("When getAllSeries (/operations/series end-point) with date 2024-01-01, returns 2 filtered series")
    void should_return_filtered_series_when_getAllSeries_with_date_2024_01_01() {
        var response = endpoints.getAllSeries("2024-01-01");
        var result = response.getBody();

        assertNotNull(result);
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("idSeriePrincipaleTest", result.getFirst().getSeriesId()),
                () -> assertEquals("idSerieTest", result.get(1).getSeriesId())
        );
    }

    @Test
    @DisplayName("When getAllSeries (/operations/series end-point), returns 200")
    void should_return_200_when_getAllSeries() throws Exception {
        mockMvc.perform(get("/operations/series"))
                .andExpect(status().isOk());
    }

    /////////////////////////////////////////////////////////
    ///        /operations/indicateur/{id}                ///
    /////////////////////////////////////////////////////////

    @Test
    @DisplayName("When getIndicatorById, returns full indicator")
    void should_return_full_indicateur_when_getIndicatorById_idIndicateurTest() throws Exception {
        var response = endpoints.getIndicatorById("idIndicateurTest");
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/indicateur-idIndicateurTest-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getIndicatorById with unknown id, returns 404")
    void should_return_404_when_getIndicatorById_unknown_id() throws Exception {
        mockMvc.perform(get("/operations/indicateur/indicateurInconnu"))
                .andExpect(status().isNotFound());
    }

}
