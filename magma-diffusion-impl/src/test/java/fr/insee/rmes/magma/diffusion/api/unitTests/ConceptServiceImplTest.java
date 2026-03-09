package fr.insee.rmes.magma.diffusion.api.unitTests;

import fr.insee.rmes.magma.diffusion.model.Concept;
import fr.insee.rmes.magma.diffusion.model.LocalisedLabel;
import fr.insee.rmes.magma.diffusion.model.NearbyConcept;
import fr.insee.rmes.magma.diffusion.services.ConceptServiceImpl;
import fr.insee.rmes.magma.diffusion.utils.ConceptDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ConceptServiceImplTest {

    private ConceptServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ConceptServiceImpl();
    }


    private ConceptDTO createBasicDTO() {
        return new ConceptDTO(
                "c001", "http://id.insee.fr/concepts/c001",
                null, null,   // intituleFr, intituleEn
                null, null,   // definitionFr, definitionEn
                null, null,   // scopeNoteFr, scopeNoteEn
                null, null,   // noteEditorialeFr, noteEditorialeEn
                null, null, null, // dateMiseAJour, dateCreation, dateFinDeValidite
                false, false, // hasLink, hasIntitulesAlternatifs
                null, null    // nearbyConcepts, intitulesAlternatifs
        );
    }

    private NearbyConcept nearbyConceptWithLink(String typeOfLink) {
        NearbyConcept nc = new NearbyConcept();
        nc.setTypeOfLink(typeOfLink);
        return nc;
    }

    // ////////////////////////////////////////////////////////////////////////
    // id et uri
    // ////////////////////////////////////////////////////////////////////////

    @Test
    void transformDTOenConcept_shouldMapIdAndUri() {
        // Given
        ConceptDTO dto = createBasicDTO();

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getId()).isEqualTo("c001");
        assertThat(result.getUri()).isEqualTo(URI.create("http://id.insee.fr/concepts/c001"));
    }

    // ////////////////////////////////////////////////////////////////////////
    // Intitulé
    // ////////////////////////////////////////////////////////////////////////

    @Test
    void transformDTOenConcept_shouldMapIntituleFrAndEn() {
        // Given
        ConceptDTO dto = new ConceptDTO(
                "c001", "http://id.insee.fr/concepts/c001",
                "Population", "Population EN",
                null, null, null, null, null, null,
                null, null, null, false, false, null, null);

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getIntitule()).hasSize(2);
        assertThat(result.getIntitule().get(0).getLangue()).isEqualTo("fr");
        assertThat(result.getIntitule().get(0).getContenu()).isEqualTo("Population");
        assertThat(result.getIntitule().get(1).getLangue()).isEqualTo("en");
        assertThat(result.getIntitule().get(1).getContenu()).isEqualTo("Population EN");
    }

    @Test
    void transformDTOenConcept_shouldMapIntituleFrOnly() {
        // Given
        ConceptDTO dto = new ConceptDTO(
                "c001", "http://id.insee.fr/concepts/c001",
                "Population", null,
                null, null, null, null, null, null,
                null, null, null, false, false, null, null);

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getIntitule().getFirst().getContenu()).isEqualTo("Population");
        assertThat(result.getIntitule().getFirst().getLangue()).isEqualTo("fr");
    }

    @Test
    void transformDTOenConcept_shouldNotSetIntituleWhenBothNull() {
        // Given
        ConceptDTO dto = createBasicDTO();

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then — setIntitule n'est pas appelé, la liste reste vide (valeur par défaut du modèle)
        assertThat(result.getIntitule()).isNull();
    }

    // ////////////////////////////////////////////////////////////////////////
    // Définition
    // ////////////////////////////////////////////////////////////////////////-

    @Test
    void transformDTOenConcept_shouldMapDefinitionFrAndEn() {
        // Given
        ConceptDTO dto = new ConceptDTO(
                "c001", "http://id.insee.fr/concepts/c001",
                null, null,
                "Définition FR", "Definition EN",
                null, null, null, null,
                null, null, null, false, false, null, null);

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getDefinition()).hasSize(2);
        assertThat(result.getDefinition().get(0).getLangue()).isEqualTo("fr");
        assertThat(result.getDefinition().get(0).getContenu()).isEqualTo("Définition FR");
        assertThat(result.getDefinition().get(1).getLangue()).isEqualTo("en");
        assertThat(result.getDefinition().get(1).getContenu()).isEqualTo("Definition EN");
    }

    @Test
    void transformDTOenConcept_shouldSetDefinitionNullWhenBothNull() {
        // Given
        ConceptDTO dto = createBasicDTO();

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getDefinition()).isNull();
    }

    // ////////////////////////////////////////////////////////////////////////
    // Définition courte (scopeNote)
    // ////////////////////////////////////////////////////////////////////////

    @Test
    void transformDTOenConcept_shouldMapScopeNoteFrAndEn() {
        // Given
        ConceptDTO dto = new ConceptDTO(
                "c001", "http://id.insee.fr/concepts/c001",
                null, null, null, null,
                "Résumé FR", "Summary EN",
                null, null,
                null, null, null, false, false, null, null);

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getDefinitionCourte()).hasSize(2);
        assertThat(result.getDefinitionCourte().get(0).getContenu()).isEqualTo("Résumé FR");
        assertThat(result.getDefinitionCourte().get(1).getContenu()).isEqualTo("Summary EN");
    }

    @Test
    void transformDTOenConcept_shouldSetDefinitionCourteNullWhenBothNull() {
        // Given
        ConceptDTO dto = createBasicDTO();

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getDefinitionCourte()).isNull();
    }

    // ////////////////////////////////////////////////////////////////////////
    // Note éditoriale
    // ////////////////////////////////////////////////////////////////////////

    @Test
    void transformDTOenConcept_shouldMapNoteEditorialeFrAndEn() {
        // Given
        ConceptDTO dto = new ConceptDTO(
                "c001", "http://id.insee.fr/concepts/c001",
                null, null, null, null, null, null,
                "Note éd. FR", "Editorial EN",
                null, null, null, false, false, null, null);

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getNoteEditoriale()).hasSize(2);
        assertThat(result.getNoteEditoriale().get(0).getContenu()).isEqualTo("Note éd. FR");
        assertThat(result.getNoteEditoriale().get(1).getContenu()).isEqualTo("Editorial EN");
    }

    @Test
    void transformDTOenConcept_shouldSetNoteEditorialeNullWhenBothNull() {
        // Given
        ConceptDTO dto = createBasicDTO();

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getNoteEditoriale()).isNull();
    }

    // ////////////////////////////////////////////////////////////////////////
    // Dates
    // ////////////////////////////////////////////////////////////////////////

    @Test
    void transformDTOenConcept_shouldParseDateCreationAsInstant() {
        // Given
        ConceptDTO dto = createBasicDTO().withDateCreation("2020-01-15T00:00:00Z");

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getDateCreation()).isEqualTo(LocalDate.of(2020, 1, 15));
    }

    @Test
    void transformDTOenConcept_shouldSetNullDatesWhenNull() {
        // Given
        ConceptDTO dto = createBasicDTO();

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getDateCreation()).isNull();
        assertThat(result.getDateMiseAJour()).isNull();
        assertThat(result.getDateFinDeValidite()).isNull();
    }

    @Test
    void transformDTOenConcept_shouldSetNullDateWhenDateIsUnparseable() {
        // Given
        ConceptDTO dto = createBasicDTO().withDateCreation("not-a-date");

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getDateCreation()).isNull();
    }

    // ////////////////////////////////////////////////////////////////////////
    // Concepts proches (nearbyConcepts)
    // ////////////////////////////////////////////////////////////////////////

    @Test
    void transformDTOenConcept_shouldMapIsReplacedByToConceptsSuivants() {
        // Given
        NearbyConcept nc = nearbyConceptWithLink("isReplacedBy");
        ConceptDTO dto = createBasicDTO().withNearbyConcepts(List.of(nc));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getConceptsSuivants()).hasSize(1).contains(nc);
        assertThat(result.getConceptsPrecedents()).isNull();
    }

    @Test
    void transformDTOenConcept_shouldMapReplacesToConceptsPrecedents() {
        // Given
        NearbyConcept nc = nearbyConceptWithLink("replaces");
        ConceptDTO dto = createBasicDTO().withNearbyConcepts(List.of(nc));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getConceptsPrecedents()).hasSize(1).contains(nc);
        assertThat(result.getConceptsSuivants()).isNull();
    }

    @Test
    void transformDTOenConcept_shouldMapRelatedToConceptsLies() {
        // Given
        NearbyConcept nc = nearbyConceptWithLink("related");
        ConceptDTO dto = createBasicDTO().withNearbyConcepts(List.of(nc));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getConceptsLies()).hasSize(1).contains(nc);
    }

    @Test
    void transformDTOenConcept_shouldMapCloseMatchToConceptsProches() {
        // Given
        NearbyConcept nc = nearbyConceptWithLink("closeMatch");
        ConceptDTO dto = createBasicDTO().withNearbyConcepts(List.of(nc));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getConceptsProches()).hasSize(1).contains(nc);
    }

    @Test
    void transformDTOenConcept_shouldMapBroaderToConceptsPlusGeneriques() {
        // Given
        NearbyConcept nc = nearbyConceptWithLink("broader");
        ConceptDTO dto = createBasicDTO().withNearbyConcepts(List.of(nc));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getConceptsPlusGeneriques()).hasSize(1).contains(nc);
    }

    @Test
    void transformDTOenConcept_shouldMapNarrowerToConceptsPlusSpecifiques() {
        // Given
        NearbyConcept nc = nearbyConceptWithLink("narrower");
        ConceptDTO dto = createBasicDTO().withNearbyConcepts(List.of(nc));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getConceptsPlusSpecifiques()).hasSize(1).contains(nc);
    }

    @Test
    void transformDTOenConcept_shouldMapReferencesToConceptsReferences() {
        // Given
        NearbyConcept nc = nearbyConceptWithLink("references");
        ConceptDTO dto = createBasicDTO().withNearbyConcepts(List.of(nc));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getConceptsReferences()).hasSize(1).contains(nc);
    }

    @Test
    void transformDTOenConcept_shouldGroupMultipleNearbyConceptsOfSameType() {
        // Given
        NearbyConcept nc1 = nearbyConceptWithLink("isReplacedBy");
        NearbyConcept nc2 = nearbyConceptWithLink("isReplacedBy");
        ConceptDTO dto = createBasicDTO().withNearbyConcepts(List.of(nc1, nc2));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getConceptsSuivants()).hasSize(2).containsExactly(nc1, nc2);
    }

    @Test
    void transformDTOenConcept_shouldIgnoreNearbyConceptWithUnknownTypeOfLink() {
        // Given
        NearbyConcept nc = nearbyConceptWithLink("unknownType");
        ConceptDTO dto = createBasicDTO().withNearbyConcepts(List.of(nc));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then — aucune liste ne doit être alimentée
        assertThat(result.getConceptsSuivants()).isNull();
        assertThat(result.getConceptsPrecedents()).isNull();
        assertThat(result.getConceptsLies()).isNull();
        assertThat(result.getConceptsProches()).isNull();
        assertThat(result.getConceptsPlusGeneriques()).isNull();
        assertThat(result.getConceptsPlusSpecifiques()).isNull();
        assertThat(result.getConceptsReferences()).isNull();
    }

    @Test
    void transformDTOenConcept_shouldIgnoreNearbyConceptWithNullTypeOfLink() {
        // Given
        NearbyConcept nc = nearbyConceptWithLink(null);
        ConceptDTO dto = createBasicDTO().withNearbyConcepts(List.of(nc));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getConceptsSuivants()).isNull();
        assertThat(result.getConceptsPrecedents()).isNull();
    }

    // ////////////////////////////////////////////////////////////////////////
    // Intitulés alternatifs
    // ////////////////////////////////////////////////////////////////////////

    @Test
    void transformDTOenConcept_shouldSetIntitulesAlternatifsNullWhenFalse() {
        // Given
        ConceptDTO dto = createBasicDTO(); // hasIntitulesAlternatifs = false

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getIntitulesAlternatifs()).isNull();
    }

    @Test
    void transformDTOenConcept_shouldMapIntitulesAlternatifs() {
        // Given
        LocalisedLabel labelFr = new LocalisedLabel().contenu("Pop.").langue("fr");
        LocalisedLabel labelEn = new LocalisedLabel().contenu("Pop.").langue("en");

        ConceptDTO dto = new ConceptDTO(
                "c001", "http://id.insee.fr/concepts/c001",
                null, null, null, null, null, null, null, null,
                null, null, null,
                false, true,
                null, List.of(labelFr, labelEn));

        // When
        Concept result = service.transformDTOenConcept(dto);

        // Then
        assertThat(result.getIntitulesAlternatifs()).hasSize(2);
        assertThat(result.getIntitulesAlternatifs().get(0).getContenu()).isEqualTo("Pop.");
        assertThat(result.getIntitulesAlternatifs().get(0).getLangue()).isEqualTo("fr");
        assertThat(result.getIntitulesAlternatifs().get(1).getLangue()).isEqualTo("en");
    }
}
