package fr.insee.rmes.magma.gestion.utils;

public record ComponentByIdDTO (
        String uri,
        String id,
        String type,
        String idMetier,
        String dateMiseAJour,
        String statutValidation,
        String prefLabelLg1,
        String prefLabelLg2,
        String altLabelLg1,
        String altLabelLg2,
        String uriConcept,
        String idConcept,
        String representation,
        String uriListeCodeString,
        String idListeCode,
        String uriParentListCode,
        String idParentListCode,
        String version,
        String uriComponentParentId,
        String uriComponentParentNotation,
        String dateCreation,
        String minLength,
        String maxLength,
        String minInclusive,
        String maxInclusive,
        String pattern
){

}
