package fr.insee.rmes.magma.diffusion.queries.parameters;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;


    public record ProjetesRequestParametizer(String code,
                                             LocalDate dateProjection,
                                             LocalDate date,
                                             Class<?> typeOrigine,
                                             boolean previous) implements ParametersForQueryDiffusion<ProjetesRequestParametizer> {


    @Override
    public ParameterValueDecoderDiffusion<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQueryDiffusion.super.findParameterValueDecoder(recordComponent);
    }

}