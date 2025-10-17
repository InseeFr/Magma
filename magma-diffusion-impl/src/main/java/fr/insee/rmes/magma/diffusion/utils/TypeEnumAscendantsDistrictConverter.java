package fr.insee.rmes.magma.diffusion.utils;


import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsDistrict;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumAscendantsDistrictConverter implements Converter<String, TypeEnumAscendantsDistrict> {
    @Override
    public TypeEnumAscendantsDistrict convert(String source) {
        for (TypeEnumAscendantsDistrict type : TypeEnumAscendantsDistrict.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsDistrict : " + source);
    }
}

