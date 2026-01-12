package fr.insee.rmes.magma.diffusion.queries.parameters;

import fr.insee.rmes.magma.diffusion.model.TypeEnum;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;


public record TerritoiresLiesRequestParametizer(String code,
                                                LocalDate date,
                                                TypeEnum type,
                                                Class<?> typeOrigine) implements ParametersForQueryDiffusion<TerritoiresLiesRequestParametizer> {


    @Override
    public ParameterValueDecoderDiffusion<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQueryDiffusion.super.findParameterValueDecoder(recordComponent);
    }
}