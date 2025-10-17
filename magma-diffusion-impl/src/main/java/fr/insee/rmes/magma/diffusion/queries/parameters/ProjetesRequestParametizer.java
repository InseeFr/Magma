package fr.insee.rmes.magma.diffusion.queries.parameters;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;


    public record ProjetesRequestParametizer(String code,
                                             LocalDate dateProjection,
                                             LocalDate date,
                                             Class<?> typeOrigine,
                                             boolean previous) implements ParametersForQuery<ProjetesRequestParametizer> {


    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }

}