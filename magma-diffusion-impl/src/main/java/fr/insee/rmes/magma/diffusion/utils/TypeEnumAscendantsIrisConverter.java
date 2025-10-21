package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsIris;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumAscendantsIrisConverter implements Converter<String, TypeEnumAscendantsIris> {
    @Override
    public TypeEnumAscendantsIris convert(String source) {
        for (TypeEnumAscendantsIris type : TypeEnumAscendantsIris.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsIris : " + source);
    }
}