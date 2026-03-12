package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.Concept;
import fr.insee.rmes.magma.diffusion.model.ConceptForList;
import fr.insee.rmes.magma.diffusion.model.LocalisedLabel;
import fr.insee.rmes.magma.diffusion.model.NearbyConcept;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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

}
