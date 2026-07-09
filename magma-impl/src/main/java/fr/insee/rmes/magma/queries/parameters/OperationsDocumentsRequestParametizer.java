package fr.insee.rmes.magma.queries.parameters;

public record OperationsDocumentsRequestParametizer(String idSims, String idRubric, String LANG)
        implements ParametersForQueryDiffusion<OperationsDocumentsRequestParametizer> {
}

