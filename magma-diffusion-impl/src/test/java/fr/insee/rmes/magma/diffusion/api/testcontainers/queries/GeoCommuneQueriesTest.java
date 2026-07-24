package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.GeoCommuneEndpoints;
import fr.insee.rmes.magma.diffusion.model.TypeEnum;
import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsCommune;
import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsCommune;
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
import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")
class GeoCommuneQueriesTest extends TestcontainerTest {

    @Autowired
    GeoCommuneEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    // =========================================================
    //   geo/commune/{code}/ascendants
    // =========================================================

    @Test
    @DisplayName("When getcogcomasc 99001 type null, returns 3 ascendants (arr, dept, region)")
    void should_return_3_ascendants_when_getcogcomasc_99001_type_null() throws Exception {
        var response = endpoints.getcogcomasc("99001", LocalDate.of(2025, 1, 1), null);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/commune-99001-ascendants-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getcogcomasc 99001 type Departement, returns 1 ascendant (dept)")
    void should_return_1_departement_when_getcogcomasc_99001_type_departement() throws Exception {
        var response = endpoints.getcogcomasc("99001", LocalDate.of(2025, 1, 1), TypeEnumAscendantsCommune.DEPARTEMENT);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/commune-99001-ascendants-departement-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getcogcomasc 99001 type Arrondissement before creation, returns 404")
    void should_return_404_when_getcogcomasc_99001_type_arrondissement_before_creation() throws Exception {
        mockMvc.perform(get("/geo/commune/99001/ascendants")
                        .param("date", "2005-01-01")
                        .param("type", String.valueOf(TypeEnumAscendantsCommune.ARRONDISSEMENT)))
                .andExpect(status().isNotFound());
    }

    // =========================================================
    //   geo/commune/{code}
    // =========================================================

    @Test
    @DisplayName("When getcogcom 99001, returns commune 99001")
    void should_return_commune_99001_when_getcogcom_99001() throws Exception {
        var response = endpoints.getcogcom("99001", LocalDate.of(2025, 1, 1));
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/commune-99001-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getcogcom 99999 (inexistant), returns 404")
    void should_return_404_when_getcogcom_99999() throws Exception {
        mockMvc.perform(get("/geo/commune/99999")
                        .param("date", "2025-01-01"))
                .andExpect(status().isNotFound());
    }

    // =========================================================
    //   geo/commune/{code}/cantons
    // =========================================================

    @Test
    @DisplayName("When getcogcomcan 99001, returns 2 cantons (9901, 9902)")
    void should_return_2_cantons_when_getcogcomcan_99001() throws Exception {
        var response = endpoints.getcogcomcan("99001", LocalDate.of(2025, 1, 1));
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/commune-99001-cantons-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    // =========================================================
    //   geo/commune/{code}/descendants
    // =========================================================

    @Test
    @DisplayName("When getcogcomdesc 99001 type null, returns 2 communes deleguees")
    void should_return_2_communeDeleguee_when_getcogcomdesc_99001_type_null() throws Exception {
        var response = endpoints.getcogcomdesc("99001", LocalDate.of(2025, 1, 1), null);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/commune-99001-descendants-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getcogcomdesc 99001 type Iris, returns 404")
    void should_return_404_when_getcogcomdesc_99001_type_iris() throws Exception {
        mockMvc.perform(get("/geo/commune/99001/descendants")
                        .param("date", "2025-01-01")
                        .param("type", String.valueOf(TypeEnumDescendantsCommune.IRIS)))
                .andExpect(status().isNotFound());
    }

    // =========================================================
    //   geo/communes
    // =========================================================

    @Test
    @DisplayName("When getcogcomliste filtreNom='Commune test', returns 3 communes actives")
    void should_return_3_communes_when_getcogcomliste_filtreNom() throws Exception {
        var response = endpoints.getcogcomliste("2025-01-01", "Commune test", false);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/communes-liste-filtreNom-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getcogcomliste date=*, returns 5 communes (actives + supprimees)")
    void should_return_5_communes_when_getcogcomliste_etoile() throws Exception {
        var response = endpoints.getcogcomliste("*", null, null);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/communes-liste-etoile-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    // =========================================================
    //   geo/commune/{code}/precedents
    // =========================================================

    @Test
    @DisplayName("When getcogcomprec 99003, returns 2 precedents (99004, 99005)")
    void should_return_2_precedents_when_getcogcomprec_99003() throws Exception {
        var response = endpoints.getcogcomprec("99003", LocalDate.of(2025, 1, 1));
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/commune-99003-precedents-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getcogcomprec 99001 (no precedents), returns 404")
    void should_return_404_when_getcogcomprec_99001_no_precedents() throws Exception {
        mockMvc.perform(get("/geo/commune/99001/precedents")
                        .param("date", "2025-01-01"))
                .andExpect(status().isNotFound());
    }

    // =========================================================
    //   geo/commune/{code}/projetes
    // =========================================================

    @Test
    @DisplayName("When getcogcomproj 99003 dateProjection=2010-01-01, returns 2 projetes (99004, 99005)")
    void should_return_2_projetes_when_getcogcomproj_99003() throws Exception {
        var response = endpoints.getcogcomproj("99003", LocalDate.of(2010, 1, 1), LocalDate.of(2025, 1, 1));
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/commune-99003-projetes-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getcogcomproj dateProjection null, returns 400")
    void should_return_400_when_getcogcomproj_dateProjection_null() throws Exception {
        mockMvc.perform(get("/geo/commune/99001/projetes")
                        .param("date", "2025-01-01"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("When getcogcomproj dateProjection empty, returns 400")
    void should_return_400_when_getcogcomproj_dateProjection_empty() throws Exception {
        mockMvc.perform(get("/geo/commune/99001/projetes")
                        .param("dateProjection", "")
                        .param("date", "2025-01-01"))
                .andExpect(status().isBadRequest());
    }

    // =========================================================
    //   geo/commune/{code}/suivants
    // =========================================================

    @Test
    @DisplayName("When getcogcomsuiv 99004, returns 1 suivant (99003)")
    void should_return_1_suivant_when_getcogcomsuiv_99004() throws Exception {
        var response = endpoints.getcogcomsuiv("99004", LocalDate.of(2010, 1, 1));
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/commune-99004-suivants-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getcogcomsuiv 99001 (active, no suivants), returns 404")
    void should_return_404_when_getcogcomsuiv_99001_no_suivants() throws Exception {
        mockMvc.perform(get("/geo/commune/99001/suivants")
                        .param("date", "2025-01-01"))
                .andExpect(status().isNotFound());
    }

    // =========================================================
    //   geo/commune/{code}/intersections
    // =========================================================

    @Test
    @DisplayName("When getcogcomintersect 99001 type null, returns 5 intersections")
    void should_return_5_intersections_when_getcogcomintersect_99001_type_null() throws Exception {
        var response = endpoints.getcogcomintersect("99001", LocalDate.of(2025, 1, 1), null);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/commune-99001-intersections-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }

    @Test
    @DisplayName("When getcogcomintersect 99001 type Canton, returns 2 cantons")
    void should_return_2_cantons_when_getcogcomintersect_99001_type_canton() throws Exception {
        var response = endpoints.getcogcomintersect("99001", LocalDate.of(2025, 1, 1), TypeEnum.CANTON);
        var result = response.getBody();

        assertNotNull(result);
        String data = objectMapper.writeValueAsString(result);
        String expected = new String(
                Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream("testcontainers/commune-99001-intersections-canton-expected.json"))
                        .readAllBytes(),
                StandardCharsets.UTF_8
        );
        JSONAssert.assertEquals(expected, data, true);
    }
}