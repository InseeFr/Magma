package fr.insee.rmes.magma.gestion.api.testcontainers;

import fr.insee.rmes.magma.gestion.api.DatasetsEndpoints;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "--spring.profiles.active=security.disabled")
@AutoConfigureMockMvc
@Tag("integration")
class DatasetsQueriesTest extends TestcontainerTest {

    static final String DATASET_ID = "25baaf1f-61cf-4d50-9124-1477afca2aa4";
    static final String DATASET_URI = "http://bauhaus/catalogues/jeuDeDonnees/25baaf1f-61cf-4d50-9124-1477afca2aa4";

    @Autowired
    DatasetsEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////
    ///        /dataset/{id}?dateMiseAJour=false           ///
    /////////////////////////////////////////////////////////

    @Test
    void should_return_full_dataset_when_getDataSetById_dateMiseAJour_false() {
        var response = endpoints.getDataSetById(DATASET_ID, false);
        var result = response.getBody();

        assertNotNull(result);
        assertAll(
                // identifiants
                () -> assertEquals(DATASET_ID, result.getId()),
                () -> assertEquals(DATASET_URI, result.getUri()),

                // catalogue record
                () -> assertEquals("2024-12-09T12:00:00.000", result.getCatalogRecordCreated()),
                () -> assertEquals("2024-12-09T12:00:00.000", result.getCatalogRecordModified()),
                () -> assertEquals("DG75-L201", result.getCatalogRecordCreator()),
                () -> assertEquals("DG75-L201", result.getCatalogRecordContributor()),

                // identifier ADMS
                () -> assertEquals("DD_EEC_SERIES", result.getIdentifier()),

                // statut
                () -> assertEquals("Provisoire, jamais publiée", result.getValidationState()),

                // creator (vide : aucun dcterms:creator avec identifiant résolu)
                () -> assertNotNull(result.getCreator()),
                () -> assertTrue(result.getCreator().isEmpty()),

                // title
                () -> assertEquals(2, result.getTitle().size()),
                () -> assertEquals("fr", result.getTitle().getFirst().getLang()),
                () -> assertEquals("Activité, emploi et chômage - séries longues", result.getTitle().getFirst().getContent()),
                () -> assertEquals("en", result.getTitle().get(1).getLang()),
                () -> assertEquals("Activité, emploi et chômage - séries longues", result.getTitle().get(1).getContent()),

                // subtitle (non renseigné → contenu null)
                () -> assertEquals(2, result.getSubtitle().size()),
                () -> assertEquals("fr", result.getSubtitle().getFirst().getLang()),
                () -> assertNull(result.getSubtitle().getFirst().getContent()),
                () -> assertEquals("en", result.getSubtitle().get(1).getLang()),
                () -> assertNull(result.getSubtitle().get(1).getContent()),

                // abstract
                () -> assertEquals(2, result.getAbstract().size()),
                () -> assertEquals("fr", result.getAbstract().getFirst().getLang()),
                () -> assertEquals("Séries longues sur la population active, l'emploi, le chômage et le halo autour du chômage.", result.getAbstract().getFirst().getContent()),
                () -> assertEquals("en", result.getAbstract().get(1).getLang()),
                () -> assertNull(result.getAbstract().get(1).getContent()),

                // description
                () -> assertEquals(2, result.getDescription().size()),
                () -> assertEquals("fr", result.getDescription().getFirst().getLang()),
                () -> assertTrue(result.getDescription().getFirst().getContent().startsWith("Les données sont détaillées par sexe")),
                () -> assertEquals("en", result.getDescription().get(1).getLang()),
                () -> assertNull(result.getDescription().get(1).getContent()),

                // scopeNote
                () -> assertEquals(2, result.getScopeNote().size()),
                () -> assertEquals("fr", result.getScopeNote().getFirst().getLang()),
                () -> assertTrue(result.getScopeNote().getFirst().getContent().startsWith("Des corrections sur les données")),
                () -> assertEquals("en", result.getScopeNote().get(1).getLang()),
                () -> assertNull(result.getScopeNote().get(1).getContent()),

                // wasGeneratedBy
                () -> assertEquals(1, result.getWasGeneratedBy().size()),
                () -> assertEquals("http://bauhaus/operations/serie/s1223", result.getWasGeneratedBy().getFirst().getId()),

                // listes vides
                () -> assertTrue(result.getTheme().isEmpty()),
                () -> assertTrue(result.getArchiveUnit().isEmpty()),
                () -> assertTrue(result.getRelations().isEmpty()),
                () -> assertTrue(result.getKeyword().isEmpty()),
                () -> assertTrue(result.getLandingPage().isEmpty()),
                () -> assertTrue(result.getTemporalResolution().isEmpty()),
                () -> assertTrue(result.getSpatialResolution().isEmpty())
        );
    }

    /////////////////////////////////////////////////////////
    ///        /dataset/{id}?dateMiseAJour=true            ///
    /////////////////////////////////////////////////////////

    @Test
    void should_return_summary_dataset_when_getDataSetById_dateMiseAJour_true() {
        var response = endpoints.getDataSetById(DATASET_ID, true);
        var result = response.getBody();

        assertNotNull(result);
        assertAll(
                () -> assertEquals(DATASET_ID, result.getId()),
                () -> assertEquals(DATASET_URI, result.getUri()),
                () -> assertEquals("2024-12-09T12:00:00.000", result.getCatalogRecordModified())
        );
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