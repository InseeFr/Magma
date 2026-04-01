package fr.insee.rmes.magma.diffusion.services;

import fr.insee.rmes.magma.diffusion.model.Concept;
import fr.insee.rmes.magma.diffusion.model.ConceptForList;
import fr.insee.rmes.magma.diffusion.model.LocalisedLabel;
import fr.insee.rmes.magma.diffusion.model.NearbyConcept;
import fr.insee.rmes.magma.diffusion.utils.ConceptDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static fr.insee.rmes.magma.diffusion.utils.LocalisedLabelUtils.createLangueContenu;
import static fr.insee.rmes.magma.diffusion.utils.LocalisedLabelUtils.createListLangueContenu;

@Service
@Slf4j
public class ConceptServiceImpl implements ConceptService{

        public Concept transformDTOenConcept(ConceptDTO conceptDTO) {
            Concept concept = new Concept();
            concept.setId(conceptDTO.id());
            concept.setUri(URI.create(conceptDTO.uri()));

            concept.setIntitule(buildLocalisedLabels(conceptDTO.intituleFr(), conceptDTO.intituleEn()));
            concept.setDefinition(buildLocalisedLabels(conceptDTO.definitionFr(), conceptDTO.definitionEn()));
            concept.setDefinitionCourte(buildLocalisedLabels(conceptDTO.scopeNoteFr(), conceptDTO.scopeNoteEn()));
            concept.setNoteEditoriale(buildLocalisedLabels(conceptDTO.noteEditorialeFr(), conceptDTO.noteEditorialeEn()));


            if (Boolean.TRUE.equals(conceptDTO.hasIntitulesAlternatifs())) {
                addIntitulesAlternatifs(conceptDTO,concept);
            }
            else concept.setIntitulesAlternatifs(null);

            addNearByConcepts(conceptDTO,concept);

            //Dates
            concept.setDateMiseAJour(tryParseDateToLocalDate(conceptDTO.dateMiseAJour()));
            concept.setDateCreation(tryParseDateToLocalDate(conceptDTO.dateCreation()));
            concept.setDateFinDeValidite(tryParseDateToLocalDate(conceptDTO.dateFinDeValidite()));

            return concept;
        }


        private static List<LocalisedLabel> buildLocalisedLabels(String frField, String enField) {
            if (frField == null && enField == null) return null;
            return createListLangueContenu(createLangueContenu(frField, "fr"), createLangueContenu(enField, "en"));
        }


        private static LocalDate tryParseDateToLocalDate(String dateString) {
            if (dateString == null || dateString.isEmpty()) {
                return null;
            }
            // Try first to parse as Instant
            try {
                return Instant.parse(dateString)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            } catch (DateTimeParseException e1) {
                // If failed, try to parse as LocalDateTime
                try {
                    return LocalDateTime.parse(dateString).toLocalDate();
                } catch (DateTimeParseException e2) {
                    log.error("IMPOSSIBLE TO PARSE THE DATE '{}' : {}", dateString, e2.getMessage());
                    return null;
                }
            }
        }


    private void addNearByConcepts(ConceptDTO conceptDTO, Concept concept) {
            concept.setConceptsSuivants(null);
            concept.setConceptsPrecedents(null);
            concept.setConceptsLies(null);
            concept.setConceptsProches(null);
            concept.setConceptsPlusGeneriques(null);
            concept.setConceptsPlusSpecifiques(null);
            concept.setConceptsReferences(null);
            if (conceptDTO.nearbyConcepts() != null) {
                conceptDTO.nearbyConcepts().forEach(nearbyConcept -> {
                    switch (nearbyConcept.getTypeOfLink()) {
                        case "isReplacedBy" -> concept.setConceptsSuivants(addToList(nearbyConcept, concept.getConceptsSuivants()));
                        case "replaces"     -> concept.setConceptsPrecedents(addToList(nearbyConcept, concept.getConceptsPrecedents()));
                        case "related"      -> concept.setConceptsLies(addToList(nearbyConcept, concept.getConceptsLies()));
                        case "closeMatch"   -> concept.setConceptsProches(addToList(nearbyConcept, concept.getConceptsProches()));
                        case "broader"      -> concept.setConceptsPlusGeneriques(addToList(nearbyConcept, concept.getConceptsPlusGeneriques()));
                        case "narrower"     -> concept.setConceptsPlusSpecifiques(addToList(nearbyConcept, concept.getConceptsPlusSpecifiques()));
                        case "references"   -> concept.setConceptsReferences(addToList(nearbyConcept, concept.getConceptsReferences()));
                        case null, default  -> log.warn("typeOfLink unknown or null : {}", nearbyConcept.getTypeOfLink());
                    }
                });
            }
        }

        private List<NearbyConcept> addToList(NearbyConcept nearbyConcept, List<NearbyConcept> conceptsList) {
            ArrayList<NearbyConcept> result = conceptsList == null ? new ArrayList<>() : new ArrayList<>(conceptsList);
            result.add(nearbyConcept);
            return result;
        }

        private void addNearByConcepts(ConceptDTO conceptDTO, ConceptForList concept) {
            concept.setConceptsSuivants(null);
            concept.setConceptsPrecedents(null);
            concept.setConceptsLies(null);
            concept.setConceptsProches(null);
            concept.setConceptsPlusGeneriques(null);
            concept.setConceptsPlusSpecifiques(null);
            concept.setConceptsReferences(null);
            if (conceptDTO.nearbyConcepts() != null) {
                conceptDTO.nearbyConcepts().forEach(nearbyConcept -> {
                    switch (nearbyConcept.getTypeOfLink()) {
                        case "isReplacedBy" -> concept.setConceptsSuivants(addToList(nearbyConcept, concept.getConceptsSuivants()));
                        case "replaces"     -> concept.setConceptsPrecedents(addToList(nearbyConcept, concept.getConceptsPrecedents()));
                        case "related"      -> concept.setConceptsLies(addToList(nearbyConcept, concept.getConceptsLies()));
                        case "closeMatch"   -> concept.setConceptsProches(addToList(nearbyConcept, concept.getConceptsProches()));
                        case "broader"      -> concept.setConceptsPlusGeneriques(addToList(nearbyConcept, concept.getConceptsPlusGeneriques()));
                        case "narrower"     -> concept.setConceptsPlusSpecifiques(addToList(nearbyConcept, concept.getConceptsPlusSpecifiques()));
                        case "references"   -> concept.setConceptsReferences(addToList(nearbyConcept, concept.getConceptsReferences()));
                        case null, default  -> log.warn("typeOfLink unknown or null : {}", nearbyConcept.getTypeOfLink());
                    }
                });
            }
        }


        private void addIntitulesAlternatifs(ConceptDTO conceptDTO, Concept concept) {
            for (LocalisedLabel item : conceptDTO.intitulesAlternatifs()) {
                LocalisedLabel newIntitule = createLangueContenu(item.getContenu(), item.getLangue());
                concept.addIntitulesAlternatifsItem(newIntitule);
            }
        }


        public ConceptForList transformDTOenDefinition(ConceptDTO conceptDTO) {
            ConceptForList concept = new ConceptForList();
            concept.setId(conceptDTO.id());
            concept.setUri(URI.create(conceptDTO.uri()));
            concept.setIntitule(conceptDTO.intituleFr());

            addNearByConcepts(conceptDTO, concept);

            return concept;
        }

}
