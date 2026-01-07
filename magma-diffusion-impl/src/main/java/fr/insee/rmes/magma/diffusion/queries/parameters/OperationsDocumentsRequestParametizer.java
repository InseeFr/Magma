package fr.insee.rmes.magma.diffusion.queries.parameters;

public record OperationsDocumentsRequestParametizer(String idSims, String idRubric, String LANG)
        implements ParametersForQuery<OperationsDocumentsRequestParametizer> {
}

