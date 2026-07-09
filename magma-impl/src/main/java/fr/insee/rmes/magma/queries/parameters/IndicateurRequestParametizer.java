package fr.insee.rmes.magma.queries.parameters;

public record IndicateurRequestParametizer(String indicatorId)
        implements ParametersForQueryGestion<IndicateurRequestParametizer> {
}