package fr.insee.rmes.magma.diffusion.queries.parameters;

import fr.insee.rmes.magma.diffusion.model.*;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record AscendantsDescendantsRequestParametizer(String code,
                                                      LocalDate date,
                                                      TypeEnumDescendantsPays typeEnumDescendantsPays,
                                                      String filtreNom,
                                                      String territoriesFilter,
                                                      Class<?> typeOrigine,
                                                      boolean ascendant) implements ParametersForQuery<AscendantsDescendantsRequestParametizer> {

    //for geo/pays/{code}/descendants
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   TypeEnumDescendantsPays typeEnumDescendantsPays,
                                                   Class<?> typeOrigine) {
        this(code, date, typeEnumDescendantsPays, null, null, typeOrigine, false);
    }

    //for descendants with filtreNom and listeTypesGeo
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   String filtreNom,
                                                   String territoriesFilter,
                                                   Class<?> typeOrigine) {
        this(code, date, null, filtreNom, territoriesFilter, typeOrigine, false);
    }

     //for ascendants/descendants with listeTypesGeo (without filtreNom)
    public AscendantsDescendantsRequestParametizer(String code,
                                                   LocalDate date,
                                                   String territoriesFilter,
                                                   Class<?> typeOrigine,
                                                   boolean ascendant) {
        this(code, date, null, null, territoriesFilter, typeOrigine, ascendant);
    }

    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        if ("filtreNom".equals(recordComponent.getName())) {
            return new ParameterValueDecoder.DelegaterDecoder<>(stringValue -> stringValue == null ? "*" : stringValue.toString());
        }
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }
}