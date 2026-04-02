package fr.insee.rmes.magma.gestion.queries.parameters;

public record SeriesOperationsRequestParametizer(String idSeries, String operationId) implements ParametersForQueryGestion<SeriesOperationsRequestParametizer> {
}