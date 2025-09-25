package fr.insee.rmes.metadata.utils;

import fr.insee.rmes.metadata.model.*;
import lombok.Getter;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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
    private List<ConceptSuivant> conceptsSuivants;


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
        if (this.conceptsSuivants != null) {
            List<ConceptConceptsSuivantsInner> mapped = this.conceptsSuivants.stream()
                    .map(cs -> {
                        ConceptConceptsSuivantsInner inner = new ConceptConceptsSuivantsInner();
                        inner.setId(cs.getId());
                        inner.setUri(cs.getUri());
                        inner.setTypeOfLink(cs.getTypeOfLink());
                        return inner;
                    })
                    .toList();
            concept.setConceptsSuivants(mapped);
        }

        // Conversion de la date en LocalDate
        if (this.dateMiseAJour != null && !this.dateMiseAJour.isEmpty()) {
            try {
                // Parser en LocalDateTime puis extraire la partie LocalDate
                OffsetDateTime localDateTime = OffsetDateTime.parse(this.dateMiseAJour);
                LocalDate localDate = localDateTime.toLocalDate();
                concept.setDateMiseAJour(localDate);
            } catch (DateTimeParseException e) {
                try {
                    // Si le format est directement un LocalDate (ex: "2019-05-17")
                    concept.setDateMiseAJour(LocalDate.parse(this.dateMiseAJour));
                } catch (DateTimeParseException ex) {
                    // Gérer l'erreur (par exemple, logger ou affecter null)
                    concept.setDateMiseAJour(null);
                }
            }
        } else {
            concept.setDateMiseAJour(null);
        }

        return concept;
    }

    public ListeConceptsInner transformDTOenDefinition() {
        ListeConceptsInner concept = new ListeConceptsInner();
        concept.setId(this.id);
        concept.setUri(URI.create(this.uri));
        concept.setIntitule(this.intituleFr);


        if (this.conceptsSuivants != null) {
            List<ConceptConceptsSuivantsInner> mapped = this.conceptsSuivants.stream()
                    .map(cs -> {
                        ConceptConceptsSuivantsInner inner = new ConceptConceptsSuivantsInner();
                        inner.setId(cs.getId());
                        inner.setUri(cs.getUri());
                        inner.setTypeOfLink(cs.getTypeOfLink());
                        return inner;
                    })
                    .toList();
            concept.setConceptsSuivants(mapped);
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


    public LocalDate getDateMiseAJour() {
        if (this.dateMiseAJour == null || this.dateMiseAJour.isEmpty()) {
            return null; // ou une valeur par défaut
        }

    try {
        // Essayer de parser en tant que LocalDateTime (ex: "2019-05-17T14:04:34.437")
        LocalDateTime localDateTime = LocalDateTime.parse(this.dateMiseAJour);
        return localDateTime.toLocalDate();
    } catch (DateTimeParseException e) {
        try {
            // Si échec, essayer de parser en tant que LocalDate (ex: "2019-05-17")
            return LocalDate.parse(this.dateMiseAJour);
        } catch (DateTimeParseException ex) {
            // Gérer l'erreur (par exemple, logger ou retourner null)
            return null;
        }
    }
}

    public void setDateMiseAJour(String dateMiseAJour) { this.dateMiseAJour = dateMiseAJour; }


    public void setHasLink(Boolean hasLink) {
        this.hasLink = hasLink;
    }

    public void setConceptsSuivants(List<ConceptSuivant> conceptsSuivants) {
        this.conceptsSuivants = conceptsSuivants;
    }
}
