package fr.insee.rmes.magma.gestion.api.testcontainers;

import fr.insee.rmes.magma.gestion.api.StructuresComposantsEndpoints;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "security.disabled")
@AutoConfigureMockMvc
@Tag("integration")

class StructuresComposantsQueriesTest  extends TestcontainerTest {

    @Autowired
    StructuresComposantsEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;


    /////////////////////////////////////////////////////////
    ///        /structures                                ///
    /////////////////////////////////////////////////////////

    //    /structures
    @Test
    void should_return_2_structures_when_structures() {
        var response = endpoints.getAllStructures(null);
        var result = response.getBody();
        assert result != null;
        var resultItem1 = result.getFirst();
        var resultItem2 = result.get(1);
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertNotNull(result),
                () -> assertEquals("2024-07-02T07:47:06.152282664", resultItem1.getDateMiseAJour()),
                () -> assertEquals("\"Provisoire, jamais publiée\"", resultItem1.getStatutValidation()),
                () -> assertEquals("dsd1000", resultItem1.getId()),
                () -> assertEquals("2025-02-19T14:48:49.041372902", resultItem2.getDateMiseAJour()),
                () -> assertEquals("\"Provisoire, jamais publiée\"", resultItem2.getStatutValidation()),
                () -> assertEquals("dsd1001", resultItem2.getId())
        );
    }

    //    /structures?dateMiseAJour=2025-01-01
    @Test
    void should_return_2_structures_when_structures_dateMiseAJour20250101() {
        var response = endpoints.getAllStructures(LocalDate.of(2025,1,1));
        var result = response.getBody();
        var resultItem1 = result.getFirst();
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.size()),
                () -> assertEquals("2025-02-19T14:48:49.041372902", resultItem1.getDateMiseAJour()),
                () -> assertEquals("\"Provisoire, jamais publiée\"", resultItem1.getStatutValidation()),
                () -> assertEquals("dsd1001", resultItem1.getId())
                );
    }

    /////////////////////////////////////////////////////////
    ///        /structure/{id}                           ///
    /////////////////////////////////////////////////////////

    // /structure/dsd1000
    @Test
    void should_return_structuredsd1000_when_structure_dsd1000() {
        var response = endpoints.getStructure("dsd1000", false);
        var result = response.getBody();
        assertNotNull(result);
        assertAll(
                () -> assertEquals("2024-07-02T07:47:06.152282664", result.getDateMiseAJour()),
                () -> assertEquals("2024-07-02T07:47:06.152238061", result.getDateCreation()),
                () -> assertEquals("TEST_NOT_FOR_PUBLICATION", result.getNotation()),
                () -> assertEquals("\"Provisoire, jamais publiée\"", result.getStatutValidation()),
                () -> assertEquals(List.of(), result.getMesures()),
                () -> assertEquals("dsd1000", result.getId()),
                () -> assertEquals("fr", result.getLabel().getFirst().getLangue()),
                () -> assertEquals("Test (à ne pas publier)", result.getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getLabel().get(1).getLangue()),
                () -> assertEquals("Test (not for publication)", result.getLabel().get(1).getContenu()),
                () -> assertEquals("http://bauhaus/structuresDeDonnees/structure/dsd1000", result.getUri()),
                () -> assertEquals(List.of(), result.getAttributs()),
                () -> assertEquals(List.of(), result.getDimensions())
        );
    }


    // /structure/dsd1000?dateMiseAJour=true
    @Test
    void should_return_structuredsd1000_when_structure_dsd1000_dateMiseAJourTrue() {
        var response = endpoints.getStructure("dsd1000", false);
        var result = response.getBody();
        assertNotNull(result);
        assertAll(
                () -> assertEquals("2024-07-02T07:47:06.152282664", result.getDateMiseAJour()),
                () -> assertEquals("dsd1000", result.getId())
        );
    }


    /////////////////////////////////////////////////////////
    ///        /composants                                ///
    /////////////////////////////////////////////////////////

    // /composants
    @Test
    void should_return_0_composants_when_composants_dateMiseAJourNull() {
        var response = endpoints.getAllStructures(null);
        var result = response.getBody();
        assertNotNull(result);
        assertEquals(0, result.size());

    }

    // /composants?dateMiseAJour=2025-01-01
    @Test
    void should_return_0_composants_when_composants_dateMiseAJour20250101() {
        var response = endpoints.getAllStructures(LocalDate.of(2025,1,1));
        var result = response.getBody();
        assertNotNull(result);
        assertEquals(0, result.size());

    }


    /////////////////////////////////////////////////////////
    ///        /composant/{id}                            ///
    /////////////////////////////////////////////////////////

    // pas de composants en dev2, on ne peut tester qu'un 404
    @Test
    void should_return_404_when_Composant_c1_dateFalse() throws Exception{
        mockMvc.perform(get("/compossant")
                        .param("id", "c1"))
                .andExpect(status().isNotFound());
    }



// //structure/{id}/sliceKeys
    //les 2 structures de dev2 n'ont aucune slicekeys
    @Test
    void should_return_0_sliceKeyscomposants_when_structure_dsd1000() {
        var response = endpoints.getSliceKeys("dsd1000");
        var result = response.getBody();
        assertNotNull(result);
        assertEquals(0, result.size());

    }


}