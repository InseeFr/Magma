package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsRegion;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumDescendantsRegionConverter implements Converter<String, TypeEnumDescendantsRegion> {
    @Override
    public TypeEnumDescendantsRegion convert(String source) {
        for (TypeEnumDescendantsRegion type : TypeEnumDescendantsRegion.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsRegion: " + source);
    }
}
