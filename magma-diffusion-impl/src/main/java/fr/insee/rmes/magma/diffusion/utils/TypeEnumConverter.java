package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumConverter implements Converter<String, TypeEnum> {
    @Override
    public TypeEnum convert(String source) {
        for (TypeEnum type : TypeEnum.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnum : " + source);
    }


}

