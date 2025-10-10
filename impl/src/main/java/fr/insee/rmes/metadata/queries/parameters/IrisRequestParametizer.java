package fr.insee.rmes.metadata.queries.parameters;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record IrisRequestParametizer(String code,
                                     LocalDate date,
                                     boolean com) implements ParametersForQuery<IrisRequestParametizer>{

    //for geo/iris
    public IrisRequestParametizer(LocalDate date,
                                  boolean com) {
        this("none", date, com);
    }

    //for geo/iris/{code} hasIrisDescendant
    public IrisRequestParametizer(String code, LocalDate date) {
        this(code, date, true); //com could be true or false
    }

    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }


}




