package fr.insee.rmes.metadata.queries.parameters;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record IrisListRequestParametizer (LocalDate date,
                                          boolean com) implements ParametersForQuery<IrisListRequestParametizer>{

    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }


}




