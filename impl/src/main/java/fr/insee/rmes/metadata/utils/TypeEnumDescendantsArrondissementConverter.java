package fr.insee.rmes.metadata.utils;

import fr.insee.rmes.metadata.model.TypeEnumDescendantsArrondissement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumDescendantsArrondissementConverter implements Converter<String, TypeEnumDescendantsArrondissement> {
    @Override
    public TypeEnumDescendantsArrondissement convert(String source) {
        for (TypeEnumDescendantsArrondissement type : TypeEnumDescendantsArrondissement.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsArrondissement: " + source);
    }
}
