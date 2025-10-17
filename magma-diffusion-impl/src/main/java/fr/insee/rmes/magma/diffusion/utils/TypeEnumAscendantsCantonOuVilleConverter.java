package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsCantonOuVille;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class TypeEnumAscendantsCantonOuVilleConverter implements Converter<String, TypeEnumAscendantsCantonOuVille> {
    @Override
    public TypeEnumAscendantsCantonOuVille convert(String source) {
        for (TypeEnumAscendantsCantonOuVille type : TypeEnumAscendantsCantonOuVille.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsCantonOuVille : " + source);
    }
}