package fr.insee.rmes.magma.gestion.unitTests;

import fr.insee.rmes.magma.gestion.services.SeriesOperationsServiceImpl;
import fr.insee.rmes.magma.gestion.utils.OperationDTO;
import fr.insee.rmes.magma.gestion.utils.SeriesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SeriesOperationsServiceImplTest {

    private SeriesOperationsServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new SeriesOperationsServiceImpl();
    }

    // =========================================================
    //   transformSeriesDTOToSerieById
    // =========================================================

    @Test
    void should_map_basic_serie_fields() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertEquals("s1223", result.getSeriesId()),
                () -> assertEquals("http://bauhaus/operations/serie/s1223", result.getUri()),
                () -> assertEquals("Publiée", result.getStatutValidation()),
                () -> assertEquals(LocalDate.of(2020,1,15), result.getDateCreation()),
                () -> assertEquals(LocalDate.of(2024,6,1), result.getDateMiseAJour())
        );
    }

    @Test
    void should_map_multilingual_serie_labels() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertEquals(2, result.getLabel().size()),
                () -> assertEquals("fr", result.getLabel().getFirst().getLangue()),
                () -> assertEquals("Enquête emploi FR", result.getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getLabel().get(1).getLangue()),
                () -> assertEquals("Employment survey EN", result.getLabel().get(1).getContenu()),

                () -> assertEquals("EE FR", result.getAltLabel().getFirst().getContenu()),
                () -> assertEquals("ES EN", result.getAltLabel().get(1).getContenu()),

                () -> assertEquals("Résumé FR", result.getResume().getFirst().getContenu()),
                () -> assertEquals("Abstract EN", result.getResume().get(1).getContenu()),

                () -> assertEquals("Note historique FR", result.getNoteHistorique().getFirst().getContenu()),
                () -> assertEquals("History note EN", result.getNoteHistorique().get(1).getContenu())
        );
    }

    @Test
    void should_map_type_when_present() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertNotNull(result.getType()),
                () -> assertEquals("t1", result.getType().getId()),
                () -> assertEquals("http://type/t1", result.getType().getUri()),
                () -> assertEquals("Enquête FR", result.getType().getLabel().getFirst().getContenu()),
                () -> assertEquals("Survey EN", result.getType().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_type_when_null() {
        assertNull(service.transformSeriesDTOToSerieById(minimalSeriesDTO()).getType());
    }

    @Test
    void should_map_frequence_when_periodicity_present() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertNotNull(result.getFrequenceCollecte()),
                () -> assertEquals("p1", result.getFrequenceCollecte().getId()),
                () -> assertEquals("http://periodicity/p1", result.getFrequenceCollecte().getUri()),
                () -> assertEquals("Annuelle FR", result.getFrequenceCollecte().getLabel().getFirst().getContenu()),
                () -> assertEquals("Annual EN", result.getFrequenceCollecte().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_frequence_when_periodicity_null() {
        assertNull(service.transformSeriesDTOToSerieById(minimalSeriesDTO()).getFrequenceCollecte());
    }

    @Test
    void should_map_famille_when_families_present() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertNotNull(result.getFamille()),
                () -> assertEquals("f1", result.getFamille().getId()),
                () -> assertEquals("http://family/f1", result.getFamille().getUri()),
                () -> assertEquals("Famille FR", result.getFamille().getLabel().getFirst().getContenu()),
                () -> assertEquals("Family EN", result.getFamille().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_famille_when_families_null() {
        assertNull(service.transformSeriesDTOToSerieById(minimalSeriesDTO()).getFamille());
    }

    @Test
    void should_map_rapportQualite_when_simsId_present() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertNotNull(result.getRapportQualite()),
                () -> assertEquals("sims1", result.getRapportQualite().getId()),
                () -> assertEquals("http://sims/sims1", result.getRapportQualite().getUri())
        );
    }

    @Test
    void should_not_set_rapportQualite_when_simsId_null() {
        assertNull(service.transformSeriesDTOToSerieById(minimalSeriesDTO()).getRapportQualite());
    }

    @Test
    void should_parse_operations_as_ref_list() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertEquals(2, result.getOperations().size()),
                () -> assertEquals("op1", result.getOperations().get(0).getId()),
                () -> assertEquals("http://op/op1", result.getOperations().get(0).getUri()),
                () -> assertEquals("Opération 1 FR", result.getOperations().get(0).getLabel().getFirst().getContenu()),
                () -> assertEquals("op2", result.getOperations().get(1).getId())
        );
    }

    @Test
    void should_return_empty_list_when_operations_null() {
        var result = service.transformSeriesDTOToSerieById(minimalSeriesDTO());
        assertNotNull(result.getOperations());
        assertTrue(result.getOperations().isEmpty());
    }

    @Test
    void should_parse_series_precedentes_suivantes_liees() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertEquals(1, result.getSeriesPrecedentes().size()),
                () -> assertEquals("prev1", result.getSeriesPrecedentes().getFirst().getId()),
                () -> assertEquals(1, result.getSeriesSuivantes().size()),
                () -> assertEquals("next1", result.getSeriesSuivantes().getFirst().getId()),
                () -> assertEquals(1, result.getSeriesLiees().size()),
                () -> assertEquals("see1", result.getSeriesLiees().getFirst().getId())
        );
    }

    @Test
    void should_parse_proprietaires_organismes_partenaires_services() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertEquals(1, result.getProprietaires().size()),
                () -> assertEquals("creator1", result.getProprietaires().getFirst().getId()),
                () -> assertEquals(1, result.getOrganismesResponsables().size()),
                () -> assertEquals("publisher1", result.getOrganismesResponsables().getFirst().getId()),
                () -> assertEquals(1, result.getPartenaires().size()),
                () -> assertEquals("contrib1", result.getPartenaires().getFirst().getId()),
                () -> assertEquals(1, result.getServicesCollecteurs().size()),
                () -> assertEquals("collector1", result.getServicesCollecteurs().getFirst().getId())
        );
    }

    @Test
    void should_return_empty_lists_for_refs_when_null() {
        var result = service.transformSeriesDTOToSerieById(minimalSeriesDTO());

        assertAll(
                () -> assertTrue(result.getSeriesPrecedentes().isEmpty()),
                () -> assertTrue(result.getSeriesSuivantes().isEmpty()),
                () -> assertTrue(result.getSeriesLiees().isEmpty()),
                () -> assertTrue(result.getIndicateurs().isEmpty()),
                () -> assertTrue(result.getProprietaires().isEmpty()),
                () -> assertTrue(result.getOrganismesResponsables().isEmpty()),
                () -> assertTrue(result.getPartenaires().isEmpty()),
                () -> assertTrue(result.getServicesCollecteurs().isEmpty())
        );
    }

    @Test
    void should_return_null_dates_when_created_and_modified_null() {
        var result = service.transformSeriesDTOToSerieById(minimalSeriesDTO());
        assertAll(
                () -> assertNull(result.getDateCreation()),
                () -> assertNull(result.getDateMiseAJour())
        );
    }

    // =========================================================
    //   transformOperationDTOToOperationById
    // =========================================================

    @Test
    void should_map_basic_operation_fields() {
        var result = service.transformOperationDTOToOperationById(fullOperationDTO());

        assertAll(
                () -> assertEquals("op1", result.getId()),
                () -> assertEquals("http://bauhaus/operations/op/op1", result.getUri()),
                () -> assertEquals("Publiée", result.getStatutValidation()),
                () -> assertEquals(LocalDate.of(2022,3,10), result.getDateCreation()),
                () -> assertEquals(LocalDate.of(2024,7,20), result.getDateMiseAJour()),
                () -> assertEquals("2023", result.getMillesime())
        );
    }

    @Test
    void should_map_multilingual_operation_labels() {
        var result = service.transformOperationDTOToOperationById(fullOperationDTO());

        assertAll(
                () -> assertEquals(2, result.getLabel().size()),
                () -> assertEquals("fr", result.getLabel().getFirst().getLangue()),
                () -> assertEquals("Opération FR", result.getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getLabel().get(1).getLangue()),
                () -> assertEquals("Operation EN", result.getLabel().get(1).getContenu()),

                () -> assertEquals("Op FR alt", result.getAltLabel().getFirst().getContenu()),
                () -> assertEquals("Op EN alt", result.getAltLabel().get(1).getContenu())
        );
    }

    @Test
    void should_map_serie_when_seriesId_present() {
        var result = service.transformOperationDTOToOperationById(fullOperationDTO());

        assertAll(
                () -> assertNotNull(result.getSerie()),
                () -> assertEquals("s1223", result.getSerie().getId()),
                () -> assertEquals("http://bauhaus/operations/serie/s1223", result.getSerie().getUri()),
                () -> assertEquals("Série FR", result.getSerie().getLabel().getFirst().getContenu()),
                () -> assertEquals("Series EN", result.getSerie().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_serie_when_seriesId_null() {
        assertNull(service.transformOperationDTOToOperationById(minimalOperationDTO()).getSerie());
    }

    @Test
    void should_map_rapportQualite_operation_when_simsId_present() {
        var result = service.transformOperationDTOToOperationById(fullOperationDTO());

        assertAll(
                () -> assertNotNull(result.getRapportQualite()),
                () -> assertEquals("sims2", result.getRapportQualite().getId()),
                () -> assertEquals("http://sims/sims2", result.getRapportQualite().getUri())
        );
    }

    @Test
    void should_not_set_rapportQualite_operation_when_simsId_null() {
        assertNull(service.transformOperationDTOToOperationById(minimalOperationDTO()).getRapportQualite());
    }

    @Test
    void should_return_null_dates_when_operation_created_and_modified_null() {
        var result = service.transformOperationDTOToOperationById(minimalOperationDTO());
        assertAll(
                () -> assertNull(result.getDateCreation()),
                () -> assertNull(result.getDateMiseAJour())
        );
    }

    // =========================================================
    //   Fixtures
    // =========================================================

    private SeriesDTO fullSeriesDTO() {
        return new SeriesDTO(
                "s1223",                                        // seriesId
                "http://bauhaus/operations/serie/s1223",        // series
                "Enquête emploi FR", "Employment survey EN",    // seriesLabelLg1, Lg2
                "EE FR", "ES EN",                               // seriesAltLabelLg1, Lg2
                "Résumé FR", "Abstract EN",                     // seriesAbstractLg1, Lg2
                "Note historique FR", "History note EN",        // seriesHistoryNoteLg1, Lg2
                "http://type/t1", "t1",                         // type, typeID
                "Enquête FR", "Survey EN",                      // typeLabelLg1, Lg2
                "http://periodicity/p1", "p1",                  // periodicity, periodicityId
                "Annuelle FR", "Annual EN",                     // periodicityLabelLg1, Lg2
                "f1$http://family/f1$Famille FR$Family EN",     // families
                "see1$http://see/see1$Liée FR$Linked EN",       // seeAlsoSeries
                "prev1$http://prev/prev1$Précédente FR$Previous EN", // previousSeries
                "next1$http://next/next1$Suivante FR$Next EN",  // nextSeries
                "op1$http://op/op1$Opération 1 FR$Operation 1 EN|op2$http://op/op2$Opération 2 FR$Operation 2 EN", // operations
                null,                                           // indicators
                "http://sims/sims1", "sims1",                  // sims, simsId
                LocalDate.of(2020, 1, 15),                      // created
                LocalDate.of(2024, 6, 1),                       // modified
                "creator1$http://creator/c1$Créateur FR$Creator EN",   // creators
                "publisher1$http://pub/p1$Éditeur FR$Publisher EN",    // publishers
                "contrib1$http://contrib/c1$Partenaire FR$Partner EN", // contributors
                "collector1$http://coll/c1$Collecteur FR$Collector EN", // dataCollectors
                "Publiée"                                        // validationState
        );
    }

    private SeriesDTO minimalSeriesDTO() {
        return new SeriesDTO(
                "s0", "http://bauhaus/operations/serie/s0",
                "Label FR", "Label EN",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null,
                null, null, null, null, null,
                null, null,
                null, null,
                null, null, null, null,
                null
        );
    }

    private OperationDTO fullOperationDTO() {
        return new OperationDTO(
                "op1",                                          // operationId
                "http://bauhaus/operations/op/op1",             // operation
                "Opération FR", "Operation EN",                 // operationLabelLg1, Lg2
                "Op FR alt", "Op EN alt",                       // operationAltLabelLg1, Lg2
                "2023",                                         // temporal
                "s1223",                                        // seriesId
                "http://bauhaus/operations/serie/s1223",        // series
                "Série FR", "Series EN",                        // seriesLabelLg1, Lg2
                "sims2", "http://sims/sims2",                   // simsId, sims
                LocalDate.of(2022, 3, 10),                      // created
                LocalDate.of(2024, 7, 20),                      // modified
                "Publiée"                                       // validationState
        );
    }

    private OperationDTO minimalOperationDTO() {
        return new OperationDTO(
                "op0", "http://bauhaus/operations/op/op0",
                "Op FR", "Op EN",
                null, null,
                null,
                null, null,
                null, null,
                null, null,
                null, null,
                null
        );
    }
}