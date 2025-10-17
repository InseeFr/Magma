package fr.insee.rmes.magma.diffusion.utils;



import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsCommune;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumAscendantsCommuneConverter implements Converter<String, TypeEnumAscendantsCommune> {
    @Override
    public TypeEnumAscendantsCommune convert(String source) {
        for (TypeEnumAscendantsCommune type : TypeEnumAscendantsCommune.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumInclusDansCommune: " + source);
    }
}

