package fr.insee.rmes.magma.gestion.utils;

public record StructureDTO(
        String uri,
        String id,
        String notation,
        String prefLabelLg1,
        String prefLabelLg2,
        String dateCreation,
        String dateMiseAJour,
        String dateFinValidite,
        String statutValidation,
        String version,
        String idRelation,
        String necessairePour,
        String idParent,
        String uriParent,
        String idParentRelation

) {
}
