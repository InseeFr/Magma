package fr.insee.rmes.magma.gestion.utils;

import java.time.LocalDate;

public record OperationDTO(
        String operationId,
        String operation,
        String operationLabelLg1,
        String operationLabelLg2,
        String operationAltLabelLg1,
        String operationAltLabelLg2,
        String temporal,
        String seriesId,
        String series,
        String seriesLabelLg1,
        String seriesLabelLg2,
        String simsId,
        String sims,
        LocalDate created,
        LocalDate modified,
        String validationState
) {
}