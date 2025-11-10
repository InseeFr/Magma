package fr.insee.rmes.magma.diffusion.queries.parameters;

import fr.insee.rmes.magma.diffusion.model.TypeEnum;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;


public record TerritoiresLiesRequestParametizer(String code,
                                                LocalDate date,
                                                TypeEnum type,
                                                Class<?> typeOrigine) implements ParametersForQuery<TerritoiresLiesRequestParametizer> {


    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}