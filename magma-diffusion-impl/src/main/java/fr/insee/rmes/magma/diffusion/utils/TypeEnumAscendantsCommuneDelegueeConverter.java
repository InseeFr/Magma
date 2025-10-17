package fr.insee.rmes.magma.diffusion.utils;


import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsCommuneDeleguee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumAscendantsCommuneDelegueeConverter implements Converter<String, TypeEnumAscendantsCommuneDeleguee> {
    @Override
    public TypeEnumAscendantsCommuneDeleguee convert(String source) {
        for (TypeEnumAscendantsCommuneDeleguee type : TypeEnumAscendantsCommuneDeleguee.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsCommuneDeleguee : " + source);
    }
}
