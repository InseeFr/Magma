package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsCantonOuVille;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumDescendantsCantonOuVilleConverter implements Converter<String, TypeEnumDescendantsCantonOuVille> {
    @Override
    public TypeEnumDescendantsCantonOuVille convert(String source) {
        for (TypeEnumDescendantsCantonOuVille type : TypeEnumDescendantsCantonOuVille.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsCantonOuVille: " + source);
    }
}
