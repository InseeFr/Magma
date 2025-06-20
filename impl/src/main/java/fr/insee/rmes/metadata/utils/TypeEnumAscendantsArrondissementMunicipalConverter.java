package fr.insee.rmes.metadata.utils;

import fr.insee.rmes.metadata.model.TypeEnumAscendantsArrondissementMunicipal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumAscendantsArrondissementMunicipalConverter implements Converter<String, TypeEnumAscendantsArrondissementMunicipal> {
    @Override
    public TypeEnumAscendantsArrondissementMunicipal convert(String source) {
        for (TypeEnumAscendantsArrondissementMunicipal type : TypeEnumAscendantsArrondissementMunicipal.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsArrondissementMunicipal : " + source);
    }
}
