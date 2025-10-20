package fr.insee.rmes.magma.diffusion.queries.parameters;

public record ClassificationRequestParametizer(String classification,
                                               String level,
                                               String code) implements ParametersForQuery<ClassificationRequestParametizer> {
}