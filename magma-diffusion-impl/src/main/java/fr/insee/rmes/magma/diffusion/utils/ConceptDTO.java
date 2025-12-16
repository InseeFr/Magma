package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

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
    private String scopeNoteFr;
    private String scopeNoteEn;
    private String noteEditorialeFr;
    private String noteEditorialeEn;
    private String dateMiseAJour;
    private String dateCreation;
    private String dateFinDeValidite;
    @Getter
    private Boolean hasLink;
    @Getter
    private Boolean hasIntitulesAlternatifs;
    @Getter
    private List<NearbyConcept> nearbyConcepts;
    private List<ConceptIntituleInner> intitulesAlternatifs;


    public Concept transformDTOenConcept() {
        Concept concept = new Concept();
        concept.setId(this.id);
        concept.setUri(URI.create(this.uri));
        if  (this.intituleFr != null || this.intituleEn != null) {
            List<ConceptIntituleInner> intitules = createListLangueContenu(createLangueContenu(intituleFr,"fr"),createLangueContenu(intituleEn,"en"));
            concept.setIntitule(intitules);
        }

        if  (this.definitionFr != null || this.definitionEn != null) {
            List<ConceptIntituleInner> definitions = createListLangueContenu(createLangueContenu(definitionFr,"fr"),createLangueContenu(definitionEn,"en"));
            concept.setDefinition(definitions);
        }
        else concept.setDefinition(null);

        if  (this.scopeNoteFr != null || this.scopeNoteEn != null) {
            List<ConceptIntituleInner> definitionCourte = createListLangueContenu(createLangueContenu(scopeNoteFr,"fr"),createLangueContenu(scopeNoteEn,"en"));
            concept.setDefinitionCourte(definitionCourte);
        }
        else concept.setDefinitionCourte(null);

        if  (this.noteEditorialeFr != null || this.noteEditorialeEn != null) {
            List<ConceptIntituleInner> noteEditoriale = createListLangueContenu(createLangueContenu(noteEditorialeFr,"fr"),createLangueContenu(noteEditorialeEn,"en"));
            concept.setNoteEditoriale(noteEditoriale);
        }
        else concept.setNoteEditoriale(null);


        if (hasIntitulesAlternatifs) {
            addIntitulesAlternatifs(concept);
        }
        else concept.setIntitulesAlternatifs(null);


        addNearByConcepts(concept);

        //Traitement des dates

        // Conversion de dateMiseAJour en LocalDate
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

        // Conversion de dateCreation  en LocalDate
        if (this.dateCreation  != null && !this.dateCreation .isEmpty()) {
            try {
                // Utilise Instant.parse() qui gère automatiquement les formats ISO
                concept.setDateCreation(Instant.parse(this.dateCreation ).atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            } catch (DateTimeParseException e) {
                log.error("IMPOSSIBLE TO PARSE THE DATE '{}' : {}", this.dateCreation , e.getMessage());
                concept.setDateCreation(null);
            }
        } else {
            concept.setDateCreation (null);
        }

        // Conversion de dateFinDeValidite  en LocalDate
        if (this.dateFinDeValidite  != null && !this.dateFinDeValidite .isEmpty()) {
            try {
                // Utilise Instant.parse() qui gère automatiquement les formats ISO
                concept.setDateFinDeValidite(Instant.parse(this.dateFinDeValidite ).atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            } catch (DateTimeParseException e) {
                log.error("IMPOSSIBLE TO PARSE THE DATE '{}' : {}", this.dateFinDeValidite , e.getMessage());
                concept.setDateFinDeValidite(null);
            }
        } else {
            concept.setDateFinDeValidite (null);
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
                List<NearbyConcept> conceptsSuivants = getNearbyConceptList("isReplacedBy");
                concept.setConceptsSuivants(conceptsSuivants);
            }

            boolean replaces = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "replaces".equals(cs.getTypeOfLink()));
            if (replaces) {
                List<NearbyConcept> conceptsPrecedents = getNearbyConceptList("replaces");
                concept.setConceptsPrecedents(conceptsPrecedents);
            }

            boolean related = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "related".equals(cs.getTypeOfLink()));
            if (related) {
                List<NearbyConcept> conceptsLies = getNearbyConceptList("related");
                concept.setConceptsLies(conceptsLies);
            }

            boolean closeMatch = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "closeMatch".equals(cs.getTypeOfLink()));
            if (closeMatch) {
                List<NearbyConcept> conceptsProches = getNearbyConceptList("closeMatch");
                concept.setConceptsProches(conceptsProches);
            }

            boolean broader = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "broader".equals(cs.getTypeOfLink()));
            if (broader) {
                List<NearbyConcept> conceptsPlusGeneriques = getNearbyConceptList("broader");
                concept.setConceptsPlusGeneriques(conceptsPlusGeneriques);
            }

            boolean narrower = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "narrower".equals(cs.getTypeOfLink()));
            if (narrower) {
                List<NearbyConcept> conceptsPlusSpecifiques = getNearbyConceptList("narrower");
                concept.setConceptsPlusSpecifiques(conceptsPlusSpecifiques);
            }

            boolean references = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "references".equals(cs.getTypeOfLink()));
            if (references) {
                List<NearbyConcept> conceptsReferences = getNearbyConceptList("references");
                concept.setConceptsReferences(conceptsReferences);
            }


        }
    }

    private void addNearByConcepts(ListeConceptsInner concept) {
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
                List<NearbyConcept> conceptsSuivants = getNearbyConceptList("isReplacedBy");
                concept.setConceptsSuivants(conceptsSuivants);
            }

            boolean replaces = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "replaces".equals(cs.getTypeOfLink()));
            if (replaces) {
                List<NearbyConcept> conceptsPrecedents = getNearbyConceptList("replaces");
                concept.setConceptsPrecedents(conceptsPrecedents);
            }

            boolean related = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "related".equals(cs.getTypeOfLink()));
            if (related) {
                List<NearbyConcept> conceptsLies = getNearbyConceptList("related");
                concept.setConceptsLies(conceptsLies);
            }

            boolean closeMatch = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "closeMatch".equals(cs.getTypeOfLink()));
            if (closeMatch) {
                List<NearbyConcept> conceptsProches = getNearbyConceptList("closeMatch");
                concept.setConceptsProches(conceptsProches);
            }

            boolean broader = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "broader".equals(cs.getTypeOfLink()));
            if (broader) {
                List<NearbyConcept> conceptsPlusGeneriques = getNearbyConceptList("broader");
                concept.setConceptsPlusGeneriques(conceptsPlusGeneriques);
            }

            boolean narrower = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "narrower".equals(cs.getTypeOfLink()));
            if (narrower) {
                List<NearbyConcept> conceptsPlusSpecifiques = getNearbyConceptList("narrower");
                concept.setConceptsPlusSpecifiques(conceptsPlusSpecifiques);
            }

            boolean references = this.nearbyConcepts.stream()
                    .anyMatch(cs -> "references".equals(cs.getTypeOfLink()));
            if (references) {
                List<NearbyConcept> conceptsReferences = getNearbyConceptList("references");
                concept.setConceptsReferences(conceptsReferences);
            }


        }
    }

    private @NotNull List<NearbyConcept> getNearbyConceptList(String typeOfLink) {
        return this.nearbyConcepts.stream()
                .filter(cs -> typeOfLink.equals(cs.getTypeOfLink()))
                .map(cs -> {
                    NearbyConcept inner = new NearbyConcept();
                    inner.setId(cs.getId());
                    inner.setUri(cs.getUri());
                    return inner;
                })
                .collect(Collectors.toList());
    }


    private void addIntitulesAlternatifs(Concept concept) {
         for (ConceptIntituleInner item : intitulesAlternatifs) {
         ConceptIntituleInner newIntitule = createLangueContenu(item.getContenu(), item.getLangue());
         concept.addIntitulesAlternatifsItem(newIntitule);
    }
}


    public ListeConceptsInner transformDTOenDefinition() {
        ListeConceptsInner concept = new ListeConceptsInner();
        concept.setId(this.id);
        concept.setUri(URI.create(this.uri));
        concept.setIntitule(this.intituleFr);

        addNearByConcepts(concept);

        return concept;
    }


    public List<ConceptIntituleInner> createListLangueContenu(ConceptIntituleInner conceptIntituleInner1, ConceptIntituleInner conceptIntituleInner2) {
        List<ConceptIntituleInner> list = new ArrayList<>();
        if (conceptIntituleInner1 != null) {
            list.add(conceptIntituleInner1);
        }
        if (conceptIntituleInner2 != null) {
            list.add(conceptIntituleInner2);
        }

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

    public String getScopeNoteFr() { return scopeNoteFr; }
    public void setScopeNoteFr(String scopeNoteFr) { this.scopeNoteFr = scopeNoteFr; }

    public String getScopeNoteEn() { return scopeNoteEn; }
    public void setScopeNoteEn(String scopeNoteEn) { this.scopeNoteEn = scopeNoteEn; }

    public String getNoteEditorialeFr() { return noteEditorialeFr; }
    public void setNoteEditorialeFr(String noteEditorialeFr) { this.noteEditorialeFr = noteEditorialeFr; }

    public String getNoteEditorialeEn() { return noteEditorialeEn; }

    public void setNoteEditorialeEn(String noteEditorialeEn) { this.noteEditorialeEn = noteEditorialeEn; }

    public void setDateMiseAJour(String dateMiseAJour) { this.dateMiseAJour = dateMiseAJour; }
    public void setDateCreation(String dateCreation) { this.dateCreation = dateCreation; }
    public void setDateFinDeValidite(String dateFinDeValidite) { this.dateFinDeValidite = dateFinDeValidite; }

    public void setHasLink(Boolean hasLink) {
        this.hasLink = hasLink;
    }

    public void setHasIntitulesAlternatifs(Boolean hasIntitulesAlternatifs) {
        this.hasIntitulesAlternatifs = hasIntitulesAlternatifs;
    }

    public void setIntitulesAlternatifs(List<ConceptIntituleInner> intitulesAlternatifs) {
        this.intitulesAlternatifs = intitulesAlternatifs;
    }

    public void setNearbyConcepts(List<NearbyConcept> nearbyConcepts) {
        this.nearbyConcepts = nearbyConcepts;
    }
}
