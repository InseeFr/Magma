package fr.insee.rmes.magma.diffusion.queries.parameters;

public record OperationRubriquesRequestParametizer(String idSims, String LG1_CL, String LG2_CL)
        implements ParametersForQuery<OperationRubriquesRequestParametizer> {

}