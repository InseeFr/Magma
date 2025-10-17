package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsCommune;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumDescendantsCommuneConverter implements Converter<String, TypeEnumDescendantsCommune> {
    @Override
    public TypeEnumDescendantsCommune convert(String source) {
        for (TypeEnumDescendantsCommune type : TypeEnumDescendantsCommune.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsCommune: " + source);
    }
}
