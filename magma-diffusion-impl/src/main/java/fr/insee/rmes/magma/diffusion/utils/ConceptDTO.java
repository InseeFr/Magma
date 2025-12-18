package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private List<LangueContenu> intitulesAlternatifs;


    public Concept transformDTOenConcept() {
        Concept concept = new Concept();
        concept.setId(this.id);
        concept.setUri(URI.create(this.uri));
        if  (this.intituleFr != null || this.intituleEn != null) {
            List<LangueContenu> intitules = createListLangueContenu(createLangueContenu(intituleFr,"fr"),createLangueContenu(intituleEn,"en"));
            concept.setIntitule(intitules);
        }

        if  (this.definitionFr != null || this.definitionEn != null) {
            List<LangueContenu> definitions = createListLangueContenu(createLangueContenu(definitionFr,"fr"),createLangueContenu(definitionEn,"en"));
            concept.setDefinition(definitions);
        }
        else concept.setDefinition(null);

        if  (this.scopeNoteFr != null || this.scopeNoteEn != null) {
            List<LangueContenu> definitionCourte = createListLangueContenu(createLangueContenu(scopeNoteFr,"fr"),createLangueContenu(scopeNoteEn,"en"));
            concept.setDefinitionCourte(definitionCourte);
        }
        else concept.setDefinitionCourte(null);

        if  (this.noteEditorialeFr != null || this.noteEditorialeEn != null) {
            List<LangueContenu> noteEditoriale = createListLangueContenu(createLangueContenu(noteEditorialeFr,"fr"),createLangueContenu(noteEditorialeEn,"en"));
            concept.setNoteEditoriale(noteEditoriale);
        }
        else concept.setNoteEditoriale(null);


        if (hasIntitulesAlternatifs) {
            addIntitulesAlternatifs(concept);
        }
        else concept.setIntitulesAlternatifs(null);


        addNearByConcepts(concept);

        //Dates
        concept.setDateMiseAJour(tryParseDateToLocalDate(this.dateMiseAJour));
        concept.setDateCreation(tryParseDateToLocalDate(this.dateCreation));
        concept.setDateFinDeValidite(tryParseDateToLocalDate(this.dateFinDeValidite));


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

    private void addNearByConcepts(Concept concept) {
        concept.setConceptsSuivants(null);
        concept.setConceptsPrecedents(null);
        concept.setConceptsLies(null);
        concept.setConceptsProches(null);
        concept.setConceptsPlusGeneriques(null);
        concept.setConceptsPlusSpecifiques(null);
        concept.setConceptsReferences(null);
        if (this.nearbyConcepts != null) {
            this.nearbyConcepts.forEach(c -> {
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

    private void addNearByConcepts(ConceptForList concept) {
        concept.setConceptsSuivants(null);
        concept.setConceptsPrecedents(null);
        concept.setConceptsLies(null);
        concept.setConceptsProches(null);
        concept.setConceptsPlusGeneriques(null);
        concept.setConceptsPlusSpecifiques(null);
        concept.setConceptsReferences(null);
        if (this.nearbyConcepts != null) {
            this.nearbyConcepts.forEach(c -> {
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
         for (LangueContenu item : intitulesAlternatifs) {
             LangueContenu newIntitule = createLangueContenu(item.getContenu(), item.getLangue());
         concept.addIntitulesAlternatifsItem(newIntitule);
    }
}


    public ConceptForList transformDTOenDefinition() {
        ConceptForList concept = new ConceptForList();
        concept.setId(this.id);
        concept.setUri(URI.create(this.uri));
        concept.setIntitule(this.intituleFr);

        addNearByConcepts(concept);

        return concept;
    }


    public List<LangueContenu> createListLangueContenu(LangueContenu conceptIntituleInner1, LangueContenu conceptIntituleInner2) {
        List<LangueContenu> list = new ArrayList<>();
        if (conceptIntituleInner1 != null) {
            list.add(conceptIntituleInner1);
        }
        if (conceptIntituleInner2 != null) {
            list.add(conceptIntituleInner2);
        }

        return list;
    }

    public LangueContenu createLangueContenu(String contenu, String langue) {
        LangueContenu conceptIntituleInner = new LangueContenu();
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

    public void setIntitulesAlternatifs(List<LangueContenu> intitulesAlternatifs) {
        this.intitulesAlternatifs = intitulesAlternatifs;
    }

    public void setNearbyConcepts(List<NearbyConcept> nearbyConcepts) {
        this.nearbyConcepts = nearbyConcepts;
    }
}
