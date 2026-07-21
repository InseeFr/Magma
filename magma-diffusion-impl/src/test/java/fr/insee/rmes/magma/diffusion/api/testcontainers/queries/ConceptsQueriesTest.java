package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.ConceptsEndpoints;
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

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")
class ConceptsQueriesTest extends TestcontainerTest {

    @Autowired
    ConceptsEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    // =========================================================
    //   concepts/definition/{id}
    // =========================================================

    @Test
    @DisplayName("When getConceptById with conceptsSuivants, returns full concept")
    void should_return_full_concept_when_getConceptById_c0001() throws Exception {
        var response = endpoints.getconcept("c0001");
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/concept-c0001-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getConceptById with conceptsPrecedents and conceptsReferences, returns full concept")
    void should_return_full_concept_when_getConceptById_c0002() throws Exception {
        var response = endpoints.getconcept("c0002");
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/concept-c0002-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getConceptById with intitulesAlternatifs, returns full concept")
    void should_return_full_concept_when_getConceptById_c0003() throws Exception {
        var response = endpoints.getconcept("c0003");
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/concept-c0003-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getConceptById with unknown id, returns 404")
    void should_return_404_when_getConceptById_unknown_id() throws Exception {
        mockMvc.perform(get("/geo/concepts/definition/c9999"))
                .andExpect(status().isNotFound());
    }

    // =========================================================
    //   concepts/definitions
    // =========================================================

    @Test
    @DisplayName("When getConceptsList with libelle filter, returns matching concepts")
    void should_return_matching_concepts_when_getConceptsList_libelle_concept_test() throws Exception {
        var response = endpoints.getconceptsliste("concept test", null);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/concepts-list-libelle-concept-test-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, false);
    }

    @Test
    @DisplayName("When getConceptsList with collection filter, returns concepts in collection")
    void should_return_collection_concepts_when_getConceptsList_collection_idCollectionTest() throws Exception {
        var response = endpoints.getconceptsliste(null, "idCollectionTest");
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/concepts-list-collection-idCollectionTest-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, false);
    }

    @Test
    @DisplayName("When getConceptsList with libelle and collection, returns filtered concepts")
    void should_return_filtered_concepts_when_getConceptsList_libelle_and_collection() throws Exception {
        var response = endpoints.getconceptsliste("peuplement", "idCollectionTest");
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        System.out.println("DEBUG DATA: " + data);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/concepts-list-peuplement-collection-idCollectionTest-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );


        JSONAssert.assertEquals(expected, data, false);
    }

    @Test
    @DisplayName("When getConceptsList with no filter, returns all concepts")
    void should_return_all_concepts_when_getConceptsList_no_filter() {
        var response = endpoints.getconceptsliste("", null);
        var result = response.getBody();

        assertNotNull(result);
        assertTrue(result.size() >= 13, "Should contain at least the 13 test concepts, got " + result.size());
    }
}