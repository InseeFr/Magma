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

        if (this.nearbyConcepts != null) {
            List<ConceptConceptsSuivantsInner> conceptsSuivants = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsPrecedents = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsLies = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsProches = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsPlusGeneriques = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsPlusSpecifiques = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsReferences = new ArrayList<>();


            this.nearbyConcepts.forEach(cs -> {
                ConceptConceptsSuivantsInner inner = new ConceptConceptsSuivantsInner();
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

        // Conversion de la date en LocalDate
        if (this.dateMiseAJour != null && !this.dateMiseAJour.isEmpty()) {
            try {
                // Parser en LocalDateTime puis extraire la partie LocalDate (ex : 2022-05-09T08:37:09.201144)
                LocalDateTime localDateTime = LocalDateTime.parse(this.dateMiseAJour);
                concept.setDateMiseAJour(localDateTime.toLocalDate());
            } catch (DateTimeParseException e1) {
                try {
                    // Parser en LocalDateTime puis extraire la partie LocalDate (ex: 2016-10-13T00:00:00.000+02:00)
                    OffsetDateTime localDateTime = OffsetDateTime.parse(this.dateMiseAJour);
                    LocalDate localDate = localDateTime.toLocalDate();
                    concept.setDateMiseAJour(localDate);
                } catch (DateTimeParseException e2) {
                    try {
                        // Si le format est directement un LocalDate (ex: "2019-05-17")
                        concept.setDateMiseAJour(LocalDate.parse(this.dateMiseAJour));
                    } catch (DateTimeParseException e3) {
                        // Gérer l'erreur (par exemple, logger ou affecter null)
                        concept.setDateMiseAJour(null);
                    }
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

        if (this.nearbyConcepts != null) {
            List<ConceptConceptsSuivantsInner> conceptsSuivants = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsPrecedents = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsLies = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsProches = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsPlusGeneriques = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsPlusSpecifiques = new ArrayList<>();
            List<ConceptConceptsSuivantsInner> conceptsReferences = new ArrayList<>();

            this.nearbyConcepts.forEach(cs -> {
                ConceptConceptsSuivantsInner inner = new ConceptConceptsSuivantsInner();
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

    public void setNearbyConcepts(List<NearbyConcept> nearbyConcepts) {
        this.nearbyConcepts = nearbyConcepts;
    }
}
