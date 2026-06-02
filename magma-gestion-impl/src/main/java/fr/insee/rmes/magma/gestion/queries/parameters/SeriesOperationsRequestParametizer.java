package fr.insee.rmes.magma.gestion.queries.parameters;

public record SeriesOperationsRequestParametizer(String idSeries, String operationId, String date)
        implements ParametersForQueryGestion<SeriesOperationsRequestParametizer> {

    public SeriesOperationsRequestParametizer(String idSeries, String operationId) {
        this(idSeries, operationId, "");
    }
}