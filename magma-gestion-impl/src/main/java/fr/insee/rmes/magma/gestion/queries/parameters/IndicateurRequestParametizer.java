package fr.insee.rmes.magma.gestion.queries.parameters;

public record IndicateurRequestParametizer(String indicatorId)
        implements ParametersForQueryGestion<IndicateurRequestParametizer> {
}