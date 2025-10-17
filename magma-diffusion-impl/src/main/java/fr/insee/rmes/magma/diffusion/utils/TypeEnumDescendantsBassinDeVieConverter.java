package fr.insee.rmes.magma.diffusion.utils;

import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsBassinDeVie;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumDescendantsBassinDeVieConverter implements Converter<String, TypeEnumDescendantsBassinDeVie> {
    @Override
    public TypeEnumDescendantsBassinDeVie convert(String source) {
        for (TypeEnumDescendantsBassinDeVie type : TypeEnumDescendantsBassinDeVie.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsBassinDeVie: " + source);
    }
}