package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsCirconscriptionTerritoriale;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class TypeEnumAscendantsCirconscriptionTerritorialeConverter implements Converter<String, TypeEnumAscendantsCirconscriptionTerritoriale> {
    @Override
    public TypeEnumAscendantsCirconscriptionTerritoriale convert(String source) {
        for (TypeEnumAscendantsCirconscriptionTerritoriale type : TypeEnumAscendantsCirconscriptionTerritoriale.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsCirconscriptionTerritoriale : " + source);
    }
}
