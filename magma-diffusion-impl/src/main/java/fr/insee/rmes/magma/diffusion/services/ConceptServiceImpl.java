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
            if  (conceptDTO.intituleFr() != null || conceptDTO.intituleEn() != null) {
                List<LocalisedLabel> intitules = createListLangueContenu(createLangueContenu(conceptDTO.intituleFr(),"fr"),createLangueContenu(conceptDTO.intituleEn(),"en"));
                concept.setIntitule(intitules);
            }

            if  (conceptDTO.definitionFr() != null || conceptDTO.definitionEn() != null) {
                List<LocalisedLabel> definitions = createListLangueContenu(createLangueContenu(conceptDTO.definitionFr(),"fr"),createLangueContenu(conceptDTO.definitionEn(),"en"));
                concept.setDefinition(definitions);
            }
            else concept.setDefinition(null);

            if  (conceptDTO.scopeNoteFr() != null || conceptDTO.scopeNoteEn() != null) {
                List<LocalisedLabel> definitionCourte = createListLangueContenu(createLangueContenu(conceptDTO.scopeNoteFr(),"fr"),createLangueContenu(conceptDTO.scopeNoteEn(),"en"));
                concept.setDefinitionCourte(definitionCourte);
            }
            else concept.setDefinitionCourte(null);

            if  (conceptDTO.noteEditorialeFr() != null || conceptDTO.noteEditorialeEn() != null) {
                List<LocalisedLabel> noteEditoriale = createListLangueContenu(createLangueContenu(conceptDTO.noteEditorialeFr(),"fr"),createLangueContenu(conceptDTO.noteEditorialeEn(),"en"));
                concept.setNoteEditoriale(noteEditoriale);
            }
            else concept.setNoteEditoriale(null);


            if (conceptDTO.hasIntitulesAlternatifs()) {
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
                conceptDTO.nearbyConcepts().forEach(c -> {
                    if ("isReplacedBy".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsSuivants() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsSuivants());
                        collections.add(c);
                        concept.setConceptsSuivants(collections);
                    }
                    if ("replaces".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsPrecedents() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsPrecedents());
                        collections.add(c);
                        concept.setConceptsPrecedents(collections);
                    }
                    if ("related".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsLies() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsLies());
                        collections.add(c);
                        concept.setConceptsLies(collections);
                    }
                    if ("closeMatch".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsProches() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsProches());
                        collections.add(c);
                        concept.setConceptsProches(collections);
                    }
                    if ("broader".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsPlusGeneriques() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsPlusGeneriques());
                        collections.add(c);
                        concept.setConceptsPlusGeneriques(collections);
                    }
                    if ("narrower".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsPlusSpecifiques() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsPlusSpecifiques());
                        collections.add(c);
                        concept.setConceptsPlusSpecifiques(collections);
                    }
                    if ("references".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsReferences() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsReferences());
                        collections.add(c);
                        concept.setConceptsReferences(collections);
                    }
                });
            }

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
                conceptDTO.nearbyConcepts().forEach(c -> {
                    if ("isReplacedBy".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsSuivants() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsSuivants());
                        collections.add(c);
                        concept.setConceptsSuivants(collections);
                    }
                    if ("replaces".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsPrecedents() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsPrecedents());
                        collections.add(c);
                        concept.setConceptsPrecedents(collections);
                    }
                    if ("related".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsLies() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsLies());
                        collections.add(c);
                        concept.setConceptsLies(collections);
                    }
                    if ("closeMatch".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsProches() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsProches());
                        collections.add(c);
                        concept.setConceptsProches(collections);
                    }
                    if ("broader".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsPlusGeneriques() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsPlusGeneriques());
                        collections.add(c);
                        concept.setConceptsPlusGeneriques(collections);
                    }
                    if ("narrower".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsPlusSpecifiques() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsPlusSpecifiques());
                        collections.add(c);
                        concept.setConceptsPlusSpecifiques(collections);
                    }
                    if ("references".equals(c.getTypeOfLink())) {
                        ArrayList<NearbyConcept> collections = concept.getConceptsReferences() == null ? new ArrayList<>() : new ArrayList<>(concept.getConceptsReferences());
                        collections.add(c);
                        concept.setConceptsReferences(collections);
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
