package fr.insee.rmes.metadata.utils;


import fr.insee.rmes.metadata.model.TypeEnumAscendantsCommuneAssociee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumAscendantsCommuneAssocieeConverter implements Converter<String, TypeEnumAscendantsCommuneAssociee> {
    @Override
    public TypeEnumAscendantsCommuneAssociee convert(String source) {
        for (TypeEnumAscendantsCommuneAssociee type : TypeEnumAscendantsCommuneAssociee.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsCommuneAssociee : " + source);
    }
}
