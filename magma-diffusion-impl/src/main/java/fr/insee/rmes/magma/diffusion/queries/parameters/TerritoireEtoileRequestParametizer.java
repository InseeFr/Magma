package fr.insee.rmes.magma.diffusion.queries.parameters;


import java.lang.reflect.RecordComponent;

public record TerritoireEtoileRequestParametizer(String code,
                                                 String date,
                                                 Class<?> typeOrigine,
                                                 String filtreNom,
                                                 String chefLieu,
                                                 boolean com) implements ParametersForQuery<fr.insee.rmes.magma.diffusion.queries.parameters.TerritoireRequestParametizer> {

    //for geo/departements, arrondissements, arrondissementsMunicipaux,
    public TerritoireEtoileRequestParametizer(String date,
                                              Class<?> typeOrigine,
                                              String chefLieu,
                                              boolean com) {
        this("none", date, typeOrigine, "*", chefLieu, com);
    }

    //for geo/airesDAttractionDesVilles, communesAssociees, communesDeleguees, etc
    public TerritoireEtoileRequestParametizer(String date,
                                              Class<?> typeOrigine,
                                              String chefLieu) {
        this("none", date, typeOrigine, "*", chefLieu, true);
    }

    //for geo/communes, geo/bassinsDeVie2022
    public TerritoireEtoileRequestParametizer(String date,
                                              Class<?> typeOrigine,
                                              String filtreNom,
                                              String chefLieu,
                                              boolean com) {
        this("none", date, typeOrigine, filtreNom, chefLieu, com);
    }


    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }

}