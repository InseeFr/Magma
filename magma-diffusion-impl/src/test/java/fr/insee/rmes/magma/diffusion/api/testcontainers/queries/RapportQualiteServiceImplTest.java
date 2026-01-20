package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.RapportQualite;
import fr.insee.rmes.magma.diffusion.model.Rubrique;
import fr.insee.rmes.magma.diffusion.services.RapportQualiteServiceImpl;
import fr.insee.rmes.magma.diffusion.utils.RapportQualiteDTO;
import fr.insee.rmes.magma.diffusion.utils.RubriqueDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RapportQualiteServiceImplTest {
    private RapportQualiteServiceImpl service;
    private RequestProcessor requestProcessor;

    @BeforeEach
    void setUp() {
        service = new RapportQualiteServiceImpl();
    }

    @Test
    void transformDTOenRapportQualite_shouldMapBasicFields() {
        // Given
        RapportQualiteDTO dto = new RapportQualiteDTO();
        dto.setId("rubrique-001");
        dto.setUri("http://example.com/rubrique-001");
        dto.setLabelLg1("Rapport qualité");
        dto.setLabelLg2("Quality report");

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getId()).isEqualTo("rubrique-001");
        assertThat(result.getUri()).isEqualTo(URI.create("http://example.com/rubrique-001"));
        assertThat(result.getLabel())
                .hasSize(2)
                .satisfies(labels -> {
                    assertThat(labels.get(0).getLangue()).isEqualTo("fr");
                    assertThat(labels.get(0).getContenu()).isEqualTo("Rapport qualité");
                    assertThat(labels.get(1).getLangue()).isEqualTo("en");
                    assertThat(labels.get(1).getContenu()).isEqualTo("Quality report");
                });
    }

    @Test
    void transformDTOenRapportQualite_shouldHandleOnlyFrenchLabel() {
        // Given
        RapportQualiteDTO dto = new RapportQualiteDTO();
        dto.setId("rubrique-002");
        dto.setUri("http://example.com/rubrique-002");
        dto.setLabelLg1("Rapport qualité");
        dto.setLabelLg2(null);

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getLabel()).hasSize(2);
        assertThat(result.getLabel().get(0).getContenu()).isEqualTo("Rapport qualité");
        assertThat(result.getLabel().get(1).getContenu()).isEmpty();
    }

    @Test
    void transformDTOenRapportQualite_shouldHandleNullRubriqueList() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();
        dto.setRubriqueDTOList(null);

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getId()).isEqualTo("rubrique-test");
        assertThat(result.getRubriques()).isNull();
    }

    @Test
    void transformRubrique_shouldTransformDateType() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();
        RubriqueDTO rubriqueDTO = createRubriqueDTO("rubrique-date", "DATE");
        rubriqueDTO.setValeurSimple("2024-01-15");
        rubriqueDTO.setTitreLg1("Date de publication");
        rubriqueDTO.setTitreLg2("Publication date");
        dto.setRubriqueDTOList(List.of(rubriqueDTO));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getRubriques()).hasSize(1);
        Rubrique rubrique = result.getRubriques().get(0);
        assertThat(rubrique.getId()).isEqualTo("rubrique-date");
        assertThat(rubrique.getType()).isEqualTo("DATE");
        assertThat(rubrique.getDate()).isEqualTo("2024-01-15");
        assertThat(rubrique.getTitre()).hasSize(2);
        assertThat(rubrique.getTitre().getFirst().getContenu()).isEqualTo("Date de publication");
    }

    @Test
    void transformRubrique_shouldTransformTextType() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();
        RubriqueDTO rubriqueDTO = createRubriqueDTO("rubrique-text", "TEXT");
        rubriqueDTO.setLabelLg1("Texte français");
        rubriqueDTO.setLabelLg2("English text");
        dto.setRubriqueDTOList(List.of(rubriqueDTO));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getRubriques()).hasSize(1);
        Rubrique rubrique = result.getRubriques().getFirst();
        assertThat(rubrique.getType()).isEqualTo("TEXT");
        assertThat(rubrique.getLabel()).hasSize(2);
        assertThat(rubrique.getLabel().get(0).getContenu()).isEqualTo("Texte français");
        assertThat(rubrique.getLabel().get(1).getContenu()).isEqualTo("English text");
    }

    @Test
    void transformRubrique_shouldTransformGeographyType() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();
        RubriqueDTO rubriqueDTO = createRubriqueDTO("rubrique-geo", "GEOGRAPHY");
        rubriqueDTO.setValeurSimple("FR");
        rubriqueDTO.setGeoUri("http://example.com/geo/fr");
        rubriqueDTO.setLabelObjLg1("France");
        rubriqueDTO.setLabelObjLg2("France");
        dto.setRubriqueDTOList(List.of(rubriqueDTO));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getRubriques()).hasSize(1);
        Rubrique rubrique = result.getRubriques().get(0);
        assertThat(rubrique.getType()).isEqualTo("GEOGRAPHY");
        assertThat(rubrique.getTerritoire()).isNotNull();
        assertThat(rubrique.getTerritoire().getId()).isEqualTo("FR");
        assertThat(rubrique.getTerritoire().getUri()).isEqualTo(URI.create("http://example.com/geo/fr"));
        assertThat(rubrique.getTerritoire().getLabel()).hasSize(2);
        assertThat(rubrique.getTerritoire().getLabel().getFirst().getContenu()).isEqualTo("France");
    }

    @Test
    void transformRubrique_shouldTransformGeographyType_withOnlyFrenchLabel() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();
        RubriqueDTO rubriqueDTO = createRubriqueDTO("rubrique-geo", "GEOGRAPHY");
        rubriqueDTO.setValeurSimple("FR");
        rubriqueDTO.setGeoUri("http://example.com/geo/fr");
        rubriqueDTO.setLabelObjLg1("France métropolitaine");
        rubriqueDTO.setLabelObjLg2(null);
        dto.setRubriqueDTOList(List.of(rubriqueDTO));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        Rubrique rubrique = result.getRubriques().getFirst();
        Assertions.assertNotNull(rubrique.getTerritoire());
        assertThat(rubrique.getTerritoire().getLabel()).hasSize(1);
        assertThat(rubrique.getTerritoire().getLabel().getFirst().getContenu()).isEqualTo("France métropolitaine");
    }

    @Test
    void transformRubrique_shouldTransformOrganizationType() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();
        RubriqueDTO rubriqueDTO = createRubriqueDTO("rubrique-org", "ORGANIZATION");
        rubriqueDTO.setValeurSimple("INSEE");
        rubriqueDTO.setOrganisationUri("http://example.com/org/insee");
        rubriqueDTO.setLabelObjLg1("Institut national de la statistique");
        rubriqueDTO.setLabelObjLg2("National Institute of Statistics");
        dto.setRubriqueDTOList(List.of(rubriqueDTO));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getRubriques()).hasSize(1);
        Rubrique rubrique = result.getRubriques().get(0);
        assertThat(rubrique.getType()).isEqualTo("ORGANIZATION");
        assertThat(rubrique.getOrganisme()).isNotNull();
        assertThat(rubrique.getOrganisme().getId()).isEqualTo("INSEE");
        assertThat(rubrique.getOrganisme().getUri()).isEqualTo(URI.create("http://example.com/org/insee"));
        assertThat(rubrique.getOrganisme().getLabel()).hasSize(2);
    }

    @Test
    void transformRubrique_shouldTransformCodeListType_singleCode() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();
        RubriqueDTO rubriqueDTO = createRubriqueDTO("rubrique-code", "CODE_LIST");
        rubriqueDTO.setValeurSimple("CODE-001");
        rubriqueDTO.setCodeUri("http://example.com/code/code-001");
        rubriqueDTO.setLabelObjLg1("Code français");
        rubriqueDTO.setLabelObjLg2("English code");
        rubriqueDTO.setMaxOccurs(null);
        dto.setRubriqueDTOList(List.of(rubriqueDTO));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getRubriques()).hasSize(1);
        Rubrique rubrique = result.getRubriques().getFirst();
        assertThat(rubrique.getType()).isEqualTo("CODE_LIST");
        assertThat(rubrique.getCodes()).hasSize(1);
        assertThat(rubrique.getCodes().getFirst().getId()).isEqualTo("CODE-001");
        assertThat(rubrique.getCodes().getFirst().getUri()).isEqualTo(URI.create("http://example.com/code/code-001"));
        assertThat(rubrique.getCodes().getFirst().getLabel()).hasSize(2);
    }

    @Test
    void transformRubrique_shouldTransformCodeListType_multipleCodesInSameRubrique() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();

        RubriqueDTO rubrique1 = createRubriqueDTO("rubrique-code", "CODE_LIST");
        rubrique1.setValeurSimple("CODE-001");
        rubrique1.setCodeUri("http://example.com/code/code-001");
        rubrique1.setLabelObjLg1("Code 1");
        rubrique1.setLabelObjLg2("Code 1 EN");
        rubrique1.setMaxOccurs("unbounded");

        RubriqueDTO rubrique2 = createRubriqueDTO("rubrique-code", "CODE_LIST");
        rubrique2.setValeurSimple("CODE-002");
        rubrique2.setCodeUri("http://example.com/code/code-002");
        rubrique2.setLabelObjLg1("Code 2");
        rubrique2.setLabelObjLg2("Code 2 EN");
        rubrique2.setMaxOccurs("unbounded");

        RubriqueDTO rubrique3 = createRubriqueDTO("rubrique-code", "CODE_LIST");
        rubrique3.setValeurSimple("CODE-003");
        rubrique3.setCodeUri("http://example.com/code/code-003");
        rubrique3.setLabelObjLg1("Code 3");
        rubrique3.setMaxOccurs("unbounded");

        dto.setRubriqueDTOList(List.of(rubrique1, rubrique2, rubrique3));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getRubriques())
                .hasSize(1)
                .first()
                .satisfies(rubrique -> {
                    assertThat(rubrique.getId()).isEqualTo("rubrique-code");
                    assertThat(rubrique.getCodes()).hasSize(3);
                    assertThat(rubrique.getCodes().get(0).getId()).isEqualTo("CODE-001");
                    assertThat(rubrique.getCodes().get(1).getId()).isEqualTo("CODE-002");
                    assertThat(rubrique.getCodes().get(2).getId()).isEqualTo("CODE-003");
                });
    }

    @Test
    void transformRubrique_shouldTransformRichTextType_withoutDocuments() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();
        RubriqueDTO rubriqueDTO = createRubriqueDTO("rubrique-rich", "RICH_TEXT");
        rubriqueDTO.setLabelLg1("Texte riche français");
        rubriqueDTO.setLabelLg2("English rich text");
        rubriqueDTO.setHasDocLg1(false);
        rubriqueDTO.setHasDocLg2(false);
        dto.setRubriqueDTOList(List.of(rubriqueDTO));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getRubriques()).hasSize(1);
        Rubrique rubrique = result.getRubriques().get(0);
        assertThat(rubrique.getType()).isEqualTo("RICH_TEXT");
        assertThat(rubrique.getContenus()).hasSize(2);
        assertThat(rubrique.getContenus().get(0).getLangue()).isEqualTo("fr");
        assertThat(rubrique.getContenus().get(0).getTexte()).isEqualTo("Texte riche français");
        assertThat(rubrique.getContenus().get(0).getDocuments()).isNull();
        assertThat(rubrique.getContenus().get(1).getLangue()).isEqualTo("en");
        assertThat(rubrique.getContenus().get(1).getTexte()).isEqualTo("English rich text");
    }

    @Test
    void transformRubrique_shouldTransformRichTextType_onlyFrenchContent() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();
        RubriqueDTO rubriqueDTO = createRubriqueDTO("rubrique-rich", "RICH_TEXT");
        rubriqueDTO.setLabelLg1("Texte français uniquement");
        rubriqueDTO.setLabelLg2(null);
        rubriqueDTO.setHasDocLg1(false);
        rubriqueDTO.setHasDocLg2(false);
        dto.setRubriqueDTOList(List.of(rubriqueDTO));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        Rubrique rubrique = result.getRubriques().get(0);
        assertThat(rubrique.getContenus()).hasSize(1);
        assertThat(rubrique.getContenus().get(0).getLangue()).isEqualTo("fr");
    }

    @Test
    void transformRubrique_shouldHandleMixedRubriqueTypes() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();

        RubriqueDTO dateRubrique = createRubriqueDTO("rubrique-date", "DATE");
        dateRubrique.setValeurSimple("2024-01-15");

        RubriqueDTO textRubrique = createRubriqueDTO("rubrique-text", "TEXT");
        textRubrique.setLabelLg1("Texte");
        textRubrique.setLabelLg2("Text");

        RubriqueDTO geoRubrique = createRubriqueDTO("rubrique-geo", "GEOGRAPHY");
        geoRubrique.setValeurSimple("FR");
        geoRubrique.setGeoUri("http://example.com/geo/fr");
        geoRubrique.setLabelObjLg1("France");

        dto.setRubriqueDTOList(List.of(dateRubrique, textRubrique, geoRubrique));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getRubriques()).hasSize(3);
        assertThat(result.getRubriques().get(0).getType()).isEqualTo("DATE");
        assertThat(result.getRubriques().get(1).getType()).isEqualTo("TEXT");
        assertThat(result.getRubriques().get(2).getType()).isEqualTo("GEOGRAPHY");
    }

    @Test
    void transformRubrique_shouldPreserveRubriqueOrder() {
        // Given
        RapportQualiteDTO dto = createBasicDTO();

        RubriqueDTO rub1 = createRubriqueDTO("rubrique-1", "TEXT");
        rub1.setLabelLg1("Premier");

        RubriqueDTO rub2 = createRubriqueDTO("rubrique-2", "TEXT");
        rub2.setLabelLg1("Deuxième");

        RubriqueDTO rub3 = createRubriqueDTO("rubrique-3", "TEXT");
        rub3.setLabelLg1("Troisième");

        dto.setRubriqueDTOList(List.of(rub1, rub2, rub3));

        // When
        RapportQualite result = service.transformDTOenRapportQualite(dto, requestProcessor);

        // Then
        assertThat(result.getRubriques())
                .extracting(Rubrique::getId)
                .containsExactly("rubrique-1", "rubrique-2", "rubrique-3");
    }

    private RapportQualiteDTO createBasicDTO() {
        RapportQualiteDTO dto = new RapportQualiteDTO();
        dto.setId("rubrique-test");
        dto.setUri("http://example.com/rubrique-test");
        dto.setLabelLg1("Test rapport");
        dto.setLabelLg2("Test report");
        return dto;
    }

    private RubriqueDTO createRubriqueDTO(String id, String type) {
        RubriqueDTO dto = new RubriqueDTO();
        dto.setId(id);
        dto.setUri("http://example.com/" + id);
        dto.setType(type);
        return dto;
    }
}
