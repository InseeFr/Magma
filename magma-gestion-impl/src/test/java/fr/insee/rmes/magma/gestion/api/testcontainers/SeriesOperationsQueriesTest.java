package fr.insee.rmes.magma.gestion.api.testcontainers;

import fr.insee.rmes.magma.gestion.api.SeriesOperationsEndpoints;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "--spring.profiles.active=security.disabled")
@AutoConfigureMockMvc
@Tag("integration")
class SeriesOperationsQueriesTest extends TestcontainerTest {

    @Autowired
    SeriesOperationsEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////
    ///        /operations/serie/{id}                     ///
    /////////////////////////////////////////////////////////

    @Test
    void should_return_serieById_idSeriePrincipaleTest_when_getSerieById_idSeriePrincipaleTest() {
        var response = endpoints.getSerieById("idSeriePrincipaleTest");
        var result = response.getBody();

        assertNotNull(result);
        assertAll(
                // identifiants
                () -> assertEquals("idSeriePrincipaleTest", result.getSeriesId()),
                () -> assertEquals("http://bauhaus/operations/serie/idSeriePrincipaleTest", result.getUri()),

                // label
                () -> assertEquals(2, result.getLabel().size()),
                () -> assertEquals("fr", result.getLabel().getFirst().getLangue()),
                () -> assertEquals("Label Série fr", result.getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getLabel().get(1).getLangue()),
                () -> assertEquals("Series Label en", result.getLabel().get(1).getContenu()),

                // altLabel
                () -> assertEquals(2, result.getAltLabel().size()),
                () -> assertEquals("fr", result.getAltLabel().getFirst().getLangue()),
                () -> assertEquals("AltLabel Série fr", result.getAltLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getAltLabel().get(1).getLangue()),
                () -> assertEquals("Series AltLabel en", result.getAltLabel().get(1).getContenu()),

                // resume
                () -> assertEquals(2, result.getResume().size()),
                () -> assertEquals("fr", result.getResume().getFirst().getLangue()),
                () -> assertEquals("Résumé de la série test", result.getResume().getFirst().getContenu()),
                () -> assertEquals("en", result.getResume().get(1).getLangue()),
                () -> assertEquals("Test series abstract", result.getResume().get(1).getContenu()),

                // noteHistorique
                () -> assertEquals(2, result.getNoteHistorique().size()),
                () -> assertEquals("fr", result.getNoteHistorique().getFirst().getLangue()),
                () -> assertEquals("Note historique test", result.getNoteHistorique().getFirst().getContenu()),
                () -> assertEquals("en", result.getNoteHistorique().get(1).getLangue()),
                () -> assertEquals("Historical note test", result.getNoteHistorique().get(1).getContenu()),

                // type
                () -> assertNotNull(result.getType()),
                () -> assertEquals("E", result.getType().getId()),
                () -> assertEquals("http://bauhaus/concepts/type/E", result.getType().getUri()),
                () -> assertEquals("fr", result.getType().getLabel().getFirst().getLangue()),
                () -> assertEquals("Enquête", result.getType().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getType().getLabel().get(1).getLangue()),
                () -> assertEquals("Survey", result.getType().getLabel().get(1).getContenu()),

                // frequenceCollecte
                () -> assertNotNull(result.getFrequenceCollecte()),
                () -> assertEquals("A", result.getFrequenceCollecte().getId()),
                () -> assertEquals("http://bauhaus/concepts/periodicity/A", result.getFrequenceCollecte().getUri()),
                () -> assertEquals("fr", result.getFrequenceCollecte().getLabel().getFirst().getLangue()),
                () -> assertEquals("Annuelle", result.getFrequenceCollecte().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getFrequenceCollecte().getLabel().get(1).getLangue()),
                () -> assertEquals("Annual", result.getFrequenceCollecte().getLabel().get(1).getContenu()),

                // famille
                () -> assertNotNull(result.getFamille()),
                () -> assertEquals("idFamilleTest", result.getFamille().getId()),
                () -> assertEquals("http://bauhaus/operations/famille/idFamilleTest", result.getFamille().getUri()),
                () -> assertEquals("fr", result.getFamille().getLabel().getFirst().getLangue()),
                () -> assertEquals("Label Famille fr", result.getFamille().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getFamille().getLabel().get(1).getLangue()),
                () -> assertEquals("Family Label en", result.getFamille().getLabel().get(1).getContenu()),

                // seriesPrecedentes
                () -> assertEquals(1, result.getSeriesPrecedentes().size()),
                () -> assertEquals("idSeriePrecedenteTest", result.getSeriesPrecedentes().getFirst().getId()),
                () -> assertEquals("http://bauhaus/operations/serie/idSeriePrecedenteTest", result.getSeriesPrecedentes().getFirst().getUri()),
                () -> assertEquals("fr", result.getSeriesPrecedentes().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("Label Série Précédente fr", result.getSeriesPrecedentes().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getSeriesPrecedentes().getFirst().getLabel().get(1).getLangue()),
                () -> assertEquals("Previous Series Label en", result.getSeriesPrecedentes().getFirst().getLabel().get(1).getContenu()),

                // seriesSuivantes
                () -> assertEquals(1, result.getSeriesSuivantes().size()),

                // seriesLiees
                () -> assertEquals(2, result.getSeriesLiees().size()),
                () -> assertEquals("idSerieLiee1Test", result.getSeriesLiees().getFirst().getId()),
                () -> assertEquals("http://bauhaus/operations/serie/idSerieLiee1Test", result.getSeriesLiees().getFirst().getUri()),
                () -> assertEquals("fr", result.getSeriesLiees().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("Label Série Liée 1 fr", result.getSeriesLiees().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getSeriesLiees().getFirst().getLabel().get(1).getLangue()),
                () -> assertEquals("Related Series 1 Label en", result.getSeriesLiees().getFirst().getLabel().get(1).getContenu()),
                () -> assertEquals("idSerieLiee2Test", result.getSeriesLiees().get(1).getId()),

                // operations
                () -> assertEquals(2, result.getOperations().size()),
                () -> assertEquals("idOperation1SeriePrincipaleTest", result.getOperations().getFirst().getId()),
                () -> assertEquals("http://bauhaus/operations/operation/idOperation1SeriePrincipaleTest", result.getOperations().getFirst().getUri()),
                () -> assertEquals("Label Opération 1 fr", result.getOperations().getFirst().getLabel().getFirst().getContenu()),

                // proprietaires
                () -> assertEquals(1, result.getProprietaires().size()),
                () -> assertEquals("idOrganisationProprietaireTest", result.getProprietaires().getFirst().getId()),
                () -> assertEquals("Label Organisation Propriétaire fr", result.getProprietaires().getFirst().getLabel().getFirst().getContenu()),

                // organismesResponsables
                () -> assertEquals(1, result.getOrganismesResponsables().size()),
                () -> assertEquals("idOrganisationResponsableTest", result.getOrganismesResponsables().getFirst().getId()),

                // partenaires
                () -> assertEquals(1, result.getPartenaires().size()),
                () -> assertEquals("idOrganisationPartenaireTest", result.getPartenaires().getFirst().getId()),

                // dates
                () -> assertEquals(LocalDate.of(2020, 1, 15), result.getDateCreation()),
                () -> assertEquals(LocalDate.of(2024, 6, 1), result.getDateMiseAJour()),

                // statut
                () -> assertEquals("Publiée", result.getStatutValidation())
        );
    }


    @Test
    void should_return_404_when_getSerieById_unknown_id() throws Exception{
        mockMvc.perform(get("/operations/serie/serieInconnue"))
                .andExpect(status().isNotFound());
    }

    /////////////////////////////////////////////////////////
    ///        /operations/operation/{id}                 ///
    /////////////////////////////////////////////////////////

    @Test
    void should_return_operationById_idOperationTest_when_getOperationByCode_idOperationTest() {
        var response = endpoints.getOperationByCode("idOperationTest");
        var result = response.getBody();

        assertNotNull(result);
        assertAll(
                () -> assertEquals("idOperationTest", result.getId()),
                () -> assertEquals("http://bauhaus/operations/operation/idOperationTest", result.getUri()),

                () -> assertEquals(2, result.getLabel().size()),
                () -> assertEquals("fr", result.getLabel().getFirst().getLangue()),
                () -> assertEquals("Label Operation Test fr", result.getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getLabel().get(1).getLangue()),
                () -> assertEquals("Test Operation Label en", result.getLabel().get(1).getContenu()),

                () -> assertEquals(2, result.getAltLabel().size()),
                () -> assertEquals("fr", result.getAltLabel().getFirst().getLangue()),
                () -> assertEquals("AltLabel Operation Test fr", result.getAltLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getAltLabel().get(1).getLangue()),
                () -> assertEquals("Operation AltLabel en", result.getAltLabel().get(1).getContenu()),

                () -> assertEquals("2026", result.getMillesime()),

                () -> assertNotNull(result.getSerie()),
                () -> assertEquals("idSerieTest", result.getSerie().getId()),
                () -> assertEquals("http://bauhaus/operations/serie/idSerieTest", result.getSerie().getUri()),
                () -> assertEquals("fr", result.getSerie().getLabel().getFirst().getLangue()),
                () -> assertEquals("Label Serie fr", result.getSerie().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getSerie().getLabel().get(1).getLangue()),
                () -> assertEquals("Series Label en", result.getSerie().getLabel().get(1).getContenu()),

                () -> assertNotNull(result.getRapportQualite()),
                () -> assertEquals("idRapportQualiteTest", result.getRapportQualite().getId()),
                () -> assertEquals("http://bauhaus/qualite/rapport/idRapportQualiteTest", result.getRapportQualite().getUri()),

                () -> assertEquals(LocalDate.of(2025, 1, 22), result.getDateCreation()),
                () -> assertEquals(LocalDate.of(2025, 4, 2), result.getDateMiseAJour()),

                () -> assertEquals("Validated", result.getStatutValidation())
        );
    }

    @Test
    void should_return_404_when_getOperationById_unknown_id() throws Exception{
        mockMvc.perform(get("/operations/operation/serieInconnue"))
                .andExpect(status().isNotFound());
    }

    /////////////////////////////////////////////////////////
    ///        /operations/series                         ///
    /////////////////////////////////////////////////////////

    @Test
    void should_return_all_series_when_getAllSeries_without_dateFilter() {
        var response = endpoints.getAllSeries(null);
        var result = response.getBody();

        assertNotNull(result);
        assertAll(
                () -> assertEquals(6, result.size()),

                // Première série par ordre alphabétique : idSerieLiee1Test
                () -> assertEquals("idSerieLiee1Test", result.getFirst().getSeriesId()),
                () -> assertEquals("http://bauhaus/operations/serie/idSerieLiee1Test", result.getFirst().getUri()),
                () -> assertEquals(2, result.getFirst().getLabel().size()),
                () -> assertEquals("fr", result.getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("Label Série Liée 1 fr", result.getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getFirst().getLabel().get(1).getLangue()),
                () -> assertEquals("Related Series 1 Label en", result.getFirst().getLabel().get(1).getContenu()),

                // Dernière série par ordre alphabétique : idSerieTest
                () -> assertEquals("idSerieTest", result.get(5).getSeriesId()),
                () -> assertEquals("http://bauhaus/operations/serie/idSerieTest", result.get(5).getUri())
        );
    }

    @Test
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
    void should_return_filtered_series_when_getAllSeries_with_date_2024_01_01() {
        var response = endpoints.getAllSeries("2024-01-01");
        var result = response.getBody();

        assertNotNull(result);
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("idSeriePrincipaleTest", result.get(0).getSeriesId()),
                () -> assertEquals("idSerieTest", result.get(1).getSeriesId())
        );
    }

    @Test
    void should_return_200_when_getAllSeries() throws Exception {
        mockMvc.perform(get("/operations/series"))
                .andExpect(status().isOk());
    }

}
