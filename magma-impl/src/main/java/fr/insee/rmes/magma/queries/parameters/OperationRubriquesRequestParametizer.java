package fr.insee.rmes.magma.queries.parameters;

public record OperationRubriquesRequestParametizer(String idSims, String LG1_CL, String LG2_CL)
        implements ParametersForQueryDiffusion<OperationRubriquesRequestParametizer> {

}