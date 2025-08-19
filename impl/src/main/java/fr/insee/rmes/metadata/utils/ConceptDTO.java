package fr.insee.rmes.metadata.utils;

import fr.insee.rmes.metadata.model.Concept;
import fr.insee.rmes.metadata.model.ConceptConceptsSuivantsInner;
import fr.insee.rmes.metadata.model.ConceptIntituleInner;
import fr.insee.rmes.metadata.model.ConceptSuivant;

import java.net.URI;
import java.time.OffsetDateTime;
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
    private OffsetDateTime dateMiseAJour;
    private Boolean hasLink;
    private List<ConceptSuivant> conceptsSuivants;

    public Concept transformDTOenConcept() {
        Concept concept = new Concept();
        concept.setId(this.id);
        concept.setUri(URI.create(this.uri));
        if  (this.intituleFr != null && this.intituleEn != null) {
            List<ConceptIntituleInner> intitules = createListLangueContenu(createLangueContenu(intituleFr,"fr"),createLangueContenu(intituleEn,"en"));
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
        concept.setDateMiseAJour(this.dateMiseAJour);
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

    public OffsetDateTime getDateMiseAJour() { return dateMiseAJour; }
    public void setDateMiseAJour(OffsetDateTime dateMiseAJour) { this.dateMiseAJour = dateMiseAJour; }

    public Boolean getHasLink() {
        return hasLink;
    }

    public void setHasLink(Boolean hasLink) {
        this.hasLink = hasLink;
    }

    public List<ConceptSuivant> getConceptsSuivants() {
        return conceptsSuivants;
    }

    public void setConceptsSuivants(List<ConceptSuivant> conceptsSuivants) {
        this.conceptsSuivants = conceptsSuivants;
    }
}
