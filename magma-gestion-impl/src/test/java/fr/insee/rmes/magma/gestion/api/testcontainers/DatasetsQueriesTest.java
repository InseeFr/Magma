package fr.insee.rmes.magma.gestion.api.testcontainers;

import fr.insee.rmes.magma.gestion.api.DatasetsEndpoints;
import fr.insee.rmes.magma.gestion.model.DataSet;
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
                 () -> assertNull(result.getCreator()),

                // title
                () -> assertEquals(2, result.getTitle().size()),
                () -> assertEquals("fr", result.getTitle().getFirst().getLangue()),
                () -> assertEquals("Activité, emploi et chômage - séries longues", result.getTitle().getFirst().getContenu()),
                () -> assertEquals("en", result.getTitle().get(1).getLangue()),
                () -> assertEquals("Activité, emploi et chômage - séries longues", result.getTitle().get(1).getContenu()),

                // subtitle
                () -> assertNull(result.getSubtitle()),

                // abstract
                () -> assertEquals(2, result.getAbstract().size()),
                () -> assertEquals("fr", result.getAbstract().getFirst().getLangue()),
                () -> assertEquals("Séries longues sur la population active, l'emploi, le chômage et le halo autour du chômage.", result.getAbstract().getFirst().getContenu()),
                () -> assertEquals("en", result.getAbstract().get(1).getLangue()),
                () -> assertNull(result.getAbstract().get(1).getContenu()),

                // description
                () -> assertEquals(2, result.getDescription().size()),
                () -> assertEquals("fr", result.getDescription().getFirst().getLangue()),
                () -> assertTrue(result.getDescription().getFirst().getContenu().startsWith("Les données sont détaillées par sexe")),
                () -> assertEquals("en", result.getDescription().get(1).getLangue()),
                () -> assertNull(result.getDescription().get(1).getContenu()),

                // scopeNote
                () -> assertEquals(2, result.getScopeNote().size()),
                () -> assertEquals("fr", result.getScopeNote().getFirst().getLangue()),
                () -> assertTrue(result.getScopeNote().getFirst().getContenu().startsWith("Des corrections sur les données")),
                () -> assertEquals("en", result.getScopeNote().get(1).getLangue()),
                () -> assertNull(result.getScopeNote().get(1).getContenu()),

                // wasGeneratedBy
                () -> assertEquals(1, result.getWasGeneratedBy().size()),
                () -> assertEquals("http://bauhaus/operations/serie/s1223", result.getWasGeneratedBy().getFirst().getId()),

                // listes vides
                () -> assertNull(result.getTheme()),
                () -> assertNull(result.getArchiveUnit()),
                () -> assertNull(result.getRelations()),
                () -> assertNull(result.getKeyword()),
                () -> assertNull(result.getLandingPage()),
                () -> assertNull(result.getTemporalResolution()),
                () -> assertNull(result.getSpatialResolution())
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
    ///        /datasets/list                              ///
    /////////////////////////////////////////////////////////

    static final String DATASET_ID_2 = "a85dab6c-ef0f-4caa-9763-c7f8c840e216";
    static final String DATASET_URI_2 = "http://bauhaus/catalogues/jeuDeDonnees/a85dab6c-ef0f-4caa-9763-c7f8c840e216";

    @Test
    void should_return_all_datasets() {
        var response = endpoints.getListDatasets(null);
        var result = response.getBody();

        assertNotNull(result);
        assertEquals(2, result.size());

        var ids = result.stream().map(DataSet::getId).toList();
        assertTrue(ids.contains(DATASET_ID));
        assertTrue(ids.contains(DATASET_ID_2));
    }

    @Test
    void should_return_dataset_list_with_correct_fields() {
        var response = endpoints.getListDatasets(null);
        var result = response.getBody();

        assertNotNull(result);

        var ds1 = result.stream().filter(d -> DATASET_ID.equals(d.getId())).findFirst().orElseThrow();
        var ds2 = result.stream().filter(d -> DATASET_ID_2.equals(d.getId())).findFirst().orElseThrow();

        assertAll(
                // dataset 1
                () -> assertEquals(DATASET_URI, ds1.getUri()),
                () -> assertEquals("Unpublished", ds1.getValidationState()),
                () -> assertEquals(2, ds1.getTitle().size()),
                () -> assertEquals("fr", ds1.getTitle().getFirst().getLangue()),
                () -> assertEquals("Activité, emploi et chômage - séries longues", ds1.getTitle().getFirst().getContenu()),

                // dataset 2
                () -> assertEquals(DATASET_URI_2, ds2.getUri()),
                () -> assertEquals("Unpublished", ds2.getValidationState()),
                () -> assertEquals(2, ds2.getTitle().size()),
                () -> assertEquals("fr", ds2.getTitle().getFirst().getLangue()),
                () -> assertEquals("Activité des branches de l'économie", ds2.getTitle().getFirst().getContenu())
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