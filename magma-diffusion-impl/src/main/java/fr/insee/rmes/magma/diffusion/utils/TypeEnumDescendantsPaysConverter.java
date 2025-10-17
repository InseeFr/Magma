package fr.insee.rmes.magma.diffusion.utils;


import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsPays;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumDescendantsPaysConverter implements Converter<String, TypeEnumDescendantsPays> {
    @Override
    public TypeEnumDescendantsPays convert(String source) {
        for (TypeEnumDescendantsPays type : TypeEnumDescendantsPays.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsPays: " + source);
    }
}