package fr.insee.rmes.magma.gestion.services;

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
    void should_map_identifiers_and_dates_when_transformSeriesDTO() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertEquals("s1001", result.getSeriesId()),
                () -> assertEquals("http://id.insee.fr/operations/serie/s1001", result.getUri()),
                () -> assertEquals(LocalDate.of(2020, 1, 15), result.getDateCreation()),
                () -> assertEquals(LocalDate.of(2024, 6, 1), result.getDateMiseAJour()),
                () -> assertEquals("Publiée", result.getStatutValidation())
        );
    }

    @Test
    void should_map_null_dates_as_null_when_transformSeriesDTO() {
        var result = service.transformSeriesDTOToSerieById(minimalSeriesDTO());

        assertNull(result.getDateCreation());
        assertNull(result.getDateMiseAJour());
    }

    @Test
    void should_map_multilingual_labels_when_transformSeriesDTO() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertEquals(2, result.getLabel().size()),
                () -> assertEquals("fr", result.getLabel().get(0).getLangue()),
                () -> assertEquals("Enquête innovation", result.getLabel().get(0).getContenu()),
                () -> assertEquals("en", result.getLabel().get(1).getLangue()),
                () -> assertEquals("Innovation survey", result.getLabel().get(1).getContenu()),

                () -> assertEquals("Sigle FR", result.getAltLabel().get(0).getContenu()),
                () -> assertEquals("Sigle EN", result.getAltLabel().get(1).getContenu()),

                () -> assertEquals("Résumé FR", result.getResume().get(0).getContenu()),
                () -> assertEquals("Abstract EN", result.getResume().get(1).getContenu()),

                () -> assertEquals("Note historique FR", result.getNoteHistorique().get(0).getContenu()),
                () -> assertEquals("History note EN", result.getNoteHistorique().get(1).getContenu())
        );
    }

    @Test
    void should_map_type_when_type_uri_is_present() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertNotNull(result.getType()),
                () -> assertEquals("E", result.getType().getId()),
                () -> assertEquals("http://id.insee.fr/concepts/type/E", result.getType().getUri()),
                () -> assertEquals("Enquête", result.getType().getLabel().get(0).getContenu()),
                () -> assertEquals("Survey", result.getType().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_type_when_type_is_null() {
        var result = service.transformSeriesDTOToSerieById(minimalSeriesDTO());
        assertNull(result.getType());
    }

    @Test
    void should_not_set_type_when_type_is_blank() {
        var dto = new SeriesDTO(
                "s1001", "http://id.insee.fr/operations/serie/s1001",
                "Label FR", "Label EN", null, null, null, null, null, null,
                "   ", null, null, null,
                null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null
        );
        assertNull(service.transformSeriesDTOToSerieById(dto).getType());
    }

    @Test
    void should_map_periodicity_when_present() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertNotNull(result.getFrequenceCollecte()),
                () -> assertEquals("A", result.getFrequenceCollecte().getId()),
                () -> assertEquals("http://id.insee.fr/concepts/periodicity/A", result.getFrequenceCollecte().getUri()),
                () -> assertEquals("Annuelle", result.getFrequenceCollecte().getLabel().get(0).getContenu()),
                () -> assertEquals("Annual", result.getFrequenceCollecte().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_periodicity_when_null() {
        assertNull(service.transformSeriesDTOToSerieById(minimalSeriesDTO()).getFrequenceCollecte());
    }

    @Test
    void should_parse_famille_from_dollar_separated_string() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertNotNull(result.getFamille()),
                () -> assertEquals("f1001", result.getFamille().getId()),
                () -> assertEquals("http://id.insee.fr/operations/famille/f1001", result.getFamille().getUri()),
                () -> assertEquals("Famille FR", result.getFamille().getLabel().get(0).getContenu()),
                () -> assertEquals("Family EN", result.getFamille().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_famille_when_families_is_null() {
        assertNull(service.transformSeriesDTOToSerieById(minimalSeriesDTO()).getFamille());
    }

    @Test
    void should_map_rapportQualite_when_simsId_present() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertNotNull(result.getRapportQualite()),
                () -> assertEquals("1500", result.getRapportQualite().getId()),
                () -> assertEquals("http://id.insee.fr/qualite/rapport/1500", result.getRapportQualite().getUri())
        );
    }

    @Test
    void should_not_set_rapportQualite_when_simsId_is_null() {
        assertNull(service.transformSeriesDTOToSerieById(minimalSeriesDTO()).getRapportQualite());
    }

    @Test
    void should_parse_single_ref_in_previousSeries() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertEquals(1, result.getSeriesPrecedentes().size()),
                () -> assertEquals("s1010", result.getSeriesPrecedentes().get(0).getId()),
                () -> assertEquals("http://id.insee.fr/operations/serie/s1010", result.getSeriesPrecedentes().get(0).getUri()),
                () -> assertEquals("Précédente FR", result.getSeriesPrecedentes().get(0).getLabel().get(0).getContenu()),
                () -> assertEquals("Previous EN", result.getSeriesPrecedentes().get(0).getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_parse_multiple_refs_separated_by_pipe_in_seeAlsoSeries() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertEquals(2, result.getSeriesLiees().size()),
                () -> assertEquals("s1197", result.getSeriesLiees().get(0).getId()),
                () -> assertEquals("s1198", result.getSeriesLiees().get(1).getId())
        );
    }

    @Test
    void should_parse_multiple_refs_in_operations() {
        var result = service.transformSeriesDTOToSerieById(fullSeriesDTO());

        assertAll(
                () -> assertEquals(2, result.getOperations().size()),
                () -> assertEquals("op2024", result.getOperations().get(0).getId()),
                () -> assertEquals("op2023", result.getOperations().get(1).getId())
        );
    }

    @Test
    void should_return_empty_list_when_refList_field_is_null() {
        var result = service.transformSeriesDTOToSerieById(minimalSeriesDTO());

        assertAll(
                () -> assertNotNull(result.getOperations()),
                () -> assertTrue(result.getOperations().isEmpty()),
                () -> assertTrue(result.getIndicateurs().isEmpty()),
                () -> assertTrue(result.getSeriesPrecedentes().isEmpty()),
                () -> assertTrue(result.getSeriesSuivantes().isEmpty()),
                () -> assertTrue(result.getSeriesLiees().isEmpty()),
                () -> assertTrue(result.getProprietaires().isEmpty()),
                () -> assertTrue(result.getOrganismesResponsables().isEmpty()),
                () -> assertTrue(result.getPartenaires().isEmpty()),
                () -> assertTrue(result.getServicesCollecteurs().isEmpty())
        );
    }

    // =========================================================
    //   transformOperationDTOToOperationById
    // =========================================================

    @Test
    void should_map_identifiers_when_transformOperationDTO() {
        var result = service.transformOperationDTOToOperationById(fullOperationDTO());

        assertAll(
                () -> assertEquals("s2193", result.getId()),
                () -> assertEquals("http://id.insee.fr/operations/operation/s2193", result.getUri()),
                () -> assertEquals("2024", result.getMillesime()),
                () -> assertEquals(LocalDate.of(2025, 1, 22), result.getDateCreation()),
                () -> assertEquals(LocalDate.of(2025, 4, 2), result.getDateMiseAJour()),
                () -> assertEquals("Publiée", result.getStatutValidation())
        );
    }

    @Test
    void should_map_multilingual_labels_when_transformOperationDTO() {
        var result = service.transformOperationDTOToOperationById(fullOperationDTO());

        assertAll(
                () -> assertEquals(2, result.getLabel().size()),
                () -> assertEquals("fr", result.getLabel().get(0).getLangue()),
                () -> assertEquals("Enquête innovation 2024", result.getLabel().get(0).getContenu()),
                () -> assertEquals("en", result.getLabel().get(1).getLangue()),
                () -> assertEquals("Innovation survey 2024", result.getLabel().get(1).getContenu()),

                () -> assertEquals("CIS 2024", result.getAltLabel().get(0).getContenu()),
                () -> assertEquals("CIS 2024", result.getAltLabel().get(1).getContenu())
        );
    }

    @Test
    void should_map_serie_when_seriesId_is_present() {
        var result = service.transformOperationDTOToOperationById(fullOperationDTO());

        assertAll(
                () -> assertNotNull(result.getSerie()),
                () -> assertEquals("s1001", result.getSerie().getId()),
                () -> assertEquals("http://id.insee.fr/operations/serie/s1001", result.getSerie().getUri()),
                () -> assertEquals("Enquête innovation", result.getSerie().getLabel().get(0).getContenu()),
                () -> assertEquals("Innovation survey", result.getSerie().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_serie_when_seriesId_is_null() {
        var dto = new OperationDTO(
                "s2193", "http://id.insee.fr/operations/operation/s2193",
                "Label FR", "Label EN", null, null,
                null,
                null, null, null, null,
                null, null, null, null, null
        );
        assertNull(service.transformOperationDTOToOperationById(dto).getSerie());
    }

    @Test
    void should_not_set_serie_when_seriesId_is_blank() {
        var dto = new OperationDTO(
                "s2193", "http://id.insee.fr/operations/operation/s2193",
                "Label FR", "Label EN", null, null,
                null,
                "  ", null, null, null,
                null, null, null, null, null
        );
        assertNull(service.transformOperationDTOToOperationById(dto).getSerie());
    }

    @Test
    void should_map_rapportQualite_when_simsId_present_for_operation() {
        var result = service.transformOperationDTOToOperationById(fullOperationDTO());

        assertAll(
                () -> assertNotNull(result.getRapportQualite()),
                () -> assertEquals("2203", result.getRapportQualite().getId()),
                () -> assertEquals("http://id.insee.fr/qualite/rapport/2203", result.getRapportQualite().getUri())
        );
    }

    @Test
    void should_not_set_rapportQualite_when_simsId_is_null_for_operation() {
        var dto = new OperationDTO(
                "s2193", "http://id.insee.fr/operations/operation/s2193",
                "Label FR", "Label EN", null, null,
                null,
                "s1001", "http://id.insee.fr/operations/serie/s1001", null, null,
                null, null, null, null, null
        );
        assertNull(service.transformOperationDTOToOperationById(dto).getRapportQualite());
    }

    @Test
    void should_map_null_dates_as_null_when_transformOperationDTO() {
        var dto = new OperationDTO(
                "s2193", "http://id.insee.fr/operations/operation/s2193",
                "Label FR", "Label EN", null, null,
                null, null, null, null, null,
                null, null, null, null, null
        );
        var result = service.transformOperationDTOToOperationById(dto);

        assertNull(result.getDateCreation());
        assertNull(result.getDateMiseAJour());
    }

    // =========================================================
    //   Fixtures
    // =========================================================

    private SeriesDTO fullSeriesDTO() {
        return new SeriesDTO(
                "s1001",
                "http://id.insee.fr/operations/serie/s1001",
                "Enquête innovation", "Innovation survey",
                "Sigle FR", "Sigle EN",
                "Résumé FR", "Abstract EN",
                "Note historique FR", "History note EN",
                "http://id.insee.fr/concepts/type/E", "E", "Enquête", "Survey",
                "http://id.insee.fr/concepts/periodicity/A", "A", "Annuelle", "Annual",
                "f1001$http://id.insee.fr/operations/famille/f1001$Famille FR$Family EN",
                "s1197$http://id.insee.fr/operations/serie/s1197$Série liée 1$Linked series 1|s1198$http://id.insee.fr/operations/serie/s1198$Série liée 2$Linked series 2",
                "s1010$http://id.insee.fr/operations/serie/s1010$Précédente FR$Previous EN",
                "s1020$http://id.insee.fr/operations/serie/s1020$Suivante FR$Next EN",
                "op2024$http://id.insee.fr/operations/operation/op2024$Opération 2024$Operation 2024|op2023$http://id.insee.fr/operations/operation/op2023$Opération 2023$Operation 2023",
                null,
                "http://id.insee.fr/qualite/rapport/1500", "1500",
                LocalDate.of(2020, 1, 15),
                LocalDate.of(2024, 6, 1),
                "insee$http://id.insee.fr/organisations/insee$Institut national de la statistique$French national institute of statistics",
                "drees$http://id.insee.fr/organisations/drees$DREES$DREES",
                "dares$http://id.insee.fr/organisations/dares$DARES$DARES",
                null,
                "Publiée"
        );
    }

    /** DTO avec tous les champs optionnels à null */
    private SeriesDTO minimalSeriesDTO() {
        return new SeriesDTO(
                "s1001", "http://id.insee.fr/operations/serie/s1001",
                "Label FR", "Label EN", null, null, null, null, null, null,
                null, null, null, null,
                null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null
        );
    }

    private OperationDTO fullOperationDTO() {
        return new OperationDTO(
                "s2193",
                "http://id.insee.fr/operations/operation/s2193",
                "Enquête innovation 2024", "Innovation survey 2024",
                "CIS 2024", "CIS 2024",
                "2024",
                "s1001", "http://id.insee.fr/operations/serie/s1001",
                "Enquête innovation", "Innovation survey",
                "2203", "http://id.insee.fr/qualite/rapport/2203",
                LocalDate.of(2025, 1, 22),
                LocalDate.of(2025, 4, 2),
                "Publiée"
        );
    }
}