package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsDepartement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumAscendantsDepartementConverter implements Converter<String, TypeEnumAscendantsDepartement> {
    @Override
    public TypeEnumAscendantsDepartement convert(String source) {
        for (TypeEnumAscendantsDepartement type : TypeEnumAscendantsDepartement.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsDepartement : " + source);
    }
}
