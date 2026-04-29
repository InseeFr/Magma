package fr.insee.rmes.magma.gestion.queries.parameters;

import fr.insee.rmes.magma.queries.parameters.ParameterValueDecoder;

import java.time.LocalDate;

@FunctionalInterface
public interface ParameterValueDecoderGestion<T> extends ParameterValueDecoder<T> {

    String LOCALE_DATE_CLASS = "java.time.LocalDate";

    String STRING_CLASS = "java.lang.String";
    String BOOLEAN_CLASS = "java.lang.Boolean";
    String INTEGER_CLASS = "java.lang.Integer";

    static <U> ParameterValueDecoderGestion<U> of(Class<U> type) {
        return switch (type.getName()) {
            case LOCALE_DATE_CLASS -> localDate -> String.valueOf(localDate == null ? LocalDate.now() : localDate);
            case STRING_CLASS -> s -> s == null ? "" : (String) s;
            case BOOLEAN_CLASS -> b -> b == null ? "false" : String.valueOf(b);
            case INTEGER_CLASS -> i -> i == null ? "0" : String.valueOf(i);
            default -> throw new IllegalArgumentException("Unsupported type: " + type.getName());
        };
    }
}
