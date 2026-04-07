package fr.insee.rmes.magma.gestion.unitTests;

import fr.insee.rmes.magma.gestion.services.DatasetsServiceImpl;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetByIdSummaryDTO;
import fr.insee.rmes.magma.gestion.utils.DatasetDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatasetsServiceImplTest {

    private DatasetsServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new DatasetsServiceImpl();
    }

    // =========================================================
    //   transformDatasetDTOsToDataSets
    // =========================================================

    @Test
    void should_map_list_of_dtos_to_datasets() {
        var dtos = List.of(
                new DatasetDTO("id1", "http://bauhaus/ds/id1", "Titre 1 FR", "Title 1 EN",
                        LocalDate.of(2024, 1, 10), "Publiée", LocalDate.of(2023, 6, 1)),
                new DatasetDTO("id2", "http://bauhaus/ds/id2", "Titre 2 FR", "Title 2 EN",
                        null, "Provisoire, jamais publiée", null)
        );

        var result = service.transformDatasetDTOsToDataSets(dtos);

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("id1", result.get(0).getId()),
                () -> assertEquals("http://bauhaus/ds/id1", result.get(0).getUri()),
                () -> assertEquals("Publiée", result.get(0).getValidationState()),
                () -> assertEquals("2024-01-10", result.get(0).getCatalogRecordModified()),
                () -> assertEquals("2023-06-01", result.get(0).getCatalogRecordCreated()),
                () -> assertEquals(2, result.get(0).getTitle().size()),
                () -> assertEquals("fr", result.get(0).getTitle().getFirst().getLangue()),
                () -> assertEquals("Titre 1 FR", result.get(0).getTitle().getFirst().getContenu()),
                () -> assertEquals("en", result.get(0).getTitle().get(1).getLangue()),
                () -> assertEquals("Title 1 EN", result.get(0).getTitle().get(1).getContenu()),
                () -> assertEquals("id2", result.get(1).getId()),
                () -> assertNull(result.get(1).getCatalogRecordModified()),
                () -> assertNull(result.get(1).getCatalogRecordCreated())
        );
    }

    // =========================================================
    //   transformDatasetByIdDTOToDataSet
    // =========================================================

    @Test
    void should_map_basic_fields() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertEquals("25baaf1f", result.getId()),
                () -> assertEquals("http://bauhaus/ds/25baaf1f", result.getUri()),
                () -> assertEquals("Provisoire, jamais publiée", result.getValidationState()),
                () -> assertEquals("2024-11-01", result.getModified()),
                () -> assertEquals("2023-05-15", result.getIssued()),
                () -> assertEquals("2.0", result.getVersion()),
                () -> assertEquals("DD_EEC_SERIES", result.getIdentifier()),
                () -> assertEquals("DG75-L201", result.getCatalogRecordCreator()),
                () -> assertEquals("DG75-L201", result.getCatalogRecordContributor()),
                () -> assertEquals("2024-12-09T12:00:00", result.getCatalogRecordCreated()),
                () -> assertEquals("2024-12-09T12:00:00", result.getCatalogRecordModified())
        );
    }

    @Test
    void should_map_multilingual_labels() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertEquals(2, result.getTitle().size()),
                () -> assertEquals("fr", result.getTitle().getFirst().getLangue()),
                () -> assertEquals("Titre FR", result.getTitle().getFirst().getContenu()),
                () -> assertEquals("en", result.getTitle().get(1).getLangue()),
                () -> assertEquals("Title EN", result.getTitle().get(1).getContenu()),

                () -> assertEquals("Sous-titre FR", result.getSubtitle().getFirst().getContenu()),
                () -> assertEquals("Subtitle EN", result.getSubtitle().get(1).getContenu()),

                () -> assertEquals("Résumé FR", result.getAbstract().getFirst().getContenu()),
                () -> assertEquals("Abstract EN", result.getAbstract().get(1).getContenu()),

                () -> assertEquals("Description FR", result.getDescription().getFirst().getContenu()),
                () -> assertEquals("Description EN", result.getDescription().get(1).getContenu()),

                () -> assertEquals("Note FR", result.getScopeNote().getFirst().getContenu()),
                () -> assertEquals("Note EN", result.getScopeNote().get(1).getContenu())
        );
    }

    @Test
    void should_map_landingPage_when_present() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertNotNull(result.getLandingPage()),
                () -> assertEquals(2, result.getLandingPage().size()),
                () -> assertEquals("fr", result.getLandingPage().getFirst().getLang()),
                () -> assertEquals("https://example.fr/page", result.getLandingPage().getFirst().getUrl()),
                () -> assertEquals("en", result.getLandingPage().get(1).getLang()),
                () -> assertEquals("https://example.en/page", result.getLandingPage().get(1).getUrl())
        );
    }

    @Test
    void should_not_set_landingPage_when_null() {
        assertNull(service.transformDatasetByIdDTOToDataSet(minimalDatasetByIdDTO()).getLandingPage());
    }

    @Test
    void should_map_publisher_when_idPublisher_present() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertNotNull(result.getPublisher()),
                () -> assertEquals("DG75-F001", result.getPublisher().getId()),
                () -> assertEquals(2, result.getPublisher().getLabel().size()),
                () -> assertEquals("Éditeur FR", result.getPublisher().getLabel().getFirst().getContenu()),
                () -> assertEquals("Publisher EN", result.getPublisher().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_publisher_when_idPublisher_is_null() {
        assertNull(service.transformDatasetByIdDTOToDataSet(minimalDatasetByIdDTO()).getPublisher());
    }

    @Test
    void should_map_spatial_when_spatialId_present() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertNotNull(result.getSpatial()),
                () -> assertEquals("France", result.getSpatial().getId()),
                () -> assertEquals("France métropolitaine", result.getSpatial().getLabel().getFirst().getContenu()),
                () -> assertEquals("Metropolitan France", result.getSpatial().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_spatial_when_spatialId_is_null() {
        assertNull(service.transformDatasetByIdDTOToDataSet(minimalDatasetByIdDTO()).getSpatial());
    }

    @Test
    void should_map_temporal_when_startPeriod_present() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertNotNull(result.getTemporal()),
                () -> assertEquals("2010", result.getTemporal().getStartPeriod()),
                () -> assertEquals("2024", result.getTemporal().getEndPeriod())
        );
    }

    @Test
    void should_not_set_temporal_when_startPeriod_is_null() {
        assertNull(service.transformDatasetByIdDTOToDataSet(minimalDatasetByIdDTO()).getTemporal());
    }

    @Test
    void should_map_structure_when_structureUri_present() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertNotNull(result.getStructure()),
                () -> assertEquals("http://bauhaus/dsd/dsd1000", result.getStructure().getUri()),
                () -> assertEquals("dsd1000", result.getStructure().getId()),
                () -> assertEquals("DSD_1000", result.getStructure().getDsd())
        );
    }

    @Test
    void should_not_set_structure_when_structureUri_is_null() {
        assertNull(service.transformDatasetByIdDTOToDataSet(minimalDatasetByIdDTO()).getStructure());
    }

    @Test
    void should_parse_numObservations_and_numSeries_as_integers() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertEquals(42000, result.getNumObservations()),
                () -> assertEquals(12, result.getNumSeries())
        );
    }

    @Test
    void should_not_set_numObservations_when_null() {
        var result = service.transformDatasetByIdDTOToDataSet(minimalDatasetByIdDTO());
        assertNull(result.getNumObservations());
        assertNull(result.getNumSeries());
    }

    @Test
    void should_parse_single_creator_from_pipe_dollar_format() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertEquals(1, result.getCreator().size()),
                () -> assertEquals("DG75-E330", result.getCreator().getFirst().getId()),
                () -> assertEquals(2, result.getCreator().getFirst().getLabel().size()),
                () -> assertEquals("fr", result.getCreator().getFirst().getLabel().getFirst().getLangue()),
                () -> assertEquals("Division ESPRI", result.getCreator().getFirst().getLabel().getFirst().getContenu()),
                () -> assertEquals("en", result.getCreator().getFirst().getLabel().get(1).getLangue()),
                () -> assertEquals("ESPRI Division", result.getCreator().getFirst().getLabel().get(1).getContenu())
        );
    }

    @Test
    void should_parse_multiple_creators_separated_by_pipe() {
        var dto = datasetByIdDTOWithCreators("DG75-A$Label A FR$Label A EN|DG75-B$Label B FR$Label B EN");
        var result = service.transformDatasetByIdDTOToDataSet(dto);

        assertAll(
                () -> assertEquals(2, result.getCreator().size()),
                () -> assertEquals("DG75-A", result.getCreator().get(0).getId()),
                () -> assertEquals("Label A FR", result.getCreator().get(0).getLabel().getFirst().getContenu()),
                () -> assertEquals("Label A EN", result.getCreator().get(0).getLabel().get(1).getContenu()),
                () -> assertEquals("DG75-B", result.getCreator().get(1).getId()),
                () -> assertEquals("Label B FR", result.getCreator().get(1).getLabel().getFirst().getContenu())
        );
    }

    @Test
    void should_not_set_creator_when_creators_is_null() {
        // le service ne setCreator que si dto.creators() est non-null et non-blank
        var result = service.transformDatasetByIdDTOToDataSet(minimalDatasetByIdDTO());
        assertNull(result.getCreator());
    }

    @Test
    void should_parse_operationStat_as_uri_list() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertEquals(1, result.getWasGeneratedBy().size()),
                () -> assertEquals("http://bauhaus/operations/serie/s1223", result.getWasGeneratedBy().getFirst().getId())
        );
    }

    @Test
    void should_return_null_wasGeneratedBy_when_operationStat_is_null() {
        assertNull(service.transformDatasetByIdDTOToDataSet(minimalDatasetByIdDTO()).getWasGeneratedBy());
    }

    @Test
    void should_parse_theme_as_uri_list() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertEquals(2, result.getTheme().size()),
                () -> assertEquals("http://theme/emploi", result.getTheme().get(0).getUri()),
                () -> assertEquals("http://theme/chomage", result.getTheme().get(1).getUri())
        );
    }

    @Test
    void should_parse_keywords_lg1_and_lg2() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertEquals(3, result.getKeyword().size()),
                () -> assertEquals("fr", result.getKeyword().get(0).getLangue()),
                () -> assertEquals("emploi", result.getKeyword().get(0).getContenu()),
                () -> assertEquals("fr", result.getKeyword().get(1).getLangue()),
                () -> assertEquals("chômage", result.getKeyword().get(1).getContenu()),
                () -> assertEquals("en", result.getKeyword().get(2).getLangue()),
                () -> assertEquals("employment", result.getKeyword().get(2).getContenu())
        );
    }

    @Test
    void should_parse_relations() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertEquals(2, result.getRelations().size()),
                () -> assertEquals("http://ds/related1", result.getRelations().get(0)),
                () -> assertEquals("http://ds/related2", result.getRelations().get(1))
        );
    }

    @Test
    void should_parse_archiveUnits() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertEquals(1, result.getArchiveUnit().size()),
                () -> assertEquals("http://archive/unit1", result.getArchiveUnit().getFirst().getId())
        );
    }

    @Test
    void should_map_wasDerivedFrom_with_description_when_present() {
        var result = service.transformDatasetByIdDTOToDataSet(fullDatasetByIdDTO());

        assertAll(
                () -> assertNotNull(result.getWasDerivedFrom()),
                () -> assertEquals(1, result.getWasDerivedFrom().getDatasets().size()),
                () -> assertEquals("ds-source-1", result.getWasDerivedFrom().getDatasets().getFirst()),
                () -> assertNotNull(result.getWasDerivedFrom().getDescription()),
                () -> assertEquals("Dérivé de FR", result.getWasDerivedFrom().getDescription().getFirst().getContenu()),
                () -> assertEquals("Derived from EN", result.getWasDerivedFrom().getDescription().get(1).getContenu())
        );
    }

    @Test
    void should_not_set_wasDerivedFrom_when_null() {
        assertNull(service.transformDatasetByIdDTOToDataSet(minimalDatasetByIdDTO()).getWasDerivedFrom());
    }

    // =========================================================
    //   transformDatasetByIdSummaryDTOToDataSet
    // =========================================================

    @Test
    void should_map_summary_fields() {
        var dto = new DatasetByIdSummaryDTO(
                "http://bauhaus/ds/25baaf1f",
                "25baaf1f",
                "2024-12-09T12:00:00"
        );
        var result = service.transformDatasetByIdSummaryDTOToDataSet(dto);

        assertAll(
                () -> assertEquals("25baaf1f", result.getId()),
                () -> assertEquals("http://bauhaus/ds/25baaf1f", result.getUri()),
                () -> assertEquals("2024-12-09T12:00:00", result.getCatalogRecordModified())
        );
    }

    // =========================================================
    //   Fixtures
    // =========================================================

    private DatasetByIdDTO fullDatasetByIdDTO() {
        return new DatasetByIdDTO(
                "http://bauhaus/ds/25baaf1f",    // uri
                "25baaf1f",                      // id
                "Provisoire, jamais publiée",    // statutValidation
                "Titre FR", "Title EN",          // titleLg1, titleLg2
                "Sous-titre FR", "Subtitle EN",  // subtitleLg1, subtitleLg2
                "Résumé FR", "Abstract EN",      // abstractLg1, abstractLg2
                "Description FR", "Description EN", // descriptionLg1, descriptionLg2
                "Note FR", "Note EN",            // scopeNoteLg1, scopeNoteLg2
                "https://example.fr/page", "https://example.en/page", // landingPageLg1, landingPageLg2
                "DG75-L201",                     // catalogRecordCreator
                "DG75-L201",                     // catalogRecordContributor
                "2024-12-09T12:00:00",           // catalogRecordModified
                "2024-12-09T12:00:00",           // catalogRecordCreated
                "2024-11-01",                    // modified
                "2023-05-15",                    // issued
                "2.0",                           // version
                null,                            // spatialTemporal
                "2010",                          // startPeriod
                "2024",                          // endPeriod
                "Dérivé de FR", "Derived from EN", // derivedDescriptionLg1, derivedDescriptionLg2
                "DG75-F001", "Éditeur FR", "Publisher EN", // idPublisher, labelPublisherLg1, labelPublisherLg2
                null, null,                      // labeltypeLg1, labeltypeLg2
                null, null,                      // labelaccessRightsLg1, labelaccessRightsLg2
                null, null,                      // labelconfidentialityStatusLg1, labelconfidentialityStatusLg2
                null, null,                      // labelaccrualPeriodicityLg1, labelaccrualPeriodicityLg2
                "France", "France métropolitaine", "Metropolitan France", // spatialId, labelspatialLg1, labelspatialLg2
                null,                            // codeProcessStep
                null,                            // disseminationStatus
                "DD_EEC_SERIES",                 // identifier
                "http://bauhaus/dsd/dsd1000", "dsd1000", "DSD_1000", null, // structureUri, structureId, dsd, isDataStructureDefinition
                "42000", "12",                   // numObservations, numSeries
                "DG75-E330$Division ESPRI$ESPRI Division", // creators
                "http://bauhaus/operations/serie/s1223", // operationStat
                "http://theme/emploi,http://theme/chomage", // names
                "ds-source-1",                   // wasDerivedFromS
                "http://ds/related1,http://ds/related2", // relations
                "emploi,chômage", "employment",  // keywordLg1, keywordLg2
                "http://archive/unit1",          // archiveUnits
                null,                            // temporalResolutions
                null                             // spatialResolutions
        );
    }

    /** DTO avec tous les champs optionnels à null */
    private DatasetByIdDTO minimalDatasetByIdDTO() {
        return new DatasetByIdDTO(
                "http://bauhaus/ds/min", "min-id", null,
                "Titre FR", "Title EN",
                null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null, null, null,
                null, null, null, null,
                null, null,
                null, null,
                null, null, null,
                null, null,
                null, null,
                null, null,
                null, null,
                null, null, null,
                null, null, null,
                null, null, null, null,
                null, null,
                null, null, null, null,
                null, null, null,
                null, null, null
        );
    }

    private DatasetByIdDTO datasetByIdDTOWithCreators(String creators) {
        return new DatasetByIdDTO(
                "http://bauhaus/ds/min", "min-id", null,
                "Titre FR", "Title EN",
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null,
                creators,
                null, null, null, null, null, null, null, null, null
        );
    }
}