package fr.insee.rmes.magma.diffusion.queries.parameters;

import fr.insee.rmes.magma.queries.parameters.ParameterValueDecoder;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record PrecedentsSuivantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   Class<?> typeOrigine,
                                                   boolean previous) implements ParametersForQueryDiffusion<PrecedentsSuivantsRequestParametizer> {


    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQueryDiffusion.super.findParameterValueDecoder(recordComponent);
    }
}

