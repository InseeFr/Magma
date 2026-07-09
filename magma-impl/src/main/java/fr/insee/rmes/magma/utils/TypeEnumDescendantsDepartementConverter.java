package fr.insee.rmes.magma.utils;

import fr.insee.rmes.magma.model.TypeEnumDescendantsDepartement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumDescendantsDepartementConverter implements Converter<String, TypeEnumDescendantsDepartement> {
    @Override
    public TypeEnumDescendantsDepartement convert(String source) {
        for (TypeEnumDescendantsDepartement type : TypeEnumDescendantsDepartement.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsDepartement: " + source);
    }
}
