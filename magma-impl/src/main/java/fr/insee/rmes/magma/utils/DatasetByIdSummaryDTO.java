package fr.insee.rmes.magma.utils;

public record DatasetByIdSummaryDTO(
        String uri,
        String id,
        String catalogRecordModified
) {
}