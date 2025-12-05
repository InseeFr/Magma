package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j


public class ConceptDTO {

    private String id;
    private String uri;
    private String intituleFr;
    private String intituleEn;
    private String definitionFr;
    private String definitionEn;
    private String noteEditorialeFr;
    private String noteEditorialeEn;
    private String dateMiseAJour;
    @Getter
    private Boolean hasLink;
    @Getter
    private List<NearbyConcept> nearbyConcepts;



    public Concept transformDTOenConcept() {
        Concept concept = new Concept();
        concept.setId(this.id);
        concept.setUri(URI.create(this.uri));
        if  (this.intituleFr != null && this.intituleEn != null) {
            List<ConceptIntituleInner> intitules = createListLangueContenu(createLangueContenu(intituleFr,"fr"),createLangueContenu(intituleEn,"en"));
            concept.setIntitule(intitules);
        }
        if  (this.intituleFr != null && this.intituleEn == null) {
            List<ConceptIntituleInner> intitules = createListLangueContenu(createLangueContenu(intituleFr,"fr"),createLangueContenu("","en"));
            concept.setIntitule(intitules);
        }

        if  (this.definitionFr != null && this.definitionEn != null) {
            List<ConceptIntituleInner> definitions = createListLangueContenu(createLangueContenu(definitionFr,"fr"),createLangueContenu(definitionEn,"en"));
            concept.setDefinition(definitions);
        }
        if  (this.noteEditorialeFr != null && this.noteEditorialeEn != null) {
            List<ConceptIntituleInner> noteEditoriales = createListLangueContenu(createLangueContenu(noteEditorialeFr,"fr"),createLangueContenu(noteEditorialeEn,"en"));
            concept.setNoteEditoriale(noteEditoriales);
        }

        addNearByConcepts(concept);

        // Conversion de la date en LocalDate
        if (this.dateMiseAJour != null && !this.dateMiseAJour.isEmpty()) {
            try {
                // Utilise Instant.parse() qui gère automatiquement les formats ISO
                concept.setDateMiseAJour(Instant.parse(this.dateMiseAJour).atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            } catch (DateTimeParseException e) {
                log.error("IMPOSSIBLE TO PARSE THE DATE '{}' : {}", this.dateMiseAJour, e.getMessage());
                concept.setDateMiseAJour(null);
            }
        } else {
            concept.setDateMiseAJour(null);
        }

        return concept;
    }

    private void addNearByConcepts(Concept concept) {
        concept.setConceptsSuivants(null);
        concept.setConceptsPrecedents(null);
        concept.setConceptsLies(null);
        concept.setConceptsProches(null);
        concept.setConceptsPlusGeneriques(null);
        concept.setConceptsPlusSpecifiques(null);
        concept.setConceptsReferences(null);

        if (this.nearbyConcepts != null) {
            boolean hasReplacedBy = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "isReplacedBy".equals(cs.getTypeOfLink()));
            if (hasReplacedBy) {
                List<NearbyConcept> conceptsSuivants = this.nearbyConcepts.stream()
                        .filter(cs -> "isReplacedBy".equals(cs.getTypeOfLink()))
                        .map(cs -> {
                            NearbyConcept inner = new NearbyConcept();
                            inner.setId(cs.getId());
                            inner.setUri(cs.getUri());
                            return inner;
                        })
                        .collect(Collectors.toList());
                concept.setConceptsSuivants(conceptsSuivants);
            }

            boolean replaces = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "replaces".equals(cs.getTypeOfLink()));
            if (replaces) {
                List<NearbyConcept> conceptsPrecedents = this.nearbyConcepts.stream()
                        .filter(cs -> "replaces".equals(cs.getTypeOfLink()))
                        .map(cs -> {
                            NearbyConcept inner = new NearbyConcept();
                            inner.setId(cs.getId());
                            inner.setUri(cs.getUri());
                            return inner;
                        })
                        .collect(Collectors.toList());
                concept.setConceptsPrecedents(conceptsPrecedents);
            }

            boolean related = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "related".equals(cs.getTypeOfLink()));
            if (related) {
                List<NearbyConcept> conceptsLies = this.nearbyConcepts.stream()
                        .filter(cs -> "related".equals(cs.getTypeOfLink()))
                        .map(cs -> {
                            NearbyConcept inner = new NearbyConcept();
                            inner.setId(cs.getId());
                            inner.setUri(cs.getUri());
                            return inner;
                        })
                        .collect(Collectors.toList());
                concept.setConceptsLies(conceptsLies);
            }

            boolean closeMatch = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "closeMatch".equals(cs.getTypeOfLink()));
            if (closeMatch) {
                List<NearbyConcept> conceptsProches = this.nearbyConcepts.stream()
                        .filter(cs -> "closeMatch".equals(cs.getTypeOfLink()))
                        .map(cs -> {
                            NearbyConcept inner = new NearbyConcept();
                            inner.setId(cs.getId());
                            inner.setUri(cs.getUri());
                            return inner;
                        })
                        .collect(Collectors.toList());
                concept.setConceptsProches(conceptsProches);
            }

            boolean broader = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "broader".equals(cs.getTypeOfLink()));
            if (broader) {
                List<NearbyConcept> conceptsPlusGeneriques = this.nearbyConcepts.stream()
                        .filter(cs -> "broader".equals(cs.getTypeOfLink()))
                        .map(cs -> {
                            NearbyConcept inner = new NearbyConcept();
                            inner.setId(cs.getId());
                            inner.setUri(cs.getUri());
                            return inner;
                        })
                        .collect(Collectors.toList());
                concept.setConceptsPlusGeneriques(conceptsPlusGeneriques);
            }

            boolean narrower = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "narrower".equals(cs.getTypeOfLink()));
            if (narrower) {
                List<NearbyConcept> conceptsPlusSpecifiques = this.nearbyConcepts.stream()
                        .filter(cs -> "narrower".equals(cs.getTypeOfLink()))
                        .map(cs -> {
                            NearbyConcept inner = new NearbyConcept();
                            inner.setId(cs.getId());
                            inner.setUri(cs.getUri());
                            return inner;
                        })
                        .collect(Collectors.toList());
                concept.setConceptsPlusSpecifiques(conceptsPlusSpecifiques);
            }

            boolean references = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "references".equals(cs.getTypeOfLink()));
            if (references) {
                List<NearbyConcept> conceptsReferences = this.nearbyConcepts.stream()
                        .filter(cs -> "references".equals(cs.getTypeOfLink()))
                        .map(cs -> {
                            NearbyConcept inner = new NearbyConcept();
                            inner.setId(cs.getId());
                            inner.setUri(cs.getUri());
                            return inner;
                        })
                        .collect(Collectors.toList());
                concept.setConceptsReferences(conceptsReferences);
            }


        }
    }


    public ListeConceptsInner transformDTOenDefinition() {
        ListeConceptsInner concept = new ListeConceptsInner();
        concept.setId(this.id);
        concept.setUri(URI.create(this.uri));
        concept.setIntitule(this.intituleFr);


        if (this.nearbyConcepts != null) {
            List<NearbyConcept> conceptsSuivants = new ArrayList<>();
            List<NearbyConcept> conceptsPrecedents = new ArrayList<>();
            List<NearbyConcept> conceptsLies = new ArrayList<>();
            List<NearbyConcept> conceptsProches = new ArrayList<>();
            List<NearbyConcept> conceptsPlusGeneriques = new ArrayList<>();
            List<NearbyConcept> conceptsPlusSpecifiques = new ArrayList<>();
            List<NearbyConcept> conceptsReferences = new ArrayList<>();

            this.nearbyConcepts.forEach(cs -> {
                NearbyConcept inner = new NearbyConcept();
                inner.setId(cs.getId());
                inner.setUri(cs.getUri());

                if ("isReplacedBy".equals(cs.getTypeOfLink())) {
                    conceptsSuivants.add(inner);
                }
                if ("replaces".equals(cs.getTypeOfLink())) {
                    conceptsPrecedents.add(inner);
                }
                if ("related".equals(cs.getTypeOfLink())) {
                    conceptsLies.add(inner);
                }
                if ("closeMatch".equals(cs.getTypeOfLink())) {
                    conceptsProches.add(inner);
                }
                if ("broader".equals(cs.getTypeOfLink())) {
                    conceptsPlusGeneriques.add(inner);
                }
                if ("narrower".equals(cs.getTypeOfLink())) {
                    conceptsPlusSpecifiques.add(inner);
                }
                if ("references".equals(cs.getTypeOfLink())) {
                    conceptsReferences.add(inner);
                }
            });

            concept.setConceptsSuivants(conceptsSuivants);
            concept.setConceptsPrecedents(conceptsPrecedents);
            concept.setConceptsLies(conceptsLies);
            concept.setConceptsProches(conceptsProches);
            concept.setConceptsPlusGeneriques(conceptsPlusGeneriques);
            concept.conceptsPlusSpecifiques(conceptsPlusSpecifiques);
            concept.conceptsReferences(conceptsReferences);
        }

        return concept;
    }


    public List<ConceptIntituleInner> createListLangueContenu(ConceptIntituleInner conceptIntituleInner1, ConceptIntituleInner conceptIntituleInner2) {
        List<ConceptIntituleInner> list = new ArrayList<>();
        list.add(conceptIntituleInner1);
        list.add(conceptIntituleInner2);
        return list;
    }

    public ConceptIntituleInner createLangueContenu(String contenu, String langue) {
        ConceptIntituleInner conceptIntituleInner = new ConceptIntituleInner();
        conceptIntituleInner.setContenu(contenu);
        conceptIntituleInner.setLangue(langue);
        return conceptIntituleInner;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUri() { return uri; }
    public void setUri(String uri) { this.uri = uri; }

    public String getIntituleFr() { return intituleFr; }
    public void setIntituleFr(String intituleFr) { this.intituleFr = intituleFr; }

    public String getIntituleEn() { return intituleEn; }
    public void setIntituleEn(String intituleEn) { this.intituleEn = intituleEn; }

    public String getDefinitionFr() { return definitionFr; }
    public void setDefinitionFr(String definitionFr) { this.definitionFr = definitionFr; }

    public String getDefinitionEn() { return definitionEn; }
    public void setDefinitionEn(String definitionEn) { this.definitionEn = definitionEn; }

    public String getNoteEditorialeFr() { return noteEditorialeFr; }
    public void setNoteEditorialeFr(String noteEditorialeFr) { this.noteEditorialeFr = noteEditorialeFr; }

    public String getNoteEditorialeEn() { return noteEditorialeEn; }

    public void setNoteEditorialeEn(String noteEditorialeEn) { this.noteEditorialeEn = noteEditorialeEn; }

    public void setDateMiseAJour(String dateMiseAJour) { this.dateMiseAJour = dateMiseAJour; }

    public void setHasLink(Boolean hasLink) {
        this.hasLink = hasLink;
    }

    public void setNearbyConcepts(List<NearbyConcept> nearbyConcepts) {
        this.nearbyConcepts = nearbyConcepts;
    }
}
