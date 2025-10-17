package fr.insee.rmes.magma.diffusion.utils;


import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsIntercommunalite;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumDescendantsIntercommunaliteConverter implements Converter<String, TypeEnumDescendantsIntercommunalite> {
    @Override
    public TypeEnumDescendantsIntercommunalite convert(String source) {
        for (TypeEnumDescendantsIntercommunalite type : TypeEnumDescendantsIntercommunalite.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsIntercommunalite: " + source);
    }
}
