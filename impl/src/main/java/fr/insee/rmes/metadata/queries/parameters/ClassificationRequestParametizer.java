package fr.insee.rmes.metadata.queries.parameters;

public record ClassificationRequestParametizer(String classification,
                                               String level,
                                               String code) implements ParametersForQuery<ClassificationRequestParametizer> {

}
