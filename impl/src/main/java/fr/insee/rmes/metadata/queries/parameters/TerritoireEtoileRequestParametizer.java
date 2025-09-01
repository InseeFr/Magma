package fr.insee.rmes.metadata.queries.parameters;


import java.lang.reflect.RecordComponent;

public record TerritoireEtoileRequestParametizer(String code,
                                                 String date,
                                                 Class<?> typeOrigine,
                                                 String filtreNom,
                                                 String chefLieu,
                                                 boolean com) implements ParametersForQuery<fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer> {

    //for geo/departements
    public TerritoireEtoileRequestParametizer(String date,
                                              Class<?> typeOrigine,
                                              String chefLieu,
                                              boolean com) {
        this("none", date, typeOrigine, "*", chefLieu, com);
    }

    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }

}