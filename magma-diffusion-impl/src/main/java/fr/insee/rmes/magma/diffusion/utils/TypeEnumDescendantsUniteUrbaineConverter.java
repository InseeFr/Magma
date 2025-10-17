package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsUniteUrbaine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumDescendantsUniteUrbaineConverter implements Converter<String, TypeEnumDescendantsUniteUrbaine> {
    @Override
    public TypeEnumDescendantsUniteUrbaine convert(String source) {
        for (TypeEnumDescendantsUniteUrbaine type : TypeEnumDescendantsUniteUrbaine.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsUniteUrbaine: " + source);
    }
}
