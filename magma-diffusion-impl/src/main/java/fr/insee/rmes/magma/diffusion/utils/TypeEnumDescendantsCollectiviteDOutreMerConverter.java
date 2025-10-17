package fr.insee.rmes.magma.diffusion.utils;


import fr.insee.rmes.magma.diffusion.model.TypeEnumDescendantsCollectiviteDOutreMer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class TypeEnumDescendantsCollectiviteDOutreMerConverter implements Converter<String, TypeEnumDescendantsCollectiviteDOutreMer> {
    @Override
    public TypeEnumDescendantsCollectiviteDOutreMer convert(String source) {
        for (TypeEnumDescendantsCollectiviteDOutreMer type : TypeEnumDescendantsCollectiviteDOutreMer.values()) {
            if (type.getValue().equalsIgnoreCase(source)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value for TypeEnumDescendantsCollectiviteDOutreMer : " + source);
    }
}
