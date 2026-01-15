package fr.insee.rmes.magma.gestion.queries.parameters;

import fr.insee.rmes.magma.queries.parameters.ParameterValueDecoder;

import java.time.LocalDate;

@FunctionalInterface
public interface ParameterValueDecoderGestion<T> extends ParameterValueDecoder<T> {

    String LOCALE_DATE_CLASS = "java.time.LocalDate";

    static <U> ParameterValueDecoderGestion<U> of(Class<U> type) {
        return switch (type.getName()) {
            case LOCALE_DATE_CLASS -> localDate -> String.valueOf(localDate == null ? LocalDate.now() : localDate);
            default -> throw new IllegalArgumentException("Unsupported type: " + type.getName());
        };
    }
}
