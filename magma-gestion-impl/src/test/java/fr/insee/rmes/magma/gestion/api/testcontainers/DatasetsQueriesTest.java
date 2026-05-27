package fr.insee.rmes.magma.gestion.api.testcontainers;

import fr.insee.rmes.magma.gestion.api.DatasetsEndpoints;
import fr.insee.rmes.magma.gestion.model.DataSet;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "--spring.profiles.active=security.disabled")
@AutoConfigureMockMvc
@Tag("integration")
class DatasetsQueriesTest extends TestcontainerTest {

    static final String DATASET_ID = "idDatasetTest";
    static final String DATASET_URI = "http://bauhaus/catalogues/jeuDeDonnees/idDatasetTest";

    @Autowired
    DatasetsEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    /////////////////////////////////////////////////////////
    ///        /dataset/{id}                              ///
    /////////////////////////////////////////////////////////

    @Test
    void should_return_full_dataset_when_getDataSetById() throws Exception {
        var response = endpoints.getDataSetById(DATASET_ID);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                getClass().getClassLoader()
                        .getResourceAsStream("testcontainers/dataset-idDatasetTest-expected.json")
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }



    /////////////////////////////////////////////////////////
    ///        /datasets/list                             ///
    /////////////////////////////////////////////////////////

    static final String DATASET_ID_2 = "idDatasetTest2";
    static final String DATASET_URI_2 = "http://bauhaus/catalogues/jeuDeDonnees/idDatasetTest2";

    @Test
    void should_return_all_datasets_when_getListDatasets() {
        var response = endpoints.getListDatasets(null);
        var result = response.getBody();

        assertNotNull(result);
        assertEquals(2, result.size());

        var ids = result.stream().map(DataSet::getId).toList();
        assertTrue(ids.contains(DATASET_ID));
        assertTrue(ids.contains(DATASET_ID_2));
    }

    @Test
    void should_return_dataset_list_with_correct_fields() throws Exception {
        var response = endpoints.getListDatasets(null);
        var result = response.getBody();

        assertNotNull(result);

        var ds1 = result.stream().filter(d -> DATASET_ID.equals(d.getId())).findFirst().orElseThrow();
        var ds2 = result.stream().filter(d -> DATASET_ID_2.equals(d.getId())).findFirst().orElseThrow();

        String ds1Expected = new String(
                getClass().getClassLoader()
                        .getResourceAsStream("testcontainers/dataset-list-idDatasetTest-expected.json")
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(ds1Expected, objectMapper.writeValueAsString(ds1), false);

        String ds2Expected = new String(
                getClass().getClassLoader()
                        .getResourceAsStream("testcontainers/dataset-list-idDatasetTest2-expected.json")
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(ds2Expected, objectMapper.writeValueAsString(ds2), false);
    }

    @Test
    void should_return_all_datasets_when_dateMiseAJour_is_before_modified_date() {
        var response = endpoints.getListDatasets("2024-12-08T00:00:00.000");
        var result = response.getBody();

        assertNotNull(result);
        assertEquals(2, result.size());

        var ids = result.stream().map(DataSet::getId).toList();
        assertTrue(ids.contains(DATASET_ID));
        assertTrue(ids.contains(DATASET_ID_2));
    }

    @Test
    void should_return_empty_list_when_dateMiseAJour_is_after_modified_date() {
        var response = endpoints.getListDatasets("2024-12-10T00:00:00.000");
        var result = response.getBody();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /////////////////////////////////////////////////////////
    ///        404                                         ///
    /////////////////////////////////////////////////////////

    @Test
    void should_return_404_when_getDataSetById_unknown_id_dateMiseAJour_false() throws Exception {
        mockMvc.perform(get("/dataset/id-inconnu").param("dateMiseAJour", "false"))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_return_404_when_getDataSetById_unknown_id_dateMiseAJour_true() throws Exception {
        mockMvc.perform(get("/dataset/id-inconnu").param("dateMiseAJour", "true"))
                .andExpect(status().isNotFound());
    }
}