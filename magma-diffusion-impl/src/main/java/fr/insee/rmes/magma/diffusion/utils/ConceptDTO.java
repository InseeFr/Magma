package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.LocalisedLabel;
import fr.insee.rmes.magma.diffusion.model.NearbyConcept;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j


public record ConceptDTO(

    String id,
    String uri,
    String intituleFr,
    String intituleEn,
    String definitionFr,
    String definitionEn,
    String scopeNoteFr,
    String scopeNoteEn,
    String noteEditorialeFr,
    String noteEditorialeEn,
    String dateMiseAJour,
    String dateCreation,
    String dateFinDeValidite,
    @Getter
    Boolean hasLink,
    @Getter
    Boolean hasIntitulesAlternatifs,
    @Getter
    List<NearbyConcept> nearbyConcepts,
    List<LocalisedLabel> intitulesAlternatifs
)
{

   public ConceptDTO withDateCreation(String dateCreation) {
       return new ConceptDTO(id, uri, intituleFr, intituleEn, definitionFr, definitionEn, scopeNoteFr, scopeNoteEn, noteEditorialeFr, noteEditorialeEn, dateMiseAJour, dateCreation, dateFinDeValidite, hasLink, hasIntitulesAlternatifs, nearbyConcepts, intitulesAlternatifs);
   }

    public ConceptDTO withNearbyConcepts(List<NearbyConcept> nearbyConcepts) {
        return new ConceptDTO(id, uri, intituleFr, intituleEn, definitionFr, definitionEn, scopeNoteFr, scopeNoteEn, noteEditorialeFr, noteEditorialeEn, dateMiseAJour, dateCreation, dateFinDeValidite, hasLink, hasIntitulesAlternatifs, nearbyConcepts, intitulesAlternatifs);
    }

    public ConceptDTO withIntitulesAlternatifs(List<LocalisedLabel> intitulesAlternatifs) {
        return new ConceptDTO(id, uri, intituleFr, intituleEn, definitionFr, definitionEn, scopeNoteFr, scopeNoteEn, noteEditorialeFr, noteEditorialeEn, dateMiseAJour, dateCreation, dateFinDeValidite, hasLink, hasIntitulesAlternatifs, nearbyConcepts, intitulesAlternatifs);
    }

    public boolean hasLinkValue() {
        return Boolean.TRUE.equals(hasLink);
    }

    public boolean hasIntitulesAlternatifsValue(){
       return Boolean.TRUE.equals(hasIntitulesAlternatifs);
    }
}
