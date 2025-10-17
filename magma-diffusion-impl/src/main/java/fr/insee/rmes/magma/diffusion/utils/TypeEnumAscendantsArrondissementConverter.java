package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnumAscendantsArrondissement;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumAscendantsArrondissementConverter implements Converter<String, TypeEnumAscendantsArrondissement> {
   @Override
   public TypeEnumAscendantsArrondissement convert(String source) {
       for (TypeEnumAscendantsArrondissement type : TypeEnumAscendantsArrondissement.values()) {
           if (type.getValue().equalsIgnoreCase(source)) {
               return type;
           }
       }
       throw new IllegalArgumentException("Invalid value for TypeEnumAscendantsArrondissement : " + source);
   }
}


