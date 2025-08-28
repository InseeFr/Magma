package fr.insee.rmes.metadata.queries.parameters;

import java.time.LocalDate;

public record ConceptRequestParametizer(String id,
                                        Class<?> typeOrigine,
                                        String label) implements ParametersForQuery<ConceptRequestParametizer> {




}
