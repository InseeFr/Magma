package fr.insee.rmes.magma.gestion.utils;

public record DatasetByIdSummaryDTO(
        String uri,
        String id,
        String catalogRecordModified
) {
}