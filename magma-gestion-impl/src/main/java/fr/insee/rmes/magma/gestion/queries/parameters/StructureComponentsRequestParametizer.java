package fr.insee.rmes.magma.gestion.queries.parameters;

import java.time.LocalDate;

public record StructureComponentsRequestParametizer(LocalDate date, Class<?> typeOrigine) implements ParametersForQueryGestion<StructureComponentsRequestParametizer> {
}
