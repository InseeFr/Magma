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
    void should_return_serieById_s1001_when_getSerieById_s1001() {
        var response = endpoints.getSerieById("s1001");
        var result = response.getBody();

        assertNotNull(result);
        assertAll(
                // identifiants
                () -> assertEquals("s1001", result.getSeriesId()),
                () -> assertEquals("http://id.insee.fr/operations/serie/s1001", result.getUri()),

                // label
                () -> assertEquals(2, result.getLabel().size()),
                () -> assertEquals("fr", result.getLabel().getFirst().getLangue()),
                () -> assertEquals("Enquête innovation", result.getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getLabel().get(1).getLangue()),
                () -> assertEquals("Innovation survey", result.getLabel().get(1).getContenu()),

                // altLabel
                () -> assertEquals(2, result.getAltLabel().size()),
                () -> assertEquals("fr", result.getAltLabel().getFirst().getLangue()),
                () -> assertEquals("Série alt", result.getAltLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getAltLabel().get(1).getLangue()),
                () -> assertEquals("Alt series", result.getAltLabel().get(1).getContenu()),

                // resume (from dcterms:abstract)
                () -> assertEquals(2, result.getResume().size()),
                () -> assertEquals("fr", result.getResume().getFirst().getLangue()),
                () -> assertEquals("Résumé de la série", result.getResume().getFirst().getContenu()),
                () -> assertEquals("en", result.getResume().get(1).getLangue()),
                () -> assertEquals("Series abstract", result.getResume().get(1).getContenu()),

                // noteHistorique
                () -> assertEquals(2, result.getNoteHistorique().size()),
                () -> assertEquals("fr", result.getNoteHistorique().getFirst().getLangue()),
                () -> assertEquals("Note historique", result.getNoteHistorique().getFirst().getContenu()),
                () -> assertEquals("en", result.getNoteHistorique().get(1).getLangue()),
                () -> assertEquals("Historical note", result.getNoteHistorique().get(1).getContenu()),

                // type
                () -> assertNotNull(result.getType()),
                () -> assertEquals("E", result.getType().getId()),
                () -> assertEquals("http://id.insee.fr/concepts/type/E", result.getType().getUri()),
                () -> assertEquals("fr", result.getType().getLabel().getFirst().getLangue()),
                () -> assertEquals("Enquête", result.getType().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getType().getLabel().get(1).getLangue()),
                () -> assertEquals("Survey", result.getType().getLabel().get(1).getContenu()),

                // frequenceCollecte
                () -> assertNotNull(result.getFrequenceCollecte()),
                () -> assertEquals("A", result.getFrequenceCollecte().getId()),
                () -> assertEquals("http://id.insee.fr/concepts/periodicity/A", result.getFrequenceCollecte().getUri()),
                () -> assertEquals("fr", result.getFrequenceCollecte().getLabel().getFirst().getLangue()),
                () -> assertEquals("Annuelle", result.getFrequenceCollecte().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getFrequenceCollecte().getLabel().get(1).getLangue()),
                () -> assertEquals("Annual", result.getFrequenceCollecte().getLabel().get(1).getContenu()),

                // famille
                () -> assertNotNull(result.getFamille()),
                () -> assertEquals("f1001", result.getFamille().getId()),
                () -> assertEquals("http://id.insee.fr/operations/famille/f1001", result.getFamille().getUri()),
                () -> assertEquals("fr", result.getFamille().getLabel().getFirst().getLangue()),
                () -> assertEquals("Famille de test", result.getFamille().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getFamille().getLabel().get(1).getLangue()),
                () -> assertEquals("Test family", result.getFamille().getLabel().get(1).getContenu()),

                // seriesPrecedentes
                () -> assertEquals(1, result.getSeriesPrecedentes().size()),
                () -> assertEquals("s1010", result.getSeriesPrecedentes().getFirst().getId()),
                () -> assertEquals("http://id.insee.fr/operations/serie/s1010", result.getSeriesPrecedentes().getFirst().getUri()),
                () -> assertEquals("fr", result.getSeriesPrecedentes().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("Enquête sur les compétences pour innover", result.getSeriesPrecedentes().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getSeriesPrecedentes().getFirst().getLabel().get(1).getLangue()),
                () -> assertEquals("Innovative skills survey", result.getSeriesPrecedentes().getFirst().getLabel().get(1).getContenu()),

                // seriesSuivantes
                () -> assertEquals(1, result.getSeriesSuivantes().size()),

                // seriesLiees
                () -> assertEquals(2, result.getSeriesLiees().size()),
                () -> assertEquals("s1197", result.getSeriesLiees().getFirst().getId()),
                () -> assertEquals("http://id.insee.fr/operations/serie/s1197", result.getSeriesLiees().getFirst().getUri()),
                () -> assertEquals("fr", result.getSeriesLiees().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("Système immatriculation au répertoire des unités statistiques", result.getSeriesLiees().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getSeriesLiees().getFirst().getLabel().get(1).getLangue()),
                () -> assertEquals("Statistical business register", result.getSeriesLiees().getFirst().getLabel().get(1).getContenu()),
                () -> assertEquals("s1198", result.getSeriesLiees().get(1).getId()),

                // operations
                () -> assertEquals(2, result.getOperations().size()),
                () -> assertEquals("op2024", result.getOperations().getFirst().getId()),
                () -> assertEquals("http://id.insee.fr/operations/operation/op2024", result.getOperations().getFirst().getUri()),
                () -> assertEquals("Opération 2024", result.getOperations().getFirst().getLabel().getFirst().getContenu()),

                // proprietaires (dc:creator)
                () -> assertEquals(1, result.getProprietaires().size()),
                () -> assertEquals("insee", result.getProprietaires().getFirst().getId()),
                () -> assertEquals("Institut national de la statistique", result.getProprietaires().getFirst().getLabel().getFirst().getContenu()),

                // organismesResponsables (dcterms:publisher)
                () -> assertEquals(1, result.getOrganismesResponsables().size()),
                () -> assertEquals("drees", result.getOrganismesResponsables().getFirst().getId()),

                // partenaires (dcterms:contributor)
                () -> assertEquals(1, result.getPartenaires().size()),
                () -> assertEquals("dares", result.getPartenaires().getFirst().getId()),

                // dates
                () -> assertEquals("2020-01-15", result.getDateCreation()),
                () -> assertEquals("2024-06-01", result.getDateMiseAJour()),

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
    void should_return_operationById_s2193_when_getOperationByCode_s2193() {
        var response = endpoints.getOperationByCode("s2193");
        var result = response.getBody();

        assertNotNull(result);
        assertAll(
                // identifiants
                () -> assertEquals("s2193", result.getId()),
                () -> assertEquals("http://id.insee.fr/operations/operation/s2193", result.getUri()),

                // label
                () -> assertEquals(2, result.getLabel().size()),
                () -> assertEquals("fr", result.getLabel().getFirst().getLangue()),
                () -> assertEquals("Enquête capacité à innover et stratégie 2024", result.getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getLabel().get(1).getLangue()),
                () -> assertEquals("Community Innovation Survey 2024", result.getLabel().get(1).getContenu()),

                // altLabel
                () -> assertEquals(2, result.getAltLabel().size()),
                () -> assertEquals("fr", result.getAltLabel().getFirst().getLangue()),
                () -> assertEquals("CIS 2024", result.getAltLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getAltLabel().get(1).getLangue()),
                () -> assertEquals("CIS 2024", result.getAltLabel().get(1).getContenu()),

                // millesime
                () -> assertEquals("2024", result.getMillesime()),

                // serie
                () -> assertNotNull(result.getSerie()),
                () -> assertEquals("s1001", result.getSerie().getId()),
                () -> assertEquals("http://id.insee.fr/operations/serie/s1001", result.getSerie().getUri()),
                () -> assertEquals("fr", result.getSerie().getLabel().getFirst().getLangue()),
                () -> assertEquals("Enquête innovation", result.getSerie().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getSerie().getLabel().get(1).getLangue()),
                () -> assertEquals("Innovation survey", result.getSerie().getLabel().get(1).getContenu()),

                // rapportQualite
                () -> assertNotNull(result.getRapportQualite()),
                () -> assertEquals("2203", result.getRapportQualite().getId()),
                () -> assertEquals("http://id.insee.fr/qualite/rapport/2203", result.getRapportQualite().getUri()),

                // dates
                () -> assertEquals(LocalDate.of(2025,1,22), result.getDateCreation()),
                () -> assertEquals(LocalDate.of(2025,4,2), result.getDateMiseAJour())
        );
    }
}
