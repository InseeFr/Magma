package fr.insee.rmes.magma.diffusion.queries.parameters;

public record ClassificationRequestParametizer(String nomenclature,
                                               String niveau,
                                               String code) implements ParametersForQueryDiffusion<ClassificationRequestParametizer> {

}
